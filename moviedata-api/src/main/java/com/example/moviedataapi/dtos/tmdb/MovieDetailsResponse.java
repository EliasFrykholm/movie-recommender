package com.example.moviedataapi.dtos.tmdb;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public record MovieDetailsResponse(String title, String overview, Date release_date,
                                   Genre[] genres, int runtime, int vote_average,
                                   String backdrop_path) {
}