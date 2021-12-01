package com.example.petscoffee.database;

import android.content.Context;
import android.util.Log;

import com.example.petscoffee.coffeeShop.CoffeeShop;
import com.google.gson.Gson;

import java.util.List;

public class Archive {
    private static Gson gson = GsonInstance.getGsonInstance();
    private static int id;//登录的用户id

    //持久化保存和读取方法,
    // 通过room数据库进行异步存储和读取，
    public static void loadCoffee(Context context, LoadListener_CoffeeShop loadListener) {
        //通过数据库读取数据时，需要通过回调来返回读取到的数据和进行接下来的操作
        new Thread(() -> {
            try {
                CoffeeShop coffeeShop = CoffeeDatabase.getInstance(context).coffeeShopDao().queryCoffee(id);
                loadListener.complete(coffeeShop);
                List<CoffeeShop> coffeeShops = CoffeeDatabase.getInstance(context).coffeeShopDao().queryCoffees();
                Log.d("testTag", "saveCoffee: ");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void loadCoffee(Context context,String account,LoadListener_CoffeeShop loadListener) {
        //通过数据库读取数据时，需要通过回调来返回读取到的数据和进行接下来的操作
        new Thread(() -> {
            try {
                CoffeeShop coffeeShop = CoffeeDatabase.getInstance(context).coffeeShopDao().queryCoffee(account);
                loadListener.complete(coffeeShop);
                List<CoffeeShop> coffeeShops = CoffeeDatabase.getInstance(context).coffeeShopDao().queryCoffees();
                Log.d("testTag", "saveCoffee: ");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void saveCoffee(CoffeeShop coffee, Context context) {
        new Thread(() -> {
            if (CoffeeDatabase.getInstance(context).coffeeShopDao().queryCoffee(id) == null) {
                //如果存在该数据则update
                try {
                    CoffeeDatabase.getInstance(context).coffeeShopDao().insertCoffee(coffee);
                    List<CoffeeShop> coffeeShops = CoffeeDatabase.getInstance(context).coffeeShopDao().queryCoffees();
                    Log.d("testTag", "saveCoffee: ");
                }catch (Exception e){
                    e.printStackTrace();
                }
            } else {//否则insert
                try {
                    CoffeeDatabase.getInstance(context).coffeeShopDao().upDateCoffee(coffee);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }


    public interface LoadListener_CoffeeShop {//loadCoffee回调接口

        void complete(CoffeeShop coffeeShop);
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Archive.id = id;
    }
}
