package com.example.petscoffee.pets;

import com.example.petscoffee.R;
import com.example.petscoffee.equipment.Equipment;

import java.io.Serializable;

public class Pets implements Serializable {
    private int hp;
    private int hunger;// 饥饿度
    private int cleanliness;// 清洁度
    private int mood;// 心情]
    private int loveliness;// 可爱度
    private int sp;// 特殊值
    private String name;// 名字
    private int species;//物种属性,1为猫，2为狗
    private int imageId;//图标id
    private Equipment[] equipments = new Equipment[3];

    public Pets(int hp, int hunger, int cleanliness, int mood, int loveliness, int sp, String name,int species) {
        this.hp = hp;
        this.hunger = hunger;
        this.cleanliness = cleanliness;
        this.mood = mood;
        this.loveliness = loveliness;
        this.sp = sp;
        this.name = name;
        this.setSpecies(species);
    }

    public float work() {
        float money = 0;// 营业所能赚到的钱
        int negative = 0;// 负面值，降低该宠物营业所能增加的money
        if (getCleanliness() < 4) {
            negative += 1;
        }
        if (getMood() < 4) {
            negative += 1;
        }
        return money = loveliness - negative;
    }


    public int addHp() {
        int addHp = 0;
        for (int i = 0; i < 3; i++) {
            if (equipments[i] != null) {
                addHp += equipments[i].addHp();
            }
        }
        return addHp;
    }


    public int addHunger() {
        int addHunger = 0;
        for (int i = 0; i < 3; i++) {
            if (equipments[i] != null) {
                addHunger += equipments[i].addHunger();
            }
        }
        return addHunger;
    }


    public int addLoveliness() {
        int addLoveliness = 0;
        for (int i = 0; i < 3; i++) {
            if (equipments[i] != null) {
                addLoveliness += equipments[i].addHunger();
            }
        }
        return addLoveliness;
    }


    public int getHp() {
        return hp + addHp();
    }


    public int getHunger() {
        return hunger + addHunger();
    }


    public int getCleanliness() {
        return cleanliness;
    }


    public int getMood() {
        return mood;
    }


    public int getLoveliness() {
        return loveliness + addLoveliness();
    }


    public int getSp() {
        return sp;
    }


    public String getName() {
        return name;
    }


    public int getSpecies() {
        return species;
    }


    public int getImageId() {
        return imageId;
    }


    public Equipment[] getEquipments() {
        return equipments;
    }


    public void setHp(int num) {
        hp += num;
    }


    public void setHunger(int num) {
        hunger += num;
    }


    public void setCleanliness(int num) {
        cleanliness += num;
    }


    public void setMood(int num) {
        mood += num;
    }


    public void setLoveliness(int num) {
        loveliness += num;
    }


    public void setSp(int num) {
        sp += num;
    }


    public void setName(String name) {
        this.name = name;
    }


    public void setSpecies(int species) {
        this.species = species;
        this.imageId = (species==1?R.drawable.cat:R.drawable.dog);
    }
}
