package com.example.petscoffee.equipment;

import com.example.petscoffee.R;
import com.example.petscoffee.goods.Goods;

public class Bowl implements Equipment,Goods {
    private int hp;
    private int hunger;
    private int loveliness;
    private int imageId;
    private String name;
    private float price;
    private int number;
    private String info;

    public Bowl(){
        this.hp =0;
        this.hunger =1;
        this.loveliness = 0;
        this.name ="Bowl";
        this.price = 500;
        this.number = 0;
        imageId = R.drawable.bowl;
        this.info = "宠物的碗，装备上每次吃饭补充的饱食度+1";
    }

    @Override
    public int addHp() {
        return hp;
    }

    @Override
    public int addHunger() {
        return hunger;
    }

    @Override
    public int addLoveliness() {
        return loveliness;
    }

    @Override
    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String getInfo() {
        return info;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public float getPrice() {
        return price;
    }

    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public int getImageId() {
        return imageId;
    }
}
