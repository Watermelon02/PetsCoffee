package com.example.petscoffee.pets;

import com.example.petscoffee.coffeeShop.CoffeeShop;

import java.util.Random;

public class Create {
    private int priority = getPriority();
    private int hp;
    private int hunger;// 饥饿度
    private int cleanliness;// 清洁度
    private int mood;// 心情
    private int loveliness;// 可爱度
    private int sp;// 特殊值
    private String name;// 名字
    public Create (CoffeeShop coffee,String name,int species){
        getPriority();//随机设置宠物属性
        if(species == 1){//如果为猫
            Cat pet = new Cat(hp, hunger, cleanliness, mood, loveliness, sp, name);
            coffee.getPets().add(pet);
        }else{
            Dog pet = new Dog(hp, hunger, cleanliness, mood, loveliness, sp, name);
            coffee.getPets().add(pet);
        }
    }

    public int getPriority(){//随机获得新宠物品质（1--10),并以此设置可爱度和sp属性
        Random random = new Random();
        int priority = random.nextInt()%11;
        this.hp = random.nextInt()%11;
        this.hunger= random.nextInt()%11;
        this.cleanliness = random.nextInt()%11;
        this.mood = random.nextInt()%11;
        while(priority <= 0){
            priority = random.nextInt()%11;
        }
        while(this.hp <= 0){
            this.hp = random.nextInt()%11;
        }
        while(this.hunger <= 0){
            this.hunger= random.nextInt()%11;
        }
        while(this.cleanliness <= 0){
            this.cleanliness = random.nextInt()%11;
        }
        while(this.mood <= 0){
            this.mood = random.nextInt()%11;
        }
        this.loveliness = priority;
        this.sp = priority;
        return priority;//也许以后可以给宠物显示界面加上品质显示？
    }
    
}

