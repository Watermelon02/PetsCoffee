package com.example.petscoffee.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petscoffee.R;
import com.example.petscoffee.adapters.MessageAdapter;
import com.example.petscoffee.coffeeShop.CoffeeShop;
import com.example.petscoffee.database.Archive;
import com.example.petscoffee.goods.Foods;
import com.example.petscoffee.goods.Keys;
import com.example.petscoffee.message.Message;
import com.example.petscoffee.message.MsgType;
import com.example.petscoffee.pets.Cat;
import com.example.petscoffee.pets.Pets;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class FirstActivity extends AppCompatActivity {
    private static ArrayList<Message> msgArray = new ArrayList<>();//游戏开始消息数组,通过initMessage初始化
    private RecyclerView recyclerView;
    private Button button;
    private EditText editText;
    private CoffeeShop coffee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        button = findViewById(R.id.first_button_send);
        editText = findViewById(R.id.first_editText);
        recyclerView = findViewById(R.id.first_recycler);
        //绑定按钮
        initMessage();//初始化消息数组
        //进行存档判断
        Archive.loadCoffee(this, coffeeShop -> {
            if (coffeeShop.getBag().size() == 0 || coffeeShop.getPets().size() == 0) {//如果该用户还没有初始化CoffeeShop或强制关闭导致存档损坏，则新开游戏
                coffee = coffeeShop;
                newGame();
            } else {
                continueGame();
            }
        });
    }

    public void newGame() {
        MessageAdapter messageAdapter = new MessageAdapter(msgArray);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(messageAdapter);
        recyclerView.setLayoutManager(layoutManager);//设置recyclerView
        //以下为默认第一只宠物属性设置
        Pets firstPet = new Cat(10, 10, 8, 10, 8, 8, "default");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText.getText().toString();
                Message message = new Message("我的第一只猫猫的名字是———" + name, MsgType.RIGHT);
                coffee.getPets().add(firstPet);
                coffee.getBag().add(new Keys());
                messageAdapter.setCoffee(coffee);
                messageAdapter.setContext(FirstActivity.this);
                firstPet.setName(name);
                Archive.saveCoffee(coffee, FirstActivity.this);//先保存一个存档，为后面打开商店读档做准备
                msgArray.add(message);//输出用户输入的名字
                msgArray.add(new Message("那么，开始工作吧！", MsgType.LEFT));
                messageAdapter.notifyItemInserted(msgArray.size() - 1);//有新消息时，刷新recyclerView中的显示
                new Timer().schedule(new TimerTask() {//在跳转到主界面后，结束该活动
                    @Override
                    public void run() {
                        finish();
                    }
                }, 1500);
            }
        });
    }

    public void continueGame() {
        Intent intent = new Intent(FirstActivity.this, CoffeeShopActivity.class);
        startActivity(intent);
        finish();
    }

    public void initMessage() {//初始化消息数组
        Message m1 = new Message("欢迎来到你的宠物咖啡馆！", MsgType.LEFT);
        Message m2 = new Message("现在，", MsgType.LEFT);
        Message m3 = new Message("为你的第一只猫猫起一个名字吧", MsgType.LEFT);
        msgArray.add(m1);
        msgArray.add(m2);
        msgArray.add(m3);
    }
}