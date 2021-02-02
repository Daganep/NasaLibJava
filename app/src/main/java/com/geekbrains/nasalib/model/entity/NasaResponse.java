package com.geekbrains.nasalib.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NasaResponse {
    @Expose
    @SerializedName("collection")
    private Collection collection;

    public Collection getCollection() {
        return collection;
    }
}
