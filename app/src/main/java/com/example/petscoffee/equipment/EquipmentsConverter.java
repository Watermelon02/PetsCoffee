package com.example.petscoffee.equipment;

import androidx.room.TypeConverter;

import com.example.petscoffee.database.GsonInstance;
import com.example.petscoffee.goods.Goods;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class EquipmentsConverter {
        //用于room存储接口list
        @TypeConverter
        public String objectToJson(List<Equipment> object) {
            return GsonInstance.getGsonInstance().toJson(object);
        }
        @TypeConverter
        public List<Equipment> jsonToObject(String json){
            Type type =  new TypeToken<List<Equipment>>(){}.getType();
            return GsonInstance.getGsonInstance().fromJson(json,type);
        }
}
