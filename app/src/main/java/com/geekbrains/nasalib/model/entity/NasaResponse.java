package com.geekbrains.nasalib.model.entity;

import androidx.room.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NasaResponse {
    @Expose
    @SerializedName("collection")
    private Collection collection;

    public Collection getCollection() {
        return collection;
    }
}
