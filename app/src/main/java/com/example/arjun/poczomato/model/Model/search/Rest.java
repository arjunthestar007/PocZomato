
package com.example.arjun.poczomato.model.Model.search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rest
{

    @SerializedName("res_id")
    @Expose
    private Integer resId;

    public Integer getResId() {
        return resId;
    }

    public void setResId(Integer resId) {
        this.resId = resId;
    }

}
