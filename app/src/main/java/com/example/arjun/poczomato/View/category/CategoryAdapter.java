package com.example.arjun.poczomato.View.category;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.arjun.poczomato.R;
import com.example.arjun.poczomato.View.RecyclerviewCallback;
import com.example.arjun.poczomato.model.Model.category.Category;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyHolder> {

    List<Category> categories;
    Context mContext;
    RecyclerviewCallback mcallback;


    public CategoryAdapter(Context mContext,List<Category> categories, RecyclerviewCallback callback) {
        this.categories = categories;
        this.mContext = mContext;
       this. mcallback=callback;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View listItem= layoutInflater.inflate(R.layout.item_category, viewGroup, false);
        MyHolder viewHolder = new MyHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder myHolder, final int i) {
        Category category=categories.get(i);
        String name=category.getCategories().getName();
        int id=category.getCategories().getId();
        myHolder.textView.setText(name);

        myHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mcallback.item(v,i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.category_text);
        }
    }
}
