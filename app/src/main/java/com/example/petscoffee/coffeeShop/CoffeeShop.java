package com.example.petscoffee.coffeeShop;

import com.example.petscoffee.bag.Bag;
import com.example.petscoffee.pets.Pets;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class CoffeeShop implements Serializable {
    private int day;// 总天数
    private int time;// 一日两档期
    private float money;
    private static Bag bag;
    private static ArrayList<Pets> pets = new ArrayList<Pets>();// 宠物对象变长数组

    public CoffeeShop(int day,int time,float money,Bag bag){
        this.day = day;
        this.time = time;
        this.money = money;
        this.bag = bag;
    }



    public void timeChange(){
        if (time < 1) {
            time++;// 时间推移
        } else {
            day++;// 总天数增加
            time = 0;
        }
    }

    public synchronized int getDay() {
        return day;
    }

    public synchronized int getTime() {
        return time;
    }

    public synchronized float getMoney() {
        return money;
    }

    public synchronized ArrayList<Pets> getPets(){
        return pets;
    }

    public Bag getBag() {
        return bag;
    }

    public synchronized void setMoney(float fee){
        this.money += fee;
    }

    public synchronized void setTime(int time){
        this.time += time;
    }

    public synchronized void setDay(int day){
        this.day += day;
    }

    public synchronized void setBag(Bag bag){this.bag = bag;}
}