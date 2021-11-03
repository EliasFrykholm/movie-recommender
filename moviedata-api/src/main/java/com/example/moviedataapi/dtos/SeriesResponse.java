package com.example.moviedataapi.dtos;

public class SeriesResponse {
    public final String tmdbId;
    public final String title;
    public final String description;
    public final String releaseDate;
    public final String[] genres;
    public final String coverArtUrl;
    public final String trailerUrl;
    public final int episodes;

    public SeriesResponse(String tmdbId,
                          String title,
                          String description,
                          String releaseDate,
                          String[] genres, String coverArtUrl, String trailerUrl, int episodes) {
        this.tmdbId = tmdbId;
        this.title = title;
        this.description = description;
        this.releaseDate = releaseDate;
        this.genres = genres;
        this.coverArtUrl = coverArtUrl;
        this.trailerUrl = trailerUrl;
        this.episodes = episodes;
    }
}
