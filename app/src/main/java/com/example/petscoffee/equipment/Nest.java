package com.example.petscoffee.equipment;

import com.example.petscoffee.R;
import com.example.petscoffee.goods.Goods;

public class Nest extends Goods /*implements Equipment*/ {
    /*private int hp;
    private int hunger;
    private int loveliness;
    private int imageId;
    private String name;
    private float price;
    private int number;
    private String info;*/

    public Nest(){
        super("nest",500,0,R.drawable.nest,"宠物的窝，装备上宠物的生命值上限+2");
    }

}
