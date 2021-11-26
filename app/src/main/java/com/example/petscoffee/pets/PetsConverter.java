package com.example.petscoffee.pets;

import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.example.petscoffee.database.GsonInstance;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class PetsConverter {
    //用于room存储接口list
    @TypeConverter
    public String objectToJson(List<? extends Pets> object) {
        return GsonInstance.getGsonInstance().toJson(object);
    }
    @TypeConverter
    public List<Pets> jsonToObject(String json){
        return GsonInstance.getGsonInstance().fromJson(json, new TypeToken<List<Pets>>(){}.getType());
    }
}
