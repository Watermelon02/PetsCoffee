package com.example.petscoffee.ui.pets.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.petscoffee.repository.WeatherRepository
import com.example.petscoffee.repository.local.Archive
import com.example.petscoffee.repository.local.CoffeeDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainPageViewModel(application: Application) : AndroidViewModel(application) {
    private var place = MutableLiveData<String>("110000")

    val coffeeShop = CoffeeDatabase.getInstance().coffeeShopDao()
        .queryCoffeeLiveData(Archive.id)

    val adcode = Transformations.switchMap(place) { place ->
        WeatherRepository.queryWeather(place)
    }

    val weather = Transformations.switchMap(adcode) { adcode ->
        WeatherRepository.queryWeather(adcode.getOrNull()?.adcode)
    }



    suspend fun queryWeather() {
        val mAdcode = WeatherRepository.queryPlace()
        withContext(Dispatchers.Main){
            place.value = mAdcode
        }
    }
}