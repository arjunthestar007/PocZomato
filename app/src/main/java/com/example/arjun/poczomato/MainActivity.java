package com.example.arjun.poczomato;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.arjun.poczomato.model.categorie.Categories;
import com.example.arjun.poczomato.model.categorie.Category;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    //8b4934620d60843ab88a5ed9f6199ab0
    private static final String TAG = "Category";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyApiEndpointInterface service = RetrofitClientInstance.getRetrofitInstance().create(MyApiEndpointInterface.class);
        Call<Categories> call = service.getCategory();
        call.enqueue(new Callback<Categories>() {
            @Override
            public void onResponse(Call<Categories> call, Response<Categories> response) {
                Categories categories=response.body();
                categories.getCategories();
                Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<Categories> call, Throwable t) {

            }
        });
    }
}
