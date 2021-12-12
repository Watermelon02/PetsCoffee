package com.example.petscoffee.ui.pets.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.petscoffee.model.CoffeeShop;
import com.example.petscoffee.repository.local.Archive;
import com.example.petscoffee.repository.local.CoffeeDatabase;

public class ShopViewModel extends AndroidViewModel {
    LiveData<CoffeeShop> coffeeShopLiveData;

    public ShopViewModel(@NonNull Application application) {
        super(application);
        coffeeShopLiveData = CoffeeDatabase.getInstance(application).coffeeShopDao().queryCoffeeLiveData(Archive.getId());
    }

    public LiveData<CoffeeShop> getCoffeeShopLiveData() {
        return coffeeShopLiveData;
    }
}
