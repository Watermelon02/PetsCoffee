package com.example.petscoffee.repository

import androidx.lifecycle.liveData
import com.example.petscoffee.repository.network.WeatherNetwork
import kotlinx.coroutines.Dispatchers
import java.lang.Exception
import java.lang.RuntimeException

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