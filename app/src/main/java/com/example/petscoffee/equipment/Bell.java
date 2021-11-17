package com.example.petscoffee.equipment;

import com.example.petscoffee.R;
import com.example.petscoffee.goods.Goods;

public class Bell implements Equipment, Goods {
    private int hp;
    private int hunger;
    private int loveliness;
    private int imageId;
    private String name;
    private float price;
    private int number;
    private String info;

    public Bell(){
        this.hp =0;
        this.hunger =0;
        this.loveliness = 1;
        this.name ="Bell";
        this.price = 1000;
        this.number = 0;
        this.imageId = R.drawable.bell;
        this.info = "铃铛，装备上宠物的可爱度+1";
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
