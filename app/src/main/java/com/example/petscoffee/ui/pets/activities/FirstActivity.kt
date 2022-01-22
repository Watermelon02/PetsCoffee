package com.example.petscoffee.ui.pets.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.petscoffee.R
import com.example.petscoffee.adapters.MsgAdapter
import com.example.petscoffee.databinding.ActivityFirstBinding
import com.example.petscoffee.model.CoffeeShop
import com.example.petscoffee.model.Message
import com.example.petscoffee.model.goods.Keys
import com.example.petscoffee.model.pets.Cat
import com.example.petscoffee.repository.local.Archive
import java.util.*

class FirstActivity : AppCompatActivity() {
    private lateinit var mActivityPageBinding:ActivityFirstBinding
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityPageBinding = ActivityFirstBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_first)
        recyclerView = mActivityPageBinding.firstRecycler
        //绑定按钮
        initMessage() //初始化消息数组
        //进行存档判断
        Archive.loadCoffee(this) { coffeeShop: CoffeeShop ->
            if (coffeeShop.bag.size == 0 || coffeeShop.pets.size == 0) { //如果该用户还没有初始化CoffeeShop或强制关闭导致存档损坏，则新开游戏
                newGame(coffeeShop)
            } else {
                continueGame()
            }
        }
    }

    private fun newGame(coffeeShop: CoffeeShop) {
        val messageAdapter = MsgAdapter(msgArray)
        val layoutManager: LayoutManager = LinearLayoutManager(this)
        recyclerView.adapter = messageAdapter
        recyclerView.layoutManager = layoutManager //设置recyclerView
        //以下为默认第一只宠物属性设置
        val firstPet = Cat(10, 10, 8, 10, 8, 8, "default")
        mActivityPageBinding.firstButton.setOnClickListener {
            val name = mActivityPageBinding.firstEditText.text.toString()
            val message = Message("我的第一只猫猫的名字是———$name", Message.RIGHT_MSG)
            coffeeShop.bag.add(Keys())
            firstPet.name = name
            coffeeShop.pets.add(firstPet)
            Archive.saveCoffee(coffeeShop, this@FirstActivity) //先保存一个存档，为后面打开商店读档做准备
            msgArray.add(message) //输出用户输入的名字
            msgArray.add(Message("那么，开始工作吧！", Message.LEFT_MSG))
            messageAdapter.notifyItemInserted(msgArray.size - 1) //有新消息时，刷新recyclerView中的显示
            Timer().schedule(object : TimerTask() {
                //在跳转到主界面后，结束该活动
                override fun run() {
                    val intent = Intent(this@FirstActivity, MainPageActivity::class.java)
                    startActivity(intent)
                }
            }, 2000)
        }
    }

    private fun continueGame() {
        val intent = Intent(this@FirstActivity, MainPageActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun initMessage() { //初始化消息数组
        val m1 = Message("欢迎来到你的宠物咖啡馆！", Message.LEFT_MSG)
        val m2 = Message("现在，", Message.LEFT_MSG)
        val m3 = Message("为你的第一只猫猫起一个名字吧", Message.LEFT_MSG)
        msgArray.add(m1)
        msgArray.add(m2)
        msgArray.add(m3)
    }

    companion object {
        val msgArray = ArrayList<Message>() //游戏开始消息数组,通过initMessage初始化
    }
}