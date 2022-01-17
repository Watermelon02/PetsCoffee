package com.example.petscoffee.model

data class PlaceResponse(//ip地址查询bean
    val adcode: String,
    val city: String,
    val info: String,
    val infocode: String,
    val province: String,
    val rectangle: String,
    val status: String
)