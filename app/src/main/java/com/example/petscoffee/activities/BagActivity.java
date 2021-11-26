package com.example.petscoffee.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petscoffee.R;
import com.example.petscoffee.adapters.GoodsAdapter;
import com.example.petscoffee.bag.Bag;
import com.example.petscoffee.coffeeShop.CoffeeShop;
import com.example.petscoffee.database.Archive;

public class BagActivity extends AppCompatActivity {
    private static Bag bag = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bag);
        try {
            CoffeeShop coffee = Archive.load(this);
            bag = coffee.getBag();//通过intent获取bag对象
        }catch (Exception e){
            e.printStackTrace();
        }
        RecyclerView recyclerView = findViewById(R.id.bag_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        GoodsAdapter goodsAdapter = new GoodsAdapter(bag);
        recyclerView.setAdapter(goodsAdapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }
}