package com.example.petscoffee.repository.network

import retrofit2.await

object WeatherNetwork {
    suspend fun queryWeather(address : String) = ServiceCreator.create<WeatherService>().queryWeather(address).await()
}