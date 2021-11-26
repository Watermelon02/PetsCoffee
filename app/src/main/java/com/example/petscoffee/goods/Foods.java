package com.example.petscoffee.goods;

import com.example.petscoffee.R;

import java.io.Serializable;

public class Foods implements Goods, Serializable {
    private String name;
    private float price;
    private int number;
    private int imageId;
    private String info;

    public Foods(){
        this.name = "Foods";
        this.price = 5;
        this.number = 0;
        this.imageId = R.drawable.food;
        this.info = "食物，宠物每天会吃饭消耗食物并补充饥饿度";
    }

    public String getInfo() {
        return info;
    }

    public void setNumber(int number) {
        this.number += number;
    }

    public String getName() {
        return this.name;
    }

    public float getPrice() {
        return this.price;
    }

    public int getNumber() {
        return this.number;
    }

    public int getImageId() {
        return imageId;
    }
}