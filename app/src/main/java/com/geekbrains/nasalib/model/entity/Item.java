package com.geekbrains.nasalib.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Item {
    @Expose
    @SerializedName("links")
    private List<ItemLinks> links;

    @Expose
    @SerializedName("data")
    private List<ItemData> data;

    @Expose
    @SerializedName("href")
    private String href;

    public String getHref() {
        return href;
    }

    public List<ItemLinks> getLinks() {
        return links;
    }
}
