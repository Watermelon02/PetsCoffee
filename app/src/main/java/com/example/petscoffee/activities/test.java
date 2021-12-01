package com.example.petscoffee.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.petscoffee.R;
import com.example.petscoffee.coffeeShop.CoffeeShop;

public class test extends AppCompatActivity {
    CoffeeShop coffee;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        new Thread(()->{
        }).start();

    }
}