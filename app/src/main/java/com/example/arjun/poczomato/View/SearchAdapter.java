package com.example.arjun.poczomato.View;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.arjun.poczomato.R;
import com.example.arjun.poczomato.View.category.CategoryAdapter;
import com.example.arjun.poczomato.model.Model.search.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {
    List<Restaurant> restaurants;
    Context mContext;
    RecyclerviewCallback mRecyclerviewcallback;

    public SearchAdapter(List<Restaurant> restaurants, Context mContext,RecyclerviewCallback callback) {
        this.restaurants = restaurants;
        this.mContext = mContext;
        mRecyclerviewcallback=callback;
    }


    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View listItem = layoutInflater.inflate(R.layout.search_item, viewGroup, false);
        SearchViewHolder holder = new SearchViewHolder(listItem);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder searchViewHolder, int i) {
        searchViewHolder.searchText.setText(restaurants.get(i).getRestaurant().getName());
        searchViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRecyclerviewcallback.item(v,i);

            }
        });
    }

    public void filterList(List<Restaurant> filterdNames) {
        this.restaurants = filterdNames;
        notifyDataSetChanged();
    }

    public void clearList() {
        this.restaurants.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder {
        TextView searchText;
        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            searchText=itemView.findViewById(R.id.ressearchtext);
        }
    }
}
