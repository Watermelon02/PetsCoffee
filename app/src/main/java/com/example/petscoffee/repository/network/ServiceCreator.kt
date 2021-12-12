package com.example.petscoffee.repository.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceCreator {
    private const val BASE_URL = "https://www.mxnzp.com/api/"
    private val retrofit =
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build()
    fun <T> create(service: Class<T>):T = retrofit.create(service)
    inline fun <reified T> create() = create(T::class.java)
}