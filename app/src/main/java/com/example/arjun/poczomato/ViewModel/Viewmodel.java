package com.example.arjun.poczomato.ViewModel;

import com.example.arjun.poczomato.DataModel.IDataModel;
import com.example.arjun.poczomato.model.Model.category.Categories;
import com.example.arjun.poczomato.model.Model.search.Search;

import io.reactivex.Observable;

public class Viewmodel {

    IDataModel iDataModel;

    public Viewmodel(IDataModel iDataModel) {
        this.iDataModel = iDataModel;

    }

    public  Observable<Categories> getcategories(){
        return iDataModel.getcategories();
    }

    public  Observable<Search> getRestaurants(String location){
        return iDataModel.getRestaurant(location,3);
    }
}
