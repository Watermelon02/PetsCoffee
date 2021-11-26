package com.example.petscoffee.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.petscoffee.bag.Bag;
import com.example.petscoffee.coffeeShop.CoffeeShop;

@Database(entities = {CoffeeShop.class, Bag.class}, version = 1,exportSchema = false)
public abstract class CoffeeDatabase extends RoomDatabase {
    private static CoffeeDatabase database;

    public synchronized static CoffeeDatabase getInstance(Context context) {
        if (database == null) {
            database = Room.databaseBuilder(context.getApplicationContext(), CoffeeDatabase.class, "coffeeDatabase").build();
        }
        return database;
    }

    public abstract CoffeeShopDao coffeeShopDao();//Database access object
}
