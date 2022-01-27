package com.example.petscoffee.model.pets;

import androidx.room.TypeConverter;

import com.example.petscoffee.repository.local.GsonInstance;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class PetsConverter {
    //用于room存储接口list
    @TypeConverter
    public String objectToJson(List<Pets> object) {
        return GsonInstance.getGsonInstance().toJson(object);
    }
    @TypeConverter
    public List<Pets> jsonToObject(String json){
        return GsonInstance.getGsonInstance().fromJson(json, new TypeToken<List<Pets>>(){}.getType());
    }
}
