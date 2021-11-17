package com.example.petscoffee.equipment;

import com.example.petscoffee.R;
import com.example.petscoffee.goods.Goods;

public class Nest implements Goods,Equipment {
    private int hp;
    private int hunger;
    private int loveliness;
    private int imageId;
    private String name;
    private float price;
    private int number;
    private String info;

    public Nest(){
        this.hp =2;
        this.hunger =0;
        this.loveliness = 0;
        this.name ="nest";
        this.price = 500;
        this.number = 0;
        imageId = R.drawable.nest;
        this.info = "宠物的窝，装备上宠物的生命值上限+2";
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
