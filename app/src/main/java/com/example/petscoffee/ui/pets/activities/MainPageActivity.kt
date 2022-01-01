package com.example.petscoffee.ui.pets.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.petscoffee.R
import com.example.petscoffee.databinding.ActivityMainBinding
import com.example.petscoffee.listener.BottomBarListener
import com.example.petscoffee.ui.pets.viewModel.MainPageViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.user_nav_header.*
import java.io.File
import java.io.FileNotFoundException

class MainPageActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //加载头像
        val file = File("/data/data/com.example.petscoffee/userHead.jpg")
        if (file.exists()) {
            try {
                user_nav_header_image.setImageBitmap(
                    BitmapFactory.decodeStream(
                        contentResolver.openInputStream(
                            FileProvider.getUriForFile(
                                this,
                                "com.example.petscoffee.ui.activities.PictureActivity",
                                file
                            )
                        )
                    )
                )
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }
        }
        val viewModel = ViewModelProvider(this).get(
            MainPageViewModel::class.java
        )
        val mainPageBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main) as ActivityMainBinding
        mainPageBinding.bottomBarListener = BottomBarListener(this)//为底部栏设置点击监听
        viewModel.coffeeShop.observe(this, { coffee ->
            mainPageBinding.coffeeShop = coffee
        })
        //查询天气监听
        edit_main_query.addTextChangedListener { text ->
            viewModel.queryWeather(text.toString())
        }
        viewModel.weather.observe(this, { result ->
            val weatherData = result.getOrNull()
            if (weatherData != null) {
                text_main_address.text = weatherData.address
                text_main_temp.text = weatherData.temp
                text_main_weather.text = weatherData.weather
                text_main_windDirection.text = weatherData.windDirection
                text_main_windPower.text = weatherData.windPower
            } else {
                text_main_address.text = null
                text_main_temp.text = null
                text_main_weather.text = null
                text_main_windDirection.text = null
                text_main_windPower.text = null
            }
        })
        Glide.with(this).load(R.drawable.mainpage_background_1).into(mainPage_background)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) { //头像设置界面返回
            try { //重新设置userHeader
                user_nav_header_image.setImageBitmap(
                    BitmapFactory.decodeStream(
                        contentResolver.openInputStream(
                            FileProvider.getUriForFile(
                                this,
                                "com.example.petscoffee.ui.activities.PictureActivity",
                                File("/data/data/com.example.petscoffee/userHead.jpg")
                            )
                        )
                    )
                )
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }
        }
    }
}