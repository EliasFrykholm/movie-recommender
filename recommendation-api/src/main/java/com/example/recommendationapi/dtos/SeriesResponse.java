package com.example.recommendationapi.dtos;

import java.util.Collection;
import java.util.Date;

public record SeriesResponse(int tmdbId, String title, String description,
                             Date releaseDate, Collection<String> genres, double rating,
                             String coverArtUrl, String trailerUrl, int episodes) {
}
