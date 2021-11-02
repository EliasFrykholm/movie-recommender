package com.example.recommendationapi.dtos;

public class GenreRating {
    public final String genre;
    public final boolean liked;

    public GenreRating(String genre, boolean liked) {
        this.genre = genre;
        this.liked = liked;
    }
}
