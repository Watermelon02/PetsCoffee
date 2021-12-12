package com.example.petscoffee.model.equipments;

import com.example.petscoffee.R;
import com.example.petscoffee.model.goods.Goods;

public class Bell extends Goods /*implements Equipment*/ {
    public Bell() {
        super("Bell", 500, 0, R.drawable.bell, "宠物的铃铛，装备上每次吃饭补充的可爱度+1");
    }
}
