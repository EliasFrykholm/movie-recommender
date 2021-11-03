package com.example.recommendationapi.services;

import com.example.recommendationapi.dtos.MovieResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MovieDataService {
    @Value("${moviedata.api.path}")
    private String moviedataPath;

    private final WebClient webClient;

    public MovieDataService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<MovieResponse> fetchMovieData(int tmdbId) {
        return webClient.get().uri(moviedataPath, uriBuilder -> uriBuilder
                .pathSegment("moviedata")
                .pathSegment("movie")
                .pathSegment(Integer.toString(tmdbId))
                .build()).retrieve().bodyToMono(MovieResponse.class);
    }

    public Flux<Integer> fetchPopularMovies(int page) {
        return webClient.get().uri(moviedataPath, uriBuilder -> uriBuilder
                .pathSegment("moviedata")
                .pathSegment("movies")
                .pathSegment("popular")
                .pathSegment(Integer.toString(page))
                .build()).retrieve().bodyToFlux(Integer.class);
    }
}
