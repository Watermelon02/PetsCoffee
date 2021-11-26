package com.example.petscoffee.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.petscoffee.coffeeShop.CoffeeShop;

@Dao
public interface CoffeeShopDao {
    @Insert
    void insert(CoffeeShop coffee);

    @Query("SELECT * FROM coffeeShop WHERE id =:id")
    CoffeeShop query(int id);
}
