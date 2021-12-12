package com.example.petscoffee.listener;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.petscoffee.R;
import com.example.petscoffee.ui.pets.activities.BagActivity;
import com.example.petscoffee.ui.pets.activities.MainPageActivity;
import com.example.petscoffee.ui.pets.activities.PetsActivity;
import com.example.petscoffee.ui.pets.activities.ShopActivity;
import com.example.petscoffee.service.WorkService;

public class BottomBarListener {//底部toolbar的点击监听
    private Context context;

    public BottomBarListener(Context context) {
        this.context = context;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bottomBar_bag:
                Intent bagIntent = new Intent(context, BagActivity.class);
                context.startActivity(bagIntent);
                break;
            case R.id.bottomBar_shop:
                Intent shopIntent = new Intent(context, ShopActivity.class);
                context.startActivity(shopIntent);
                break;
            case R.id.bottomBar_work:
                Intent workIntent = new Intent(context, WorkService.class);
                context.startService(workIntent);
                break;
            case R.id.bottomBar_pets:
                Intent petsIntent = new Intent(context, PetsActivity.class);
                context.startActivity(petsIntent);
                break;
            case R.id.bottomBar_wash:
                Intent homeIntent = new Intent(context, MainPageActivity.class);
                context.startActivity(homeIntent);
                ;
        }
    }
}
