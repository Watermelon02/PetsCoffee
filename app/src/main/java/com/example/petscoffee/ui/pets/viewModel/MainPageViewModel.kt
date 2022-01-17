package com.example.petscoffee.ui.pets.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.petscoffee.repository.WeatherRepository
import com.example.petscoffee.repository.local.Archive
import com.example.petscoffee.repository.local.CoffeeDatabase
import com.example.petscoffee.utils.ipGetter.IpGetter

class MainPageViewModel(application: Application) : AndroidViewModel(application) {
    private val ip = IpGetter.getLocalIp()
    private val adcode = MutableLiveData<String>()

    val coffeeShop = CoffeeDatabase.getInstance(application).coffeeShopDao()
        .queryCoffeeLiveData(Archive.getId())

    val weather = Transformations.switchMap(adcode) { adcode ->
        WeatherRepository.queryWeather(adcode)
    }

    suspend fun queryWeather(){
        adcode.value = WeatherRepository.queryPlace(ip)
    }
}