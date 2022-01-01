package com.example.petscoffee.repository.network

import com.example.petscoffee.model.WeatherResponse
import com.example.petscoffee.utils.connect.Call
import com.example.petscoffee.utils.retrofit.RetrofitBuilder


interface WeatherService {
    @RetrofitBuilder.GET("weather/current/{address}?app_id=e1fkqpoasgomn92q&app_secret=RnZHRGR5ZTNkaTVGR0F2ekRObSt4UT09")
    fun queryWeather(@RetrofitBuilder.Path("address") address: String): Call<WeatherResponse>
}
