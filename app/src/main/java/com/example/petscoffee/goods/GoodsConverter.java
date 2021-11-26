package com.example.petscoffee.goods;

import androidx.room.TypeConverter;

import com.example.petscoffee.database.GsonInstance;
import com.example.petscoffee.pets.Pets;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class GoodsConverter {
    //用于room存储接口list
    @TypeConverter
    public String objectToJson(List<? extends Goods> object) {
        return GsonInstance.getGsonInstance().toJson(object);
    }
    @TypeConverter
    public List<Goods> jsonToObject(String json){
        Type type =  new TypeToken<List<Goods>>(){}.getType();
        return GsonInstance.getGsonInstance().fromJson(json,type);
    }
}
