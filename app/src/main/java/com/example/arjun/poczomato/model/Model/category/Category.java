
package com.example.arjun.poczomato.model.Model.category;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Category {

    @SerializedName("categories")
    @Expose
    private Categories_ categories;

    public Categories_ getCategories() {
        return categories;
    }

    public void setCategories(Categories_ categories) {
        this.categories = categories;
    }

}
