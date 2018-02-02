package com.test.androidtest.service.model;

import com.google.gson.annotations.SerializedName;


public class RetrofitResponse {

    @SerializedName("films")
    private Films films = null;

    public Films getFilms() {
        return films;
    }

    public void setFilms(Films films) {
        this.films = films;
    }
}
