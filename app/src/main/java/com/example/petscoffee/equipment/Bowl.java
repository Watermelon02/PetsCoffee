package com.example.petscoffee.equipment;

import com.example.petscoffee.R;
import com.example.petscoffee.goods.Goods;

public class Bowl extends Goods /*implements Equipment */{


    public Bowl(){
        super("Bowl",500,0,R.drawable.bowl,"宠物的碗，装备上每次吃饭补充的饱食度+1");
    }

}
