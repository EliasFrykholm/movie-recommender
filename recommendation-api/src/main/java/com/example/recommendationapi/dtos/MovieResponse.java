package com.example.recommendationapi.dtos;

import java.util.Collection;
import java.util.Date;

public record MovieResponse(int tmdbId, String title, String description,
                            Date releaseDate, Collection<String> genres, int runtime,
                            int rating, String coverArtUrl, String trailerUrl) {
}