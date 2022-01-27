package com.example.petscoffee.model.equipments;

import com.example.petscoffee.R;
import com.example.petscoffee.model.goods.Goods;

public class Bell extends Goods /*implements Equipment*/ {
    public Bell() {
        super("Bell", 500, 0, R.drawable.bell, "Bell\n装备上每次吃饭补充的可爱度+1");
    }
}
