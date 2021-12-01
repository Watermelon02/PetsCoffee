package com.example.petscoffee.goods;

import com.example.petscoffee.R;

import java.io.Serializable;

public class Foods extends Goods implements Serializable {

    public Foods(){
        super("Foods",5f,0,R.drawable.food,"食物，宠物每天会吃饭消耗食物并补充饥饿度");

    }

}