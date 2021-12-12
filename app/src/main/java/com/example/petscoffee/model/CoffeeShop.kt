package com.example.petscoffee.coffeeShop;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.petscoffee.goods.Goods;
import com.example.petscoffee.goods.GoodsConverter;
import com.example.petscoffee.pets.Pets;
import com.example.petscoffee.pets.PetsConverter;

import java.util.ArrayList;
import java.util.List;

@TypeConverters({PetsConverter.class,GoodsConverter.class})
@Entity(tableName = "coffeeShop")
public class CoffeeShop {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int time;
    private int day;
    private float money;
    private String account;
    private String name;
    private String password;
    private List<Pets> pets = new ArrayList<>();// 宠物对象变长数组
    private List<Goods> bag = new ArrayList<>();

    public CoffeeShop(int time, int day, float money) {
        this.time = time;
        this.day = day;
        this.money = money;
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

    public List<Pets> getPets() {
        return pets;
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


    public void setPets(List<Pets> pets) {
        this.pets = pets;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Goods> getBag() {
        return bag;
    }

    public void setBag(List<Goods> bag) {
        this.bag = bag;
    }
}