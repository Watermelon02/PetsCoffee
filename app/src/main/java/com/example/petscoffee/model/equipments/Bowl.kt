package com.example.petscoffee.model.equipments;

import com.example.petscoffee.R;
import com.example.petscoffee.model.goods.Goods;

public class Bowl extends Goods /*implements Equipment */{


    public Bowl(){
        super("Bowl",500,0,R.drawable.bowl,"Bowl\n装备上每次吃饭补充的饱食度+1");
    }

}
