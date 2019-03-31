package com.example.arjun.poczomato.View.categorymodel;

import com.example.arjun.poczomato.RetrofitClientInstance;
import com.example.arjun.poczomato.model.categorie.Categories;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class Datamodel implements IDataModel {


    @Override
    public Observable<Categories> getcategories() {
        IDataModel service = RetrofitClientInstance.getRetrofitInstance().create(IDataModel.class);
        return service.getcategories().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).map(result->result);
    }

}
