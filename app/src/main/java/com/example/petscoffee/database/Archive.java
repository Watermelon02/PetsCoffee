package com.example.petscoffee.database;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.petscoffee.coffeeShop.CoffeeShop;
import com.google.gson.Gson;

public class Archive {
    static Gson gson = GsonInstance.getGsonInstance();
    //持久化保存和读取方法,
    // 使用了Gson将自定义对象转为json对象(以及反序列化)，
    // 使用Gson时需将所有内部类设置为静态
    public static CoffeeShop load(Activity activity) {
        //通过Gson序列化CoffeeShop对象，变为json对象；
        SharedPreferences sp = activity.getSharedPreferences("coffee", Context.MODE_PRIVATE);
        String data = sp.getString("coffee", null);
        return gson.fromJson(data, CoffeeShop.class);
    }

    public static CoffeeShop load(Service service) {//重载该方法，以供Service来读取存档
        //通过Gson序列化CoffeeShop对象，变为json对象；
        SharedPreferences sp = service.getSharedPreferences("coffee", Context.MODE_PRIVATE);
        String data = sp.getString("coffee", null);
        return gson.fromJson(data, CoffeeShop.class);
    }

    public static void save(CoffeeShop coffee, Activity activity) {
        //通过Gson序列化CoffeeShop对象，变为json对象；
        SharedPreferences.Editor editor = activity.getSharedPreferences("coffee", Context.MODE_PRIVATE).edit();
        String data = gson.toJson(coffee);
        editor.putString("coffee", data);
        editor.apply();
        /*if (gson.fromJson(activity.getSharedPreferences("coffee",Context.MODE_PRIVATE).getString("coffee",null),CoffeeShop.class).getBag().getBag().size()>1){
            CoffeeShop test = gson.fromJson(activity.getSharedPreferences("coffee",Context.MODE_PRIVATE).getString("coffee",null),CoffeeShop.class);
            Goods test1 = test.getBag().getBag().get(1);
            Log.d("testTag","123");
        }*/

    }

    public static void save(CoffeeShop coffee, Service service) {//重载该方法，以供Service来保存存档
        //通过Gson序列化CoffeeShop对象，变为json对象；
        SharedPreferences.Editor editor = service.getSharedPreferences("coffee", Context.MODE_PRIVATE).edit();
        String data = gson.toJson(coffee);
        editor.putString("coffee", data);
        editor.apply();
    }

}
