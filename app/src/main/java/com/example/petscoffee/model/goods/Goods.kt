package com.example.petscoffee.model.goods

import java.io.Serializable

open class Goods(//定义商品模板
    val name: String, val price: Float, private var number: Int, val imageId: Int, val info: String
) : Serializable {
    fun setNumber(number: Int) {
        this.number += number
    }

    fun getNumber(): Int {
        return number
    }
}