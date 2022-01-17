package com.example.petscoffee.repository.network

import com.example.petscoffee.model.PlaceResponse
import com.example.petscoffee.model.WeatherResponse


object WeatherNetwork {
    private val weatherService = ServiceCreator.create<WeatherService>()
    private val placeService = ServiceCreator.create<PlaceService>()
    suspend fun queryWeather(adcode : String) = weatherService.queryWeather(adcode).await<WeatherResponse>()
    suspend fun queryPlace(ip:String) = placeService.queryPlace(ip).await<PlaceResponse>()
}