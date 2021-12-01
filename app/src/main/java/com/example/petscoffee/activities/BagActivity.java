package com.example.petscoffee.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petscoffee.R;
import com.example.petscoffee.adapters.GoodsAdapter;
import com.example.petscoffee.database.Archive;
import com.example.petscoffee.goods.Goods;

import java.util.ArrayList;
import java.util.List;

public class BagActivity extends AppCompatActivity {
    private static List<Goods> bag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bag);
        Archive.loadCoffee(this, coffeeShop ->{
            bag = coffeeShop.getBag();
            this.runOnUiThread(()->{
                RecyclerView recyclerView = findViewById(R.id.bag_recyclerview);
                LinearLayoutManager layoutManager = new LinearLayoutManager(this);
                recyclerView.setLayoutManager(layoutManager);
                GoodsAdapter goodsAdapter = new GoodsAdapter(bag);
                recyclerView.setAdapter(goodsAdapter);
            });
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }
}