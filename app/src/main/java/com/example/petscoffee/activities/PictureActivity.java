package com.example.petscoffee.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.bumptech.glide.Glide;
import com.example.petscoffee.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class PictureActivity extends AppCompatActivity implements View.OnClickListener {
    private final static int TAKE_PHOTO = 0;
    private final static int OPEN_ALBUM = 1;
    private ImageView image;
    private Uri uri;//文件地址uri

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);
        image = findViewById(R.id.pictureActivity_image);
        Button button_takePhoto = findViewById(R.id.pictureActivity_takePhoto);
        Button button_openAlbum = findViewById(R.id.pictureActivity_openAlbum);
        File file = new File("/data/data/com.example.petscoffee/userHead.jpg");
        uri = FileProvider.getUriForFile(this, "com.example.petscoffee.activities.PictureActivity", file);
        try {
            if (file.exists()) {//若存在则显示头像
                image.setImageBitmap(BitmapFactory.decodeStream(getContentResolver().openInputStream(uri)));
            } else {
                file.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        button_openAlbum.setOnClickListener(this);
        button_takePhoto.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pictureActivity_takePhoto:
                Intent takePhoto_intent = new Intent("android.media.action.IMAGE_CAPTURE");
                takePhoto_intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                startActivityForResult(takePhoto_intent, TAKE_PHOTO);
                break;
            case R.id.pictureActivity_openAlbum:
                Intent openAlbum_intent = new Intent("android.intent.action.GET_CONTENT");
                openAlbum_intent.setType("image/*");
                startActivityForResult(openAlbum_intent, OPEN_ALBUM);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case TAKE_PHOTO:
                    try {
                        Bitmap photo = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
                        setImage(photo);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case OPEN_ALBUM:
                    new Thread(()->{
                        try {//保存用户选取的照片到指定头像路径(通过io流)
                            OutputStream out = new FileOutputStream(new File("/data/data/com.example.petscoffee/userHead.jpg"));
                            InputStream in = getContentResolver().openInputStream(data.getData());
                            int temp = 0;
                            while ((temp = in.read()) != -1) {
                                out.write(temp);
                            }
                            Bitmap photo = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
                            setImage(photo);//不知道为什么卡的一批
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }).start();
            }
        } else {//图片处理失败
            Toast.makeText(this, "导入图片错误", Toast.LENGTH_SHORT).show();
        }
    }

    public void setImage(Bitmap photo) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Glide.with(PictureActivity.this).load(photo).into(image);
            }
        });
    }
}