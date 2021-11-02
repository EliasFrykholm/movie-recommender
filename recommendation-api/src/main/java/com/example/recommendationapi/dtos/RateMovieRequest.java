package com.example.recommendationapi.dtos;

public class RateMovieRequest {
    public final String userId;
    public final String tmdbId;
    public final boolean liked;

    public RateMovieRequest(String userId, String tmdbId, boolean liked) {
        this.userId = userId;
        this.tmdbId = tmdbId;
        this.liked = liked;
    }
}
