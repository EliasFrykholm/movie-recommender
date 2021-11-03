package com.example.recommendationapi.services;

import com.example.recommendationapi.models.Movie;
import com.example.recommendationapi.models.Series;
import com.example.recommendationapi.repository.neo4j.MovieRepository;
import com.example.recommendationapi.repository.neo4j.SeriesRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class RecommendationService {
    private final MovieRepository movieRepo;
    private final SeriesRepository seriesRepo;

    public RecommendationService(MovieRepository movieRepo, SeriesRepository seriesRepo) {
        this.movieRepo = movieRepo;
        this.seriesRepo = seriesRepo;
    }

    public Collection<Integer> getMovieRecommendations(String userId) {
        Collection<Movie> recommendations = movieRepo.getMovieRecommendations(userId);
        return recommendations.stream().map(movie -> movie.tmdbId).toList();
    }

    public String getSeriesRecommendation(String userId) {
        Collection<Series> recommendations = seriesRepo.getSeriesRecommendations(userId);
        if(recommendations.isEmpty()){
            return "No recommendation";
        }
        return recommendations.stream().map(recommendation -> "Movie id: " + recommendation.tmdbId).reduce("", (s, s2) -> s + "\n" + s2);
    }
}
