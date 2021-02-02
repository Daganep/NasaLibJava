package com.geekbrains.nasalib.model.entity;

import java.io.Serializable;

public class CEInfo implements Serializable {

    private String URL;
    private String title;
    private String data;
    private String creator;

    public CEInfo(Item item){
        URL = item.getLinks().get(0).getHref();
        title = item.getData().get(0).getTitle();
        data = item.getData().get(0).getDateCreate();
        creator = item.getData().get(0).getCreator();
    }


    public String getURL() {
        return URL;
    }

    public String getTitle() {
        return title;
    }

    public String getCreator() {
        return creator;
    }

    public String getData() {
        return data;
    }
}
