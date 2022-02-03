package com.example.petscoffee.repository.network

import com.example.petscoffee.model.CoffeeShop
import com.example.petscoffee.model.friends.Friends
import com.example.petscoffee.repository.service.coffee.*

/**
 * description ： 远程数据操作相关的api
 * author : Watermelon02
 * email : 1446157077@qq.com
 * date : 2022/2/2 16:56
 */
object CoffeeNetwork {
    suspend fun login(account:String,password:String): CoffeeShop {
        return CoffeeServiceCreator.create<LoginService>().login(account, password).await()
    }
    suspend fun register(account:String,name:String,password:String):Long{
        return CoffeeServiceCreator.create<RegisterService>().register(account, name, password).await()
    }
    suspend fun save(coffeeShop: String){
        CoffeeServiceCreator.create<SaveService>().save(coffeeShop).await<String>()//返回值为保存的账号，不需要所以就不返回了
    }
    suspend fun query(name: String):Friends{
        return CoffeeServiceCreator.create<QueryService>().queryFriend(name).await()
    }
    suspend fun singleQuery(account: String):CoffeeShop{
        return CoffeeServiceCreator.create<SingleQueryService>().singleQuery(account).await()
    }
}