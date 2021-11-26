package com.example.petscoffee.goods;

import com.example.petscoffee.R;

import java.io.Serializable;

public class Keys implements Goods, Serializable {
    private String name;
    private float price;
    private int number;
    private int imageId;
    private String info;

    public Keys() {
        this.name = "Keys";
        this.price = 0;
        this.number = 1;
        this.imageId = R.drawable.key;
        this.info = "店铺的钥匙，小心保管";
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
