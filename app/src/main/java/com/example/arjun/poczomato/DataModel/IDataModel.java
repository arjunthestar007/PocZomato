package com.example.arjun.poczomato.DataModel;

import com.example.arjun.poczomato.model.Model.category.Categories;
import com.example.arjun.poczomato.model.Model.search.Search;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IDataModel {

    @GET("categories")
    Observable<Categories> getcategories();

    @GET("search")
    Observable<Search> getRestaurant(@Query("q") String place,@Query("start") int offset);
}
