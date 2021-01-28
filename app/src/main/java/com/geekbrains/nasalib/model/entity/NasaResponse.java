package com.geekbrains.nasalib.model.entity;

import androidx.room.Entity;

import java.util.List;

public class NasaResponse {
    private List<Link> links;

    public List<Link> getLinks() {
        return links;
    }
}
