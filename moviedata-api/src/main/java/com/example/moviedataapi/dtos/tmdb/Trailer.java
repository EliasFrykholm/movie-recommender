package com.example.moviedataapi.dtos.tmdb;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Trailer {
    public final String site;
    public final String type;
    public final String key;
    public final boolean official;

    public Trailer(String site, String type, String key, boolean official) {
        this.site = site;
        this.type = type;
        this.key = key;
        this.official = official;
    }
}
