package com.example.petscoffee.utils.retrofit

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import java.lang.reflect.Method
import java.lang.reflect.Proxy

class RetrofitBuilder {
    annotation class GET(val url: String)
    annotation class POST(val url: String)
    annotation class Path(val value: String)

    private lateinit var baseurl: String
    private var gson: Gson = Gson()

    fun baseUrl(baseurl: String): RetrofitBuilder = apply {
        this.baseurl = baseurl
    }

    fun addGson(gson: Gson) {
        this.gson = gson
    }

    fun build() = Retrofit(baseurl, gson)
}

class Retrofit(val baseurl: String, val gson: Gson?) {
    @RequiresApi(Build.VERSION_CODES.O)
    fun <T> create(service: Class<T>): T {
        return Proxy.newProxyInstance(
            service.classLoader,
            arrayOf(service)
        ) { proxy, method, args -> loadServiceMethod(method, this).invoke(args) } as T
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun loadServiceMethod(
        method: Method,
        retrofit: Retrofit
    ): ServiceMethod<Any?> {
        return ServiceMethod.parseAnnotation(method,retrofit)
    }
}