package com.geekbrains.nasalib.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Itemdata {
    @Expose
    @SerializedName("links")
    private List<ItemdataLinks> links;

    @Expose
    @SerializedName("data")
    private List<Data> data;

    @Expose
    @SerializedName("href")
    private String href;

    public String getHref() {
        return href;
    }
}
