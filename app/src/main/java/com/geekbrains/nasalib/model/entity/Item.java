package com.geekbrains.nasalib.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Item {
    @Expose
    @SerializedName("data")
    private List<Itemdata> data;

    public List<Itemdata> getData() {
        return data;
    }
}
