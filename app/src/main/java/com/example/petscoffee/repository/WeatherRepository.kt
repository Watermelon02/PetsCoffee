package com.example.petscoffee.repository

import androidx.lifecycle.liveData
import com.example.petscoffee.repository.network.WeatherNetwork

object WeatherRepository {
    fun queryWeather(adcode: String) = liveData {
        if (adcode==null){
            emit(null)
        }
        val result = try {
            val weatherResponse = WeatherNetwork.queryWeather(adcode)
            val data = weatherResponse.lives[0]
            Result.success(data)
        } catch (e: Exception) {
            Result.failure(RuntimeException("WeatherRepositoryException"))
        }
        emit(result)
    }

    suspend fun queryPlace(ip: String) :String {
        return WeatherNetwork.queryPlace(ip).adcode
    }
}