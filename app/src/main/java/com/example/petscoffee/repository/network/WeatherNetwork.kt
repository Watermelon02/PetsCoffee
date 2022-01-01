package com.example.petscoffee.repository.network

import com.example.petscoffee.model.WeatherResponse


object WeatherNetwork {
    private val weatherService = ServiceCreator.create<WeatherService>()
    suspend fun queryWeather(address : String) = weatherService.queryWeather(address).await<WeatherResponse>()
}