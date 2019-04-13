package com.example.arjun.poczomato;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.arjun.poczomato.model.Model.category.Categories;
import com.example.arjun.poczomato.model.Model.category.Category;

import java.util.List;

@Dao
public interface ZomatoDao {
    @Insert
    void insert(List<Category> categoryList);
//
//    @Query("DELETE FROM categories_table")
//    void deleteAll();
//
//    @Query("SELECT * from categories_table")
//    List<Category> getAllWords();
}
