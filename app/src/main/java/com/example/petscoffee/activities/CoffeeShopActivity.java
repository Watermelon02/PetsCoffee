package com.example.petscoffee.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.petscoffee.R;
import com.example.petscoffee.coffeeShop.CoffeeShop;
import com.example.petscoffee.file.Archive;
import com.example.petscoffee.service.WorkService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.io.File;
import java.io.FileNotFoundException;

public class CoffeeShopActivity extends AppCompatActivity implements View.OnClickListener {//游戏主界面
    private static CoffeeShop coffee;
    private DrawerLayout drawerLayout;//用户信息侧边栏
    private ImageView userHead;//用户头像

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee_shop);
        TextView textView_time = findViewById(R.id.coffeePage_time);//显示时间（day和time）
        TextView textView_money = findViewById(R.id.coffeePage_money);//显示金钱
        drawerLayout = findViewById(R.id.coffeePage_DrawerLayout);
        //绑定控件
        Button button_bag = findViewById(R.id.coffeePage_bag);
        Button button_shop = findViewById(R.id.coffeePage_shop);
        Button button_pets = findViewById(R.id.coffeePage_pets);
        Button button_work = findViewById(R.id.coffeePage_work);
        Button button_wash = findViewById(R.id.coffeePage_wash);
        FloatingActionButton float_user = findViewById(R.id.coffeePage_floating);
        //绑定控件
        coffee = Archive.load(CoffeeShopActivity.this);
        textView_time.setText("第" + coffee.getDay() + "天" + "的"
                + (coffee.getTime() == 0 ? "上午" : "下午"));
        textView_money.setText("💰:" + coffee.getMoney());
        //设置页面显示信息
        NavigationView navigationView = findViewById(R.id.coffeePage_navigationView);
        View headerView = navigationView.inflateHeaderView(R.layout.user_nav_header);
        userHead = headerView.findViewById(R.id.user_nav_header_image);
        //通过navigationView的inflateHeaderView（）方法加载headerView,
        // 以此来实现headerView中的用户头像点击事件
        File file = new File("/data/data/com.example.petscoffee/userHead.jpg");
        if (file.exists()) {
            try {
                userHead.setImageBitmap(BitmapFactory.decodeStream(getContentResolver().openInputStream(FileProvider.getUriForFile(this, "com.example.petscoffee.activities.PictureActivity", file))));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        userHead.setOnClickListener(this);
        button_bag.setOnClickListener(this);
        button_shop.setOnClickListener(this);
        button_pets.setOnClickListener(this);
        button_work.setOnClickListener(this);
        button_wash.setOnClickListener(this);
        float_user.setOnClickListener(this);
        //设置点击监听
    }

    @Override
    protected void onResume() {
        super.onResume();
        float money = 0;
        TextView textView_time = findViewById(R.id.coffeePage_time);//显示时间（day和time）
        TextView textView_money = findViewById(R.id.coffeePage_money);//显示金钱
        money = Archive.load(this).getMoney();
        coffee = Archive.load(CoffeeShopActivity.this);
        textView_time.setText("第" + coffee.getDay() + "天" + "的"
                + (coffee.getTime() == 0 ? "上午" : "下午"));
        textView_money.setText("💰:" + coffee.getMoney());
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.coffeePage_bag://打开背包界面
                Intent bagIntent = new Intent(CoffeeShopActivity.this, BagActivity.class);
                startActivityForResult(bagIntent, 1);
                break;
            case R.id.coffeePage_shop://打开商店界面
                Intent shopIntent = new Intent(CoffeeShopActivity.this, ShopActivity.class);
                startActivityForResult(shopIntent, 2);
                break;
            case R.id.coffeePage_pets://打开宠物界面
                Intent petsIntent = new Intent(CoffeeShopActivity.this, PetsActivity.class);
                startActivityForResult(petsIntent, 3);
                break;
            case R.id.coffeePage_work:
                Intent workIntent = new Intent(this, WorkService.class);
                startService(workIntent);//创建并绑定WorkService
                break;
            case R.id.coffeePage_wash:
                break;
            case R.id.coffeePage_floating:
                drawerLayout.openDrawer(GravityCompat.START);
                break;//显示侧边栏
            case R.id.user_nav_header_image://点击用户头像进入用户头像设置activity
                Intent pictureIntent = new Intent(CoffeeShopActivity.this, PictureActivity.class);
                startActivityForResult(pictureIntent, 4);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1://背包活动结束
            case 2://商店界面结束
            case 3://宠物界面结束
                refreshCoffee(resultCode);
                break;
            case 4://头像设置界面返回
                try {//重新设置userHeader
                    userHead.setImageBitmap(BitmapFactory.decodeStream(getContentResolver().openInputStream(FileProvider.getUriForFile(this, "com.example.petscoffee.activities.PictureActivity", new File("/data/data/com.example.petscoffee/userHead.jpg")))));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
        }
    }

    @SuppressLint("SetTextI18n")
    public void refreshCoffee(int resultCode) {//在进行各界面的操作后，
        // 根据回传的Intent刷新主界面的view和coffeeShop实例
        if (resultCode == RESULT_OK) {
            coffee = Archive.load(CoffeeShopActivity.this);
            TextView textView_time = findViewById(R.id.coffeePage_time);//显示时间（day和time）
            TextView textView_money = findViewById(R.id.coffeePage_money);//显示金钱
            textView_time.setText("第" + coffee.getDay() + "天" + "的"
                    + (coffee.getTime() == 0 ? "上午" : "下午"));
            textView_money.setText("💰:" + coffee.getMoney());
        } else {
            Toast.makeText(CoffeeShopActivity.this, "回传数据错误", Toast.LENGTH_SHORT).show();
        }
    }

    /*@Override
    protected void onDestroy() {
        super.onDestroy();
        if (workBinder.getIsFinished()) {//判断WorkService是否结束，如果是则不保存进度（避免覆盖掉work后的结果），
        } else {//否则存储进度
            Archive.save(coffee, CoffeeShopActivity.this);
        }
    }*/
}
