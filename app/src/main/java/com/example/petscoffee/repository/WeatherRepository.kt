package com.example.petscoffee.repository

import android.util.Log
import androidx.lifecycle.liveData
import com.example.petscoffee.repository.network.WeatherNetwork

object WeatherRepository {
    fun queryWeather(address: String) = liveData {
        val result = try {
            val weatherResponse = WeatherNetwork.queryWeather(address)
            val data = weatherResponse.data
            Result.success(data)
        } catch (e: Exception) {
            Result.failure(RuntimeException("WeatherRepositoryException"))
        }
        emit(result)
    }
}