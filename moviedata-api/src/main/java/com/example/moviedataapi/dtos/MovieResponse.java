package com.example.moviedataapi.dtos;

public class MovieResponse {
    public final String tmdbId;
    public final String title;
    public final String description;
    public final String releaseDate;
    public final String[] genres;
    public final String coverArtUrl;
    public final String trailerUrl;

    public MovieResponse(String tmdbId,
                         String title,
                         String description,
                         String releaseDate,
                         String[] genres,
                         String coverArtUrl, String trailerUrl) {
        this.tmdbId = tmdbId;
        this.title = title;
        this.description = description;
        this.releaseDate = releaseDate;
        this.genres = genres;
        this.coverArtUrl = coverArtUrl;
        this.trailerUrl = trailerUrl;
    }
}
