package com.example.arjun.poczomato.View.categorymodel;

import com.example.arjun.poczomato.model.categorie.Categories;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface IDataModel {

    @GET("categories")
    Observable<Categories> getcategories();
    
}
