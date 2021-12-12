package com.example.petscoffee.model

import androidx.room.Entity
import androidx.room.TypeConverters
import com.example.petscoffee.model.pets.PetsConverter
import androidx.room.PrimaryKey
import com.example.petscoffee.model.goods.Goods
import com.example.petscoffee.model.goods.GoodsConverter
import com.example.petscoffee.model.pets.Pets
import java.util.ArrayList
import java.lang.StringBuilder

@TypeConverters(PetsConverter::class, GoodsConverter::class)
@Entity(tableName = "coffeeShop")
class CoffeeShop(
    var time: Int,
    var day: Int,
    var money: Float,
    val account: String,
    var name: String,
    var password: String,
    val pets: MutableList<Pets>, // 宠物对象变长数组
    val bag: MutableList<Goods>, @PrimaryKey(autoGenerate = true)
    var id: Int = 0
) {
    constructor(
        time: Int,
        day: Int,
        money: Float,
        account: String,
        name: String,
        password: String
    ) : this(time, day, money, account, name, password, ArrayList<Pets>(), ArrayList<Goods>())

    fun timeChange() {
        if (time < 1) {
            time++ // 时间推移
        } else {
            day++ // 总天数增加
            time = 0
        }
    }

    fun showTime():String {//为databinding展示时间
        return StringBuilder().run {
            append("第")
            append(day)
            append("天 的 ")
            append(if (time == 0) "上午" else "下午")
            toString()
        }
    }
}

