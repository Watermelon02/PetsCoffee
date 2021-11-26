package com.example.petscoffee.coffeeShop;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.petscoffee.bag.Bag;
import com.example.petscoffee.pets.Pets;
import com.example.petscoffee.pets.PetsConverter;

import java.io.Serializable;
import java.util.ArrayList;

@Entity(tableName = "coffeeShop")
@TypeConverters({PetsConverter.class})
public class CoffeeShop implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int day;// 总天数
    private int time;// 一日两档期
    private float money;
    private static Bag bag;
    private static ArrayList<Pets> pets = new ArrayList<Pets>();// 宠物对象变长数组

    public CoffeeShop(){};

    public CoffeeShop(int day, int time, float money, Bag bag) {
        //用于FirstActivity中初始化
        this.day = day;
        this.time = time;
        this.money = money;
        this.bag = bag;
    }

    public void timeChange() {
        if (time < 1) {
            time++;// 时间推移
        } else {
            day++;// 总天数增加
            time = 0;
        }
    }

    public int getDay() {
        return day;
    }

    public int getTime() {
        return time;
    }

    public float getMoney() {
        return money;
    }

    public ArrayList<Pets> getPets() {
        return pets;
    }

    public Bag getBag() {
        return bag;
    }

    public void setMoney(float fee) {
        this.money += fee;
    }

    public void setTime(int time) {
        this.time += time;
    }

    public void setDay(int day) {
        this.day += day;
    }

    public void setBag(Bag bag) {
        this.bag = bag;
    }

    public static void setPets(ArrayList<Pets> pets) {
        CoffeeShop.pets = pets;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}