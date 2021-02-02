package com.geekbrains.nasalib.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ItemData {
    @Expose
    @SerializedName("description")
    private String description;

    public String getDescription() {
        return description;
    }
}
