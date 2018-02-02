
package com.test.androidtest.service.model;

import com.google.gson.annotations.SerializedName;


public class Film {

    @SerializedName("id")
    private String id;

    @SerializedName("title")
    private String title;

    @SerializedName("images")
    private Images images;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }
}
