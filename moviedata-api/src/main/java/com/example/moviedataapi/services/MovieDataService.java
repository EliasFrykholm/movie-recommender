package com.example.moviedataapi.services;

import com.example.moviedataapi.dtos.tmdb.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

@Service
public class MovieDataService {
    @Value("${tmdb.api.key}")
    private String tmdbApiKey;
    @Value("${tmdb.api.path}")
    private String tmdbPath;

    private final WebClient webClient;

    public MovieDataService(WebClient webClient) {
        this.webClient = webClient;
    }

    private String getTrailerLink(Optional<Trailer> trailer){
        if(trailer.isPresent()){
            return "https://www.youtube.com/watch?v=" + trailer.get().key();
        } else {
            return "";
        }
    }

    public Mono<String> getYoutubeTrailer(String tmdbId) {
        return getTrailers(tmdbId).map(movieTrailerResponse -> Arrays.stream(movieTrailerResponse.results()).filter(trailer -> trailer.site().equals("YouTube") && trailer.type().equals("Trailer")).findFirst()).map(this::getTrailerLink);
    }

    public Mono<MovieTrailerResponse> getTrailers(String tmdbId){
        return webClient.get().uri(tmdbPath, uriBuilder -> uriBuilder
                .pathSegment("movie")
                .pathSegment(tmdbId)
                .pathSegment("videos")
                .queryParam("api_key", tmdbApiKey)
                .build()).retrieve().bodyToMono(MovieTrailerResponse.class);
    }

    public Mono<MovieDetailsResponse> getMovieDetails(String tmdbId) {
        return webClient.get().uri(tmdbPath, uriBuilder -> uriBuilder
                .pathSegment("movie")
                .pathSegment(tmdbId)
                .queryParam("api_key", tmdbApiKey)
                .build()).retrieve().bodyToMono(MovieDetailsResponse.class);
    }

    public Mono<Collection<String>> getPopularMovieIds(int page) {
        return webClient.get().uri(tmdbPath, uriBuilder -> uriBuilder
                .pathSegment("discover")
                .pathSegment("movie")
                .queryParam("api_key", tmdbApiKey)
                .queryParam("page", page)
                .build()).retrieve().bodyToMono(DiscoverResponse.class)
                .map(discoverResponse -> Arrays.stream(discoverResponse.results())
                .map(TmdbIdResponse::id).toList());
    }
}
