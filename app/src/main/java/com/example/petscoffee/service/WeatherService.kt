package com.example.petscoffee.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import kotlin.concurrent.thread

/**
 * description ： 用于实时改变天气数据
 * author : Watermelon02
 * email : 1446157077@qq.com
 * date : 2022/1/17 17:38
 */
class WeatherService : Service() {
    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    override fun onCreate() {
        thread {  }
    }

}