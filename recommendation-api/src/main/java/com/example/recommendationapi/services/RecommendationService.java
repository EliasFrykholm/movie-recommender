package com.example.recommendationapi.services;

import com.example.recommendationapi.models.Movie;
import com.example.recommendationapi.models.Series;
import com.example.recommendationapi.repository.neo4j.MovieRepository;
import com.example.recommendationapi.repository.neo4j.SeriesRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class RecommendationService {
    private final MovieRepository movieRepo;
    private final SeriesRepository seriesRepo;

    public RecommendationService(MovieRepository movieRepo, SeriesRepository seriesRepo) {
        this.movieRepo = movieRepo;
        this.seriesRepo = seriesRepo;
    }

    public Flux<Integer> getMovieRecommendations(String userId) {
        Flux<Movie> recommendations = movieRepo.getMovieRecommendations(userId);
        return recommendations.map(movie -> movie.tmdbId);
    }

    public Flux<Integer> getSeriesRecommendation(String userId) {
        Flux<Series> recommendations = seriesRepo.getSeriesRecommendations(userId);
        return recommendations.map(series -> series.tmdbId);
    }
}
