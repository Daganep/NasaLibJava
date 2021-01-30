package com.geekbrains.nasalib.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Metadata {
    @Expose
    @SerializedName("total_hits")
    private int totalHits;

    public int getTotalHits() {
        return totalHits;
    }
}
