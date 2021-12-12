package com.example.petscoffee.repository.network

import com.example.petscoffee.model.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherService {
    @GET("weather/current/{address}?app_id=e1fkqpoasgomn92q&app_secret=RnZHRGR5ZTNkaTVGR0F2ekRObSt4UT09")
    fun queryWeather(@Path("address") address: String): Call<WeatherResponse>
}