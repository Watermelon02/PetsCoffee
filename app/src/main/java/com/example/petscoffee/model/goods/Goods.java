package com.example.petscoffee.model.goods;

import com.example.petscoffee.R;

import java.io.Serializable;

public class Goods implements Serializable {//定义商品模板
    private String name;
    private float price;
    private int number;
    private int imageId;
    private String info;
    public Goods(String name,float price,int number,int imageId,String info){
        this.name = name;
        this.price = price;
        this.number = number;
        this.imageId = imageId;
        this.info = info;
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


