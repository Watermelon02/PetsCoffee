package com.example.petscoffee.repository.local

import com.example.petscoffee.model.CoffeeShop
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.petscoffee.model.pets.Pets

@Dao
interface CoffeeShopDao {
    @Insert
    fun insertCoffee(coffee: CoffeeShop)

    @Query("SELECT * FROM coffeeShop WHERE id =:id")
    fun queryCoffee(id: Int): CoffeeShop

    @Query("SELECT * FROM coffeeShop WHERE account=:account")
    fun queryCoffee(account: String): CoffeeShop

    @Query("SELECT * FROM coffeeShop WHERE id=:id")
    fun queryCoffeeLiveData(id: Int): LiveData<CoffeeShop>

    @Delete
    fun deleteCoffee(coffee: CoffeeShop)

    @Query("SELECT * FROM coffeeShop")
    fun queryCoffees(): MutableList<CoffeeShop>

    @Update
    fun upDateCoffee(coffee: CoffeeShop) //上面为CoffeeShop数据的curd
}