package com.example.petscoffee.model

class WeatherResponse(val code: Int, val msg: String, val data:WeatherData) {
    class WeatherData(
        val address: String,
        val cityCode: String,
        val temp: String,
        val weather: String,
        val windDirection: String,
        val windPower: String,
        val humidity: String,
        val reportTime: String
    )
}