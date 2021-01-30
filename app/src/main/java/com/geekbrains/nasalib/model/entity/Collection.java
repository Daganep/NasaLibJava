package com.geekbrains.nasalib.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Collection {

    @Expose
    @SerializedName("version")
    private String version;

    @Expose
    @SerializedName("href")
    private String href;

    @Expose
    @SerializedName("meatdata")
    private Metadata meatdata;

    @Expose
    @SerializedName("items")
    private List<Item> items;

    @Expose
    @SerializedName("links")
    private List<Link> links;

    public String getVersion() {
        return version;
    }

    public String getHref() {
        return href;
    }

    public Metadata getMeatdata() {
        return meatdata;
    }

    public List<Item> getItems() {
        return items;
    }

    public List<Link> getLinks() {
        return links;
    }
}
