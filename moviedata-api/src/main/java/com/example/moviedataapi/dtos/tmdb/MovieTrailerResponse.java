package com.example.moviedataapi.dtos.tmdb;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieTrailerResponse {
    public final Trailer[] response;

    public MovieTrailerResponse(Trailer[] response) {
        this.response = response;
    }
}
