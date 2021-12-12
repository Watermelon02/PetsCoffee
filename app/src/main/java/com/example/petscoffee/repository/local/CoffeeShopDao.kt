package com.example.petscoffee.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.petscoffee.coffeeShop.CoffeeShop;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface CoffeeShopDao {
    @Insert
    void insertCoffee(CoffeeShop coffee);

    @Query("SELECT * FROM coffeeShop WHERE id =:id")
    CoffeeShop queryCoffee(int id);

    @Query("SELECT * FROM coffeeShop WHERE account=:account")
    CoffeeShop queryCoffee(String account);

    @Delete
    void deleteCoffee(CoffeeShop coffee);

    @Query("SELECT * FROM coffeeShop")
    List<CoffeeShop> queryCoffees();

    @Update
    void upDateCoffee(CoffeeShop coffee);

    //上面为CoffeeShop数据的curd
}
