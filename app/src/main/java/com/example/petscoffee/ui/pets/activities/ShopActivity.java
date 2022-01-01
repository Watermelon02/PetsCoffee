package com.example.petscoffee.ui.pets.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.petscoffee.R;
import com.example.petscoffee.model.CoffeeShop;
import com.example.petscoffee.repository.local.Archive;
import com.example.petscoffee.databinding.ActivityShopBinding;
import com.example.petscoffee.listener.BottomBarListener;
import com.example.petscoffee.ui.pets.viewModel.ShopViewModel;

public class ShopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //为头部toolBar的dataBinding传入数据
        ActivityShopBinding shopBinding = DataBindingUtil.setContentView(this, R.layout.activity_shop);
        Archive.loadCoffee(this, shopBinding::setCoffeeShop);
        //为底部toolBar的dataBinding传入数据
        shopBinding.setBottomBarListener(new BottomBarListener(this));
        //标题栏
        Button choose_goods = findViewById(R.id.shop_choose_goods);
        Button choose_pets = findViewById(R.id.shop_choose_pets);
        View goods = findViewById(R.id.shop_goods_layout);
        View pets = findViewById(R.id.shop_pets_layout);
        ShopViewModel shopViewModel = new ViewModelProvider(this).get(ShopViewModel.class);
        shopViewModel.getCoffeeShopLiveData().observe(this, new Observer<CoffeeShop>() {

            @Override
            public void onChanged(CoffeeShop coffeeShop) {
                shopBinding.setCoffeeShop(coffeeShop);
            }
        });
        choose_goods.setOnClickListener(v -> {//切换显示的fragment
            pets.setVisibility(View.GONE);
            goods.setVisibility(View.VISIBLE);
        });
        choose_pets.setOnClickListener(v -> {
            pets.setVisibility(View.VISIBLE);
            goods.setVisibility(View.GONE);
        });
    }
}