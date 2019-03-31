package com.example.arjun.poczomato.View.categoryviewmodel;

import com.example.arjun.poczomato.View.categorymodel.IDataModel;
import com.example.arjun.poczomato.model.categorie.Categories;

import io.reactivex.Observable;

public class Viewmodel {

    IDataModel iDataModel;

    public Viewmodel(IDataModel iDataModel) {
        this.iDataModel = iDataModel;

    }

    public  Observable<Categories> getcategories(){
        return iDataModel.getcategories();
    }


}
