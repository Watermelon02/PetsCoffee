package com.example.petscoffee.model.equipments;

import com.example.petscoffee.R;
import com.example.petscoffee.model.goods.Goods;

public class Nest extends Goods /*implements Equipment*/ {
    public Nest(){
        super("nest",500,0,R.drawable.nest,"宠物的窝，装备上宠物的生命值上限+2");
    }

}
