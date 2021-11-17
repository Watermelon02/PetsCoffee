package com.example.petscoffee.pets;

import com.example.petscoffee.equipment.Equipment;

import java.io.Serializable;

public interface Pets extends Comparable<Pets>, Serializable {


    float work();

    int compareTo(Pets pet);

    int getHp();

    int getHunger();

    int getCleanliness();

    int getMood();

    int getLoveliness();

    int getSp();

    String getName();

    int getSpecies();

    int getImageId();//获取图标id
    
    Equipment[] getEquipments();//获取宠物装备
    
    int addHp();//装备的生命加成
    
    int addHunger();//装备的饱食度加成
    
    int addLoveliness();//装备的可爱度加成

    void setHp(int num);

    void setHunger(int num);

    void setCleanliness(int num);

    void setMood(int num);

    void setLoveliness(int num);

    void setSp(int num);

    void setName(String name);

    void setSpecies(int species);
}
