package com.example.petscoffee.listener

import android.content.Context
import android.view.View
import com.example.petscoffee.R
import android.content.Intent
import com.example.petscoffee.ui.pets.activities.BagActivity
import com.example.petscoffee.ui.pets.activities.ShopActivity
import com.example.petscoffee.ui.pets.activities.PetsActivity
import com.example.petscoffee.ui.pets.activities.MainPageActivity

class BottomBarListener(  //底部toolbar的点击监听
    private val context: Context
) {
    fun onClick(view: View) {
        when (view.id) {
            R.id.bottomBar_bag -> {
                val bagIntent = Intent(context, BagActivity::class.java)
                context.startActivity(bagIntent)
            }
            R.id.bottomBar_shop -> {
                val shopIntent = Intent(context, ShopActivity::class.java)
                context.startActivity(shopIntent)
            }
            R.id.bottomBar_pets -> {
                val petsIntent = Intent(context, PetsActivity::class.java)
                context.startActivity(petsIntent)
            }
            R.id.bottomBar_wash -> {
                val homeIntent = Intent(context, MainPageActivity::class.java)
                context.startActivity(homeIntent)
            }
        }
    }
}