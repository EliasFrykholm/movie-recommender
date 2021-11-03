package com.example.moviedataapi.dtos.tmdb;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieDetailsResponse {
    public final String title;
    public final String overview;
    public final Date release_date;
    public final Genre[] genres;
    public final int runtime;
    public final int vote_average;
    public final String backdrop_path;

    public MovieDetailsResponse(String title,
                                String overview,
                                Date release_date,
                                Genre[] genres,
                                int runtime,
                                int vote_average,
                                String backdrop_path) {
        this.title = title;
        this.overview = overview;
        this.release_date = release_date;
        this.genres = genres;
        this.runtime = runtime;
        this.vote_average = vote_average;
        this.backdrop_path = backdrop_path;
    }
}