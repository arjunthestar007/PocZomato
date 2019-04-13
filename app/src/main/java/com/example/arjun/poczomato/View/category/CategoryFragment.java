package com.example.arjun.poczomato.View.category;


import android.arch.persistence.room.RoomDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.arjun.poczomato.R;
import com.example.arjun.poczomato.ActiviyListener;
import com.example.arjun.poczomato.DataModel.DatamodelService;
import com.example.arjun.poczomato.View.NotifyEvent;
import com.example.arjun.poczomato.View.RecyclerviewCallback;
import com.example.arjun.poczomato.View.RestaurarantList;
import com.example.arjun.poczomato.ViewModel.Viewmodel;
import com.example.arjun.poczomato.model.Model.category.Categories;
import com.example.arjun.poczomato.model.Model.category.Category;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import io.reactivex.Observable;


public class CategoryFragment extends Fragment {

    RecyclerView recyclerView;
    Observable<Categories> categories;
    List<Category> categoryList;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    public static CategoryFragment getnstance() {
        Bundle args = new Bundle();
        CategoryFragment fragment = new CategoryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ActiviyListener listener = (ActiviyListener) getActivity();
        listener.settitle(false, "Category");
        View view = inflater.inflate(R.layout.category_fragment, container, false);
        recyclerView = view.findViewById(R.id.recycler_category);
        callCategory();
        return view;
    }


    public void callCategory() {
        Viewmodel viewmodel = new Viewmodel(new DatamodelService());
        viewmodel.getcategories().subscribe(this::setData);
        Log.d("CAtegory", "callCategory: " + categories);

    }

    private void setData(Categories categories) {
        categoryList=categories.getCategories();
//        ZomatoRoomDatabase db = ZomatoRoomDatabase.getDatabase(getActivity());
//        db.wordDao().insert(categoryList);
        CategoryAdapter categoryAdapter = new CategoryAdapter(getActivity(), categoryList, new RecyclerviewCallback() {
            @Override
            public void item(View v, int position) {
                ActiviyListener listener = (ActiviyListener) getActivity();
                listener.addfragment(RestaurarantList.getInstance());
                EventBus.getDefault().postSticky(new NotifyEvent(categoryList.get(position).getCategories().getName()));

            }
        });
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(categoryAdapter);


    }
    }
