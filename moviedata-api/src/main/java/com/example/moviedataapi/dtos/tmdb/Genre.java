package com.example.moviedataapi.dtos.tmdb;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Genre {
    public final int id;
    public final String name;

    public Genre(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
