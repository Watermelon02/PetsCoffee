package com.example.petscoffee.model.goods;

import com.example.petscoffee.R;

import java.io.Serializable;

public class Keys extends Goods implements Serializable {

    public Keys() {
        super("Keys",0,1,R.drawable.key,"店铺的钥匙，小心保管");
    }
}
