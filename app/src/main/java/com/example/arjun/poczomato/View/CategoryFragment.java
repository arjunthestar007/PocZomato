package com.example.arjun.poczomato.View;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.arjun.poczomato.MyApiEndpointInterface;
import com.example.arjun.poczomato.R;
import com.example.arjun.poczomato.RetrofitClientInstance;
import com.example.arjun.poczomato.ActiviyListener;
import com.example.arjun.poczomato.model.categorie.Categories;
import com.example.arjun.poczomato.model.categorie.Category;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment
{

    RecyclerView recyclerView;
    List<Category> categoryList;
    public CategoryFragment() {
        // Required empty public constructor
    }

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
        MyApiEndpointInterface service = RetrofitClientInstance.getRetrofitInstance().create(MyApiEndpointInterface.class);
        service.getCategory().enqueue(new Callback<Categories>() {
            @Override
            public void onResponse(Call<Categories> call, Response<Categories> response) {
                Categories categories = response.body();
                 categoryList = categories.getCategories();
                CategoryAdapter categoryAdapter = new CategoryAdapter(getActivity(), categoryList, new RecyclerviewCallback() {
                    @Override
                    public void item(View v, int position) {
                       // Toast.makeText(getActivity(), categoryList.get(position).getCategories().getName(), Toast.LENGTH_SHORT).show();
                        ActiviyListener listener= (ActiviyListener) getActivity();
                        listener.addfragment(RestaurarantList.getInstance());
                        EventBus.getDefault().postSticky(new NotifyEvent(categoryList.get(position).getCategories().getName()));

                    }
                });
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                recyclerView.setAdapter(categoryAdapter);
            }

            @Override
            public void onFailure(Call<Categories> call, Throwable t) {

            }
        });

    }


}
