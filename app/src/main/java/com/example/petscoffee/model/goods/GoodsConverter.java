package com.example.petscoffee.model.goods;

import androidx.room.TypeConverter;

import com.example.petscoffee.repository.local.GsonInstance;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class GoodsConverter {
    //用于room存储接口list
    @TypeConverter
    public String objectToJson(List<Goods> object) {
        return GsonInstance.getGsonInstance().toJson(object);
    }
    @TypeConverter
    public List<Goods> jsonToObject(String json){
        Type type =  new TypeToken<List<Goods>>(){}.getType();
        return GsonInstance.getGsonInstance().fromJson(json,type);
    }
}
