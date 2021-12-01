package com.example.petscoffee.database;

import com.example.petscoffee.goods.Goods;
import com.example.petscoffee.pets.Pets;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
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
            gson = gsonBuilder.create();
        }
        return gson;
    }
}
