package com.example.petscoffee.repository.network

import com.example.petscoffee.utils.retrofit.RetrofitBuilder


object ServiceCreator {
    private const val BASE_URL = "https://restapi.amap.com/"
    private val retrofit =
        RetrofitBuilder().baseUrl(BASE_URL).build()

    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)
    inline fun <reified T> create() = create(T::class.java)
}