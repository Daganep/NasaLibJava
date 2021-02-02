package com.geekbrains.nasalib.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItemLinks {
    @Expose
    @SerializedName("href")
    private String href;

    public String getHref() {
        return href;
    }
}
