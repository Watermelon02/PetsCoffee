package com.example.petscoffee.repository.network

import com.example.petscoffee.model.CoffeeShop
import com.example.petscoffee.repository.service.coffee.CoffeeServiceCreator
import com.example.petscoffee.repository.service.coffee.LoginService

/**
 * description ： suspend方法获取登录结果
 * author : Watermelon02
 * email : 1446157077@qq.com
 * date : 2022/1/27 22:22
 */
object LoginNetwork {
    suspend fun login(account:String,password:String):CoffeeShop{
        return CoffeeServiceCreator.create<LoginService>().login(account, password).await()
    }
}