package com.example.petscoffee.utils.connect

import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

open class Call<T>(val request: Request) {
    inline fun <reified T> enqueue(callback: Callback<T>) {
        when (request.method) {
            "GET" -> {
                Thread {
                    try {
                        val response = Connect.get<T>(request.url).body
                        callback.onResponse(Response(response))
                    } catch (e: Exception) {
                        callback.onFailure(e)
                    }
                }.start()
            }
            "POST" -> {
                Thread {
                    try {
                        val response = Connect.post<T>(request.url)
                        callback.onResponse(response)
                    } catch (e: Exception) {
                        callback.onFailure(e)
                    }
                }.start()
            }
        }
    }

    suspend inline fun <reified T> await(): T {
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onResponse(response: Response<T>) {
                    val body = response.body
                    if (body != null) {
                        continuation.resumeWith(Result.success(body))
                    } else {
                        continuation.resumeWithException(RuntimeException("ResponseBody is null"))
                    }
                }

                override fun onFailure(e: Exception) {
                    continuation.resumeWithException(RuntimeException("ResponseBody is null"))
                }
            })
        }
    }

    interface Callback<T> {
        fun onResponse(response: Response<T>)
        fun onFailure(e: Exception)
    }

}

