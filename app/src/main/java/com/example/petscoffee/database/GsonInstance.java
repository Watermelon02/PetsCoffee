package com.example.petscoffee.database;

import com.example.petscoffee.goods.Goods;
import com.example.petscoffee.pets.Pets;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class GsonInstance {
    //单例程gson，注册了InterfaceAdapter,用于序列化CoffeeShop对象
    private static GsonInstance gsonInstance;
    private static Gson gson;

    public static Gson getGsonInstance() {
        if (gsonInstance == null) {
            gsonInstance = new GsonInstance();
        }
        return getGson();
    }

    private static Gson getGson() {
        if (gson == null) {
            GsonBuilder gsonBuilder = new GsonBuilder();
            //注册适配器，因为getType()是protect方法，所以要加{}
            gsonBuilder.registerTypeAdapter(new TypeToken<List<? extends Goods>>() {
            }.getType(), new InterfaceAdapter<List<? extends Goods>>());
            gsonBuilder.registerTypeAdapter(new TypeToken<List<? extends Pets>>() {
            }.getType(), new InterfaceAdapter<List<? extends Goods>>());
            gson = gsonBuilder.create();
        }
        return gson;
    }
}
