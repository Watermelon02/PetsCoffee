package com.example.petscoffee.ui.pets.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.petscoffee.model.CoffeeShop
import com.example.petscoffee.repository.local.Archive
import com.example.petscoffee.repository.local.CoffeeDatabase

/**
 * description ： WasherDialogFragment对应的viewModel,主要用于获取/保存宠物cleanliness属性
 * author : Watermelon02
 * email : 1446157077@qq.com
 * date : 2022/1/24 16:02
 */
class WasherViewModel : ViewModel() {
    fun getCoffee():LiveData<CoffeeShop>{
        return CoffeeDatabase.getInstance().coffeeShopDao().queryCoffeeLiveData(Archive.id)
    }
    fun saveCoffee(coffeeShop: CoffeeShop){
        Thread{CoffeeDatabase.getInstance().coffeeShopDao().upDateCoffee(coffeeShop)}.start()
    }
}