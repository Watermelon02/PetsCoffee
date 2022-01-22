package com.example.petscoffee.ui.pets.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.petscoffee.R
import com.example.petscoffee.customview.AvatarView
import com.example.petscoffee.customview.PlayablePetView
import com.example.petscoffee.databinding.ActivityMainBinding
import com.example.petscoffee.listener.BottomBarListener
import com.example.petscoffee.service.WorkService
import com.example.petscoffee.ui.pets.viewModel.MainPageViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileNotFoundException

class MainPageActivity : AppCompatActivity() {
    private lateinit var userHeaderLauncherCallback: ActivityResultCallback<ActivityResult>
    private lateinit var viewModel: MainPageViewModel
    private lateinit var mainPageBinding: ActivityMainBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainPageBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main) as ActivityMainBinding
        initUserHeader()
        mainPageBinding.bottomBarListener = BottomBarListener(this)//为底部栏设置点击监听
        mainPageBinding.mainPageTest.bottomBarWork.setWork {//为工作按钮设置监听器
            val workIntent = Intent(
                this,
                WorkService::class.java
            )
            startService(workIntent)
            workStart()
        }
        viewModel = ViewModelProvider(this).get(
            MainPageViewModel::class.java
        )
        GlobalScope.launch { viewModel.queryWeather() }
        viewModel.coffeeShop.observe(this, { coffee ->
            mainPageBinding.coffeeShop = coffee
        })
        //查询天气监听
        viewModel.weather.observe(this, { result ->
            val weatherData = result?.getOrNull()
            if (weatherData != null) {
                mainPageBinding.textMainWeather.text = weatherData.weather
            } else {
                mainPageBinding.textMainWeather.text = null
            }
        })
    }

    private fun initUserHeader() {
        val userHeaderImage = mainPageBinding.mainPageNavigationView.inflateHeaderView(R.layout.user_nav_header)
            .findViewById(R.id.user_nav_header_image) as ImageView
        //加载头像
        val file = File("/data/data/com.example.petscoffee/userHead.jpg")
        if (file.exists()) {
            try {
                val avatar = AvatarView(
                    BitmapFactory.decodeStream(
                        contentResolver.openInputStream(
                            FileProvider.getUriForFile(
                                this,
                                "com.example.petscoffee.ui.pets.activities.PictureActivity",
                                file
                            )
                        )
                    )
                )
                userHeaderImage.setImageDrawable(avatar)
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }
        }
        userHeaderLauncherCallback = ActivityResultCallback<ActivityResult> {
            //头像设置界面返回
            try { //重新设置userHeader
                userHeaderImage.setImageBitmap(
                    BitmapFactory.decodeStream(
                        contentResolver.openInputStream(
                            FileProvider.getUriForFile(
                                this,
                                "com.example.petscoffee.ui.pets.activities.PictureActivity",
                                File("/data/data/com.example.petscoffee/userHead.jpg")
                            )
                        )
                    )
                )
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }
        }
        val resultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
            userHeaderLauncherCallback
        )
        userHeaderImage.setOnClickListener {
            resultLauncher.launch(
                Intent(this, PictureActivity::class.java)
            )
        }
    }

    private fun workStart() {//生成移动的宠物
        viewModel.coffeeShop.observe(this, {
            val petsViews = ArrayList<PlayablePetView>()
            for (pet in it.pets) {
                val petView = PlayablePetView(this, null)
                petView.setImageResource(pet.imageId)
                mainPageBinding.mainPageCounter.addView(petView, ViewGroup.LayoutParams(128, 128))
                petsViews.add(petView)
                petView.drop()
                petView.move()
            }
        })
    }
}