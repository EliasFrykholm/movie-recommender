package com.example.recommendationapi.services;

import com.example.recommendationapi.dtos.GenreRating;
import com.example.recommendationapi.models.*;
import com.example.recommendationapi.repository.neo4j.GenreRepository;
import com.example.recommendationapi.repository.neo4j.MovieRepository;
import com.example.recommendationapi.repository.neo4j.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PreferencesService {
    @Autowired
    private final UserRepository userRepo;
    private final GenreRepository genreRepo;
    private final MovieRepository movieRepo;

    public PreferencesService(UserRepository userRepo, GenreRepository genreRepo, MovieRepository movieRepo) {
        this.userRepo = userRepo;
        this.genreRepo = genreRepo;
        this.movieRepo = movieRepo;
    }

    public void addGenreRatings(String userId, GenreRating[] ratings){
        userRepo.addUser(userId);
        for(GenreRating rating : ratings) {
            genreRepo.addGenre(rating.genre);
            userRepo.addGenreRelationship(userId, rating.genre, rating.liked);
        }
    }

    public void addMovieRating(String userId, String tmdbId, boolean liked){
        userRepo.addUser(userId);
        movieRepo.addMovie(tmdbId);
        userRepo.addMovieRelationship(userId, tmdbId, liked);
    }

}
