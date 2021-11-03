package com.example.recommendationapi.dtos;

public class RatingRequest {
    public final String userId;
    public final int tmdbId;
    public final boolean liked;

    public RatingRequest(String userId, int tmdbId, boolean liked) {
        this.userId = userId;
        this.tmdbId = tmdbId;
        this.liked = liked;
    }
}
