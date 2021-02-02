package com.geekbrains.nasalib.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {
    @Expose
    @SerializedName("description")
    private String description;

    public String getDescription() {
        return description;
    }
}
