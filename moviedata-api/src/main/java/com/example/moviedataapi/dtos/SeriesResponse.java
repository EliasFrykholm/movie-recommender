package com.example.moviedataapi.dtos;

import java.util.Date;

public class SeriesResponse {
    public final String tmdbId;
    public final String title;
    public final String description;
    public final Date releaseDate;
    public final String[] genres;
    public final int rating;
    public final String coverArtUrl;
    public final String trailerUrl;
    public final int episodes;

    public SeriesResponse(String tmdbId,
                          String title,
                          String description,
                          Date releaseDate,
                          String[] genres, int rating, String coverArtUrl, String trailerUrl, int episodes) {
        this.tmdbId = tmdbId;
        this.title = title;
        this.description = description;
        this.releaseDate = releaseDate;
        this.genres = genres;
        this.rating = rating;
        this.coverArtUrl = coverArtUrl;
        this.trailerUrl = trailerUrl;
        this.episodes = episodes;
    }
}
