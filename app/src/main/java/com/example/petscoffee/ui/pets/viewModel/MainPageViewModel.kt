package com.example.petscoffee.ui.pets.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.petscoffee.repository.WeatherRepository
import com.example.petscoffee.repository.local.CoffeeDatabase
import com.example.petscoffee.repository.local.Archive

class MainPageViewModel(application: Application) : AndroidViewModel(application) {
    private val address = MutableLiveData<String>()

    val coffeeShop = CoffeeDatabase.getInstance(application).coffeeShopDao()
        .queryCoffeeLiveData(Archive.getId())

    val weather = Transformations.switchMap(address) {address->
        WeatherRepository.queryWeather(address)
    }

    fun queryWeather(address: String) {
        this.address.value = address
    }
}