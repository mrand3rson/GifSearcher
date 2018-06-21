package com.example.gifsearcher.models;


public class GiphyData {

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public GiphyObject getImages() {
        return images;
    }

    private String title;
    private GiphyObject images;
}
