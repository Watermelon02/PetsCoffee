package com.example.petscoffee.repository.network

import com.example.petscoffee.model.PlaceResponse
import com.example.petscoffee.model.WeatherResponse
import com.example.petscoffee.model.pets.AdcodeResponse


object WeatherNetwork {
    private val weatherService = ServiceCreator.create<WeatherService>()
    private val placeService = ServiceCreator.create<PlaceService>()
    private val adcodeService = ServiceCreator.create<AdcodeService>()
    suspend fun queryWeather(adcode: String) =
        weatherService.queryWeather(adcode).await<WeatherResponse>()

    suspend fun queryPlace(ip: String): PlaceResponse {
        return placeService.queryPlace(ip).await()
    }

    suspend fun queryAdcode(place:String): AdcodeResponse {
        return adcodeService.queryAdcode(place).await()
    }
}