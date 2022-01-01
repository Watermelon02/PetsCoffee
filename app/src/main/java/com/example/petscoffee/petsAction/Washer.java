package com.example.petscoffee.petsAction;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.example.petscoffee.R;
import com.example.petscoffee.adapters.PetsAdapter;
import com.example.petscoffee.model.pets.Pets;

import java.util.TimerTask;

public class Washer extends TimerTask implements Runnable {//实现了线程功能，也许以后可以加个一键洗澡？
    private Pets pet;
    private Context context;
    private ProgressBar progressBar;
    private PetsAdapter.ViewHolder petsViewHolder;

    public Washer(Context context, Pets pet, PetsAdapter.ViewHolder petsViewHolder) {
        this.pet = pet;
        this.context = context;
        this.petsViewHolder = petsViewHolder;
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_pets_wash, null);//获取洗澡进度条view
        AlertDialog.Builder alertdialog = new AlertDialog.Builder(context);//洗澡小游戏功能,
        // 通过传入的context设置显示的位置(一般是由宠物界面的洗澡按钮调用)
        alertdialog.setTitle("洗 澡");
        TextView textView = view.findViewById(R.id.wash_text);
        textView.setText("快快抓住逃跑的宠物");
        progressBar = view.findViewById(R.id.wash_progressBar);
        Button button = view.findViewById(R.id.wash_button);
        //点击按钮增加进度
        button.setOnClickListener(v -> {
            int progress = progressBar.getProgress();
            if (progress < 100) {//进度不满时
                progress += 2 * (10 - pet.getCleanliness());//根据宠物现在的清洁度调整清洗难度
                progressBar.setProgress(progress);
                if (progress > 30) {//进度超过50时，更改textView
                    textView.setText("就快洗完了，加油");
                } else if(progress>70){
                    textView.setText("真的就快洗完了，加油");
                } else {//小于30时
                    textView.setText("快快抓住逃跑的宠物");
                }
            } else {
                pet.setCleanliness(10 - pet.getCleanliness());//进度条满,恢复满清洁度
                textView.setText("洗干净了！");
            }
        });
        alertdialog.setView(view);
        alertdialog.setCancelable(false);
        alertdialog.setPositiveButton("完毕", (dialog, which) -> {
            TextView petsCleanliness = petsViewHolder.getPetsCleanliness();//重新设置宠物属性界面的数值显示
            petsCleanliness.setText("clean :"+String.valueOf(pet.getCleanliness()));
        });
        alertdialog.setNegativeButton("结束", (dialog, which) -> {
            TextView petsCleanliness = petsViewHolder.getPetsCleanliness();
            petsCleanliness.setText("clean :"+String.valueOf(pet.getCleanliness()));
        });
        alertdialog.show();
    }

    public void run() {//TimerTask类run方法,
        // 当清洁度不满时，定时减少进度条进度,通过PetsAdapter中的Timer类控制调用间隔
        if (progressBar.getProgress() < 100) {
            progressBar.setProgress(progressBar.getProgress() - 5);
        }
    }

}
