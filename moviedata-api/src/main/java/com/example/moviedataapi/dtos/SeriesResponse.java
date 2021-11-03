package com.example.moviedataapi.dtos;

import com.example.moviedataapi.dtos.tmdb.Genre;
import com.example.moviedataapi.dtos.tmdb.SeriesDetailsResponse;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

public class SeriesResponse {
    public final int tmdbId;
    public final String title;
    public final String description;
    public final Date releaseDate;
    public final Collection<String> genres;
    public final double rating;
    public final String coverArtUrl;
    public final String trailerUrl;
    public final int episodes;

    public SeriesResponse(int tmdbId,
                          String title,
                          String description,
                          Date releaseDate,
                          Collection<String> genres, int rating, String coverArtUrl, String trailerUrl, int episodes) {
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

    public SeriesResponse(int tmdbId, SeriesDetailsResponse series, String trailerUrl){
        this.tmdbId = tmdbId;
        this.title = series.name();
        this.description = series.overview();
        this.releaseDate = series.first_air_date();
        this.genres = Arrays.stream(series.genres()).map(Genre::name).toList();
        this.rating = series.vote_average();
        this.coverArtUrl = "https://image.tmdb.org/t/p/original" + series.poster_path();
        this.trailerUrl = trailerUrl;
        this.episodes = series.number_of_episodes();
    }
}
