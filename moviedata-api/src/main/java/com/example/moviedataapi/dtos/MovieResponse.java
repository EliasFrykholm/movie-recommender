package com.example.moviedataapi.dtos;

import com.example.moviedataapi.dtos.tmdb.MovieDetailsResponse;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

public class MovieResponse {
    public final int tmdbId;
    public final String title;
    public final String description;
    public final Date releaseDate;
    public final Collection<String> genres;
    public final int runtime;
    public final double rating;
    public final String coverArtUrl;
    public final String trailerUrl;

    public MovieResponse(int tmdbId,
                         String title,
                         String description,
                         Date releaseDate,
                         Collection<String> genres,
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

    public MovieResponse(int tmdbId, MovieDetailsResponse details, String trailer) {
        this.tmdbId = tmdbId;
        this.title = details.title();
        this.description = details.overview();
        this.releaseDate = details.release_date();
        this.genres = Arrays.stream(details.genres()).map(genre -> genre.name()).toList();
        this.runtime = details.runtime();
        this.rating = details.vote_average();
        this.coverArtUrl = details.backdrop_path();
        this.trailerUrl = trailer;
    }
}
