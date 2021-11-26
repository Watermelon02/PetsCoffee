package com.example.petscoffee.equipment;

import java.io.Serializable;

public interface Equipment extends Serializable {
    public int addHp();//增加hp
    public int addHunger();//增加每次吃饭补充的饥饿度
    public int addLoveliness();//增加可爱度
}
