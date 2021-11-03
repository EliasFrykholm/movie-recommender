package com.example.moviedataapi.dtos;

public class MovieResponse {
    public final String tmdbId;
    public final String title;
    public final String description;
    public final String releaseDate;
    public final String[] genres;
    public final int runtime;
    public final int rating;
    public final String coverArtUrl;
    public final String trailerUrl;

    public MovieResponse(String tmdbId,
                         String title,
                         String description,
                         String releaseDate,
                         String[] genres,
                         int runtime, int rating,
                         String coverArtUrl, String trailerUrl) {
        this.tmdbId = tmdbId;
        this.title = title;
        this.description = description;
        this.releaseDate = releaseDate;
        this.genres = genres;
        this.runtime = runtime;
        this.rating = rating;
        this.coverArtUrl = coverArtUrl;
        this.trailerUrl = trailerUrl;
    }
}
