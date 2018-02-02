package com.test.androidtest.service.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by kapillondhe on 2/2/18.
 */

public class Films {

    @SerializedName("film")
    private List<Film> films = null;

    public List<Film> getFilms() {
        return films;
    }

    public void setFilms(List<Film> image) {
        this.films = image;
    }
}
