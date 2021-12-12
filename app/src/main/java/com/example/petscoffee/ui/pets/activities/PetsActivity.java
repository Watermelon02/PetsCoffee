package com.example.petscoffee.ui.pets.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.petscoffee.R;
import com.example.petscoffee.adapters.PetsAdapter;
import com.example.petscoffee.model.CoffeeShop;
import com.example.petscoffee.repository.local.Archive;
import com.example.petscoffee.model.pets.Pets;


import java.util.List;

public class PetsActivity extends AppCompatActivity {
    private static CoffeeShop coffee;
    private static List<Pets> pets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pets);
        Archive.loadCoffee(this, coffeeShop -> {
            this.runOnUiThread(()->{
                coffee = coffeeShop;
                ViewPager2 recyclerView = findViewById(R.id.pets_recyclerView);
                PetsAdapter petsAdapter = new PetsAdapter(coffee);
                recyclerView.setAdapter(petsAdapter);
            });
        });
    }

    @Override
    public void onBackPressed() {
        Archive.saveCoffee(coffee,this);//存储数据
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }
}