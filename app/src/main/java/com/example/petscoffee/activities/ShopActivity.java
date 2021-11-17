package com.example.petscoffee.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.petscoffee.R;
import com.example.petscoffee.coffeeShop.CoffeeShop;
import com.example.petscoffee.file.Archive;
import com.example.petscoffee.fragment.Shop_goods_fragment;
import com.example.petscoffee.fragment.Shop_pets_fragment;

public class ShopActivity extends AppCompatActivity{
    private static CoffeeShop coffee;

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
            Shop_goods_fragment goods_fragment = (Shop_goods_fragment) getSupportFragmentManager().findFragmentById(R.id.shop_activity_goods_fragment);
            goods_fragment.setCoffee(Archive.load(ShopActivity.this));//设置碎片中的CoffeeShop数据
        });
        choose_pets.setOnClickListener(v -> {
            pets.setVisibility(View.VISIBLE);
            goods.setVisibility(View.GONE);
            Shop_pets_fragment pets_fragment = (Shop_pets_fragment) getSupportFragmentManager().findFragmentById(R.id.shop_activity_pets_fragment);
            pets_fragment.setCoffee(Archive.load(ShopActivity.this));//设置碎片中的CoffeeShop数据
        });
    }



    @Override
    public void onBackPressed() {//当返回时，返回coffeeShop数据
        coffee = Archive.load(ShopActivity.this);
        Archive.save(coffee,ShopActivity.this);
        CoffeeShop coffeeShop = Archive.load(ShopActivity.this);
        Intent intent = new Intent();
        intent.putExtra("coffee", coffee);
        setResult(RESULT_OK, intent);
        Archive.save(coffee,this);//保存
        finish();
    }
}