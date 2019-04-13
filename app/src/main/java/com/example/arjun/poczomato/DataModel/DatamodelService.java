package com.example.arjun.poczomato.DataModel;

import com.example.arjun.poczomato.RetrofitClientInstance;
import com.example.arjun.poczomato.model.Model.category.Categories;
import com.example.arjun.poczomato.model.Model.search.Search;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class DatamodelService implements IDataModel {

    IDataModel service = RetrofitClientInstance.getRetrofitInstance().create(IDataModel.class);

    @Override
    public Observable<Categories> getcategories() {
        return service.getcategories().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).map(result->result);
    }

    @Override
    public Observable<Search> getRestaurant(String location,int offset) {
        return service.getRestaurant(location,3).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).map(result->result);
    }
}
