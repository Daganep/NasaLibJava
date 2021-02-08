package com.geekbrains.nasalib.model.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "table_elements")
public class Element implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String URL;
    private String title;
    private String data;
    private String creator;

    public Element(){}

    public Element(Item item){
        if(item.getLinks() != null)
        URL = item.getLinks().get(0).getHref();
        if(item.getData() != null){
            title = item.getData().get(0).getTitle();
            data = item.getData().get(0).getDateCreate();
            creator = item.getData().get(0).getCreator();
        }
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
