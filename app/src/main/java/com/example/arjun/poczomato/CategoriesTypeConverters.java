package com.example.arjun.poczomato;

import android.arch.persistence.room.TypeConverter;

import com.example.arjun.poczomato.model.Model.category.Category;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class CategoriesTypeConverters {
    Gson gson = new Gson();

    @TypeConverter
    public List<Category> stringToSomeObjectList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<Category>>() {}.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public String someObjectListToString(List<Category> someObjects) {
        return gson.toJson(someObjects);
    }
}
