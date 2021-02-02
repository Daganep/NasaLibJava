package com.geekbrains.nasalib.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ItemData {
    @Expose
    @SerializedName("title")
    private String title;
    @Expose
    @SerializedName("description")
    private String description;
    @Expose
    @SerializedName("secondary_creator")
    private String creator;
    @Expose
    @SerializedName("date_created")
    private String dateCreate;

    public String getDescription() {
        return description;
    }

    public String getCreator() {
        return creator;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public String getTitle() {
        return title;
    }
}
