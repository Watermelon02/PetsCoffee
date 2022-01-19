package com.example.petscoffee.repository

import androidx.lifecycle.liveData
import com.example.petscoffee.repository.network.WeatherNetwork
import com.example.petscoffee.utils.ipGetter.IpGetter

object WeatherRepository {
    fun queryWeather(adcode: String?) = liveData {
        adcode?.let {
            val result = try {
                val weatherResponse = WeatherNetwork.queryWeather(adcode)
                val data = weatherResponse.lives[0]
                Result.success(data)
            } catch (e: Exception) {
                Result.failure(RuntimeException("WeatherRepositoryException"))
            }
            emit(result)
        }
    }

    suspend fun queryPlace() :String {
        val ip = IpGetter.getLocalIp()
        return WeatherNetwork.queryPlace(ip).district
    }

    suspend fun queryAdcode(place:String) :String {
        return WeatherNetwork.queryAdcode(place).districts[0].adcode
    }
}