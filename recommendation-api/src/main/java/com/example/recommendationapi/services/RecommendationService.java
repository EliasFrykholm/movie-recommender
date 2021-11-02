package com.example.recommendationapi.services;

import com.example.recommendationapi.models.Movie;
import com.example.recommendationapi.repository.neo4j.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class RecommendationService {
    private final MovieRepository movieRepo;

    public RecommendationService(MovieRepository movieRepo) {
        this.movieRepo = movieRepo;
    }

    public String getMovieRecommendation(String userId) {
        Collection<Movie> recommendations = movieRepo.getMovieRecommendations(userId);
        if(recommendations.isEmpty()){
            return "No recommendation";
        }
        return recommendations.stream().map(recommendation -> "Movie id: " + recommendation.tmdbId).reduce("", (s, s2) -> s + "\n" + s2);
    }
}
