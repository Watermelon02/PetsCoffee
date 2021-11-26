package com.example.petscoffee.database;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class InterfaceAdapter<T> implements JsonSerializer<T>, JsonDeserializer<T> {
    //用于序列化接口列表（List<Goods>,List<Pets>的适配器）
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public JsonElement serialize(T src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject wrapper = new JsonObject();
        wrapper.addProperty("type",src.getClass().getTypeName());
        wrapper.add("data",context.serialize(src));
        return null;
    }
    @Override
    public T deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject wrapper = (JsonObject) json;
        JsonElement data = wrapper.get("data");
        String typeName = wrapper.get("type").getAsString();
        Type type = null;
        try {
            type = Class.forName(typeName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return context.deserialize(data,type);
    }
}
