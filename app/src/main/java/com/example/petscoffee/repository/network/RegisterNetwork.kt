package com.example.petscoffee.repository.network

import com.example.petscoffee.repository.service.coffee.CoffeeServiceCreator
import com.example.petscoffee.repository.service.coffee.RegisterService

/**
 * description ： suspend方法获取注册新用户返回的id
 * author : Watermelon02
 * email : 1446157077@qq.com
 * date : 2022/1/27 15:28
 */
object RegisterNetwork {
    private val registerService = CoffeeServiceCreator.create<RegisterService>()
    suspend fun register(account:String,name:String,password:String):Long{
        return registerService.register(account, name, password).await()
    }
}