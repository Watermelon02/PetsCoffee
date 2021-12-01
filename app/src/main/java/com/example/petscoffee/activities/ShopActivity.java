package com.example.petscoffee.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.petscoffee.R;
import com.example.petscoffee.coffeeShop.CoffeeShop;

public class ShopActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        Button choose_goods = findViewById(R.id.shop_choose_goods);
        Button choose_pets = findViewById(R.id.shop_choose_pets);
        View goods = findViewById(R.id.shop_goods_layout);
        View pets = findViewById(R.id.shop_pets_layout);
        choose_goods.setOnClickListener(v->{
            pets.setVisibility(View.GONE);
            goods.setVisibility(View.VISIBLE);
        });
        choose_pets.setOnClickListener(v -> {
            pets.setVisibility(View.VISIBLE);
            goods.setVisibility(View.GONE);
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }
}