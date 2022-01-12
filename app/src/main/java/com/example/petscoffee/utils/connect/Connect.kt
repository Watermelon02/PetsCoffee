package com.example.petscoffee.utils.connect

import android.util.Log
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import kotlin.math.log

object Connect {
    var cookies: List<String>? = null
    fun newCall(request: Request) {

    }

    inline fun <reified T> get(url: String): Response<T> {
        (URL(url).openConnection() as HttpURLConnection).run {
            if (cookies != null) {
                val cookie = java.lang.StringBuilder()
                for (srt in cookies!!) {
                    cookie.append(srt).append(";")
                }
                setRequestProperty("Cookie", cookie.toString())
            }
            doInput = true
            doOutput = false
            connectTimeout = 15000
            readTimeout = 15000
            requestMethod = "GET"
            setRequestProperty("Connection", "Keep-Alive")
            connect()
            val reader = BufferedReader(InputStreamReader(inputStream))
            val builder = StringBuilder()
            reader.use {
                it.forEachLine {
                    Log.d("testTag", it)
                    builder.append(it)
                }
            }
            val response = Gson().fromJson(builder.toString(), T::class.java)
            return Response(response)
        }
    }

    inline fun <reified T> post(url: String): Response<T> {
        (URL(url).openConnection() as HttpURLConnection).run {
            if (cookies != null) {
                val cookie = java.lang.StringBuilder()
                for (srt in cookies!!) {
                    cookie.append(srt).append(";")
                }
                setRequestProperty("Cookie", cookie.toString())
            }
            doInput = true
            doOutput = true
            connectTimeout = 15000
            readTimeout = 15000
            requestMethod = "POST"
            setRequestProperty("Connection", "Keep-Alive")
            connect()
            val header = headerFields
            cookies = header["Set-cookie"]
            val reader = BufferedReader(InputStreamReader(inputStream))
            val builder = StringBuilder()
            reader.use { it.forEachLine { builder.append(it) } }
            val response = Gson().fromJson(builder.toString(), T::class.java)
            return Response(response)
        }
    }


}