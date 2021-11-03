package com.example.moviedataapi.dtos.tmdb;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TrailerResponse(Trailer[] results) {
}
