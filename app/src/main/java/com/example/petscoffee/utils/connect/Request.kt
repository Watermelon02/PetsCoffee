package com.example.petscoffee.utils.connect

data class Request(val method: String, val url: String)

class RequestBuilder {
    private lateinit var method: String
    private lateinit var url: String
    fun method(method: String) = apply { this.method = method }
    fun url(url: String) = apply { this.url = url }
    fun build() = Request(method,url)
}

