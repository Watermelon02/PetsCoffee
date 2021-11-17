package com.example.petscoffee.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.petscoffee.R;
import com.example.petscoffee.adapters.PetsAdapter;
import com.example.petscoffee.coffeeShop.CoffeeShop;
import com.example.petscoffee.file.Archive;
import com.example.petscoffee.pets.Pets;

import java.util.ArrayList;

public class PetsActivity extends AppCompatActivity {
    private static CoffeeShop coffee;
    private static ArrayList<Pets> pets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pets);
        coffee = Archive.load(PetsActivity.this);
        pets = coffee.getPets();
        ViewPager2 recyclerView = findViewById(R.id.pets_recyclerView);
        PetsAdapter petsAdapter = new PetsAdapter(pets,coffee.getBag());
        recyclerView.setAdapter(petsAdapter);
    }

    @Override
    public void onBackPressed() {
        Archive.save(coffee,PetsActivity.this);//存储数据
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }
}