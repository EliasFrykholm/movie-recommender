package com.example.recommendationapi.dtos;

public class RateGenresRequest {
    public final String userId;
    public final GenreRating[] ratings;

    public RateGenresRequest(String userId, GenreRating[] ratings) {
        this.userId = userId;
        this.ratings = ratings;
    }
}
