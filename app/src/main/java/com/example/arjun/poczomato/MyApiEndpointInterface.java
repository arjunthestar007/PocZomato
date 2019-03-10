package com.example.arjun.poczomato;

import com.example.arjun.poczomato.model.categorie.Categories;
import com.example.arjun.poczomato.model.categorie.Category;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MyApiEndpointInterface {

    @GET("categories")
    Call<Categories> getCategory();

}
