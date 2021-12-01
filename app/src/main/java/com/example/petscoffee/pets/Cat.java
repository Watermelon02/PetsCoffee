package com.example.petscoffee.pets;

import com.example.petscoffee.R;
import com.example.petscoffee.equipment.Equipment;

public class Cat extends Pets {
    /*private int hp;
    private int hunger;// 饥饿度
    private int cleanliness;// 清洁度
    private int mood;// 心情]
    private int loveliness;// 可爱度
    private int sp;// 特殊值
    private String name;// 名字
    private int species;//物种属性,1为猫，2为狗
    private int imageId = R.drawable.cat;//图标id
    private Equipment[] equipments = new Equipment[3];*/

    public Cat(int hp, int hunger, int cleanliness, int mood, int loveliness, int sp, String name) {
        super(hp,hunger,cleanliness,mood,loveliness,sp,name,1);
        /*this.hp = hp;
        this.hunger = hunger;
        this.cleanliness = cleanliness;
        this.mood = mood;
        this.loveliness = loveliness;
        this.sp = sp;
        this.name = name;
        this.setSpecies(1);*/
    }

   /* @Override
    public int addHp() {
        int addHp = 0;
        for (int i = 0; i < 3; i++) {
            if (equipments[i] != null) {
                addHp += equipments[i].addHp();
            }
        }
        return addHp;
    }

    @Override
    public int addHunger() {
        int addHunger = 0;
        for (int i = 0; i < 3; i++) {
            if (equipments[i] != null) {
                addHunger += equipments[i].addHunger();
            }
        }
        return addHunger;
    }

    @Override
    public int addLoveliness() {
        int addLoveliness = 0;
        for (int i = 0; i < 3; i++) {
            if (equipments[i] != null) {
                addLoveliness += equipments[i].addHunger();
            }
        }
        return addLoveliness;
    }

    public float work() {
        float money = 0;// 营业所能赚到的钱
        int negative = 0;// 负面值，降低该宠物营业所能增加的money
        if (cleanliness < 4) {
            negative += 1;
        }
        if (mood < 4) {
            negative += 1;
        }
        return money = loveliness - negative;
    }



    @Override
    public int getHp() {
        return hp + addHp();
    }

    @Override
    public int getHunger() {
        return hunger + addHunger();
    }

    @Override
    public int getCleanliness() {
        return cleanliness;
    }

    @Override
    public int getMood() {
        return mood;
    }

    @Override
    public int getLoveliness() {
        return loveliness + addLoveliness();
    }

    @Override
    public int getSp() {
        return sp;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSpecies() {
        return species;
    }


    public int getImageId() {
        return imageId;
    }

    @Override
    public Equipment[] getEquipments() {
        return equipments;
    }

    @Override
    public void setHp(int num) {
        hp += num;
    }

    @Override
    public void setHunger(int num) {
        hunger += num;
    }

    @Override
    public void setCleanliness(int num) {
        cleanliness += num;
    }

    @Override
    public void setMood(int num) {
        mood += num;
    }

    @Override
    public void setLoveliness(int num) {
        loveliness += num;
    }

    @Override
    public void setSp(int num) {
        sp += num;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setSpecies(int species) {
        this.species = species;
    }*/
}
