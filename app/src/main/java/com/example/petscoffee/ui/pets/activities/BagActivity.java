package com.example.petscoffee.ui.pets.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petscoffee.R;
import com.example.petscoffee.adapters.GoodsAdapter;
import com.example.petscoffee.repository.local.Archive;
import com.example.petscoffee.databinding.ActivityBagBinding;
import com.example.petscoffee.model.goods.Goods;

import java.util.List;

public class BagActivity extends AppCompatActivity {
    private static List<Goods> bag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityBagBinding bagBinding = DataBindingUtil.setContentView(this,R.layout.activity_bag);
        Archive.loadCoffee(this, coffeeShop ->{
            bagBinding.setCoffeeShop(coffeeShop);//设置dataBinding
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
}