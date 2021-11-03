package com.example.moviedataapi.services;

import com.example.moviedataapi.dtos.tmdb.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
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

    private String getTrailerLink(Trailer trailer){
        return "https://www.youtube.com/watch?v=" + trailer.key();
    }

    private String getYoutubeTrailer(Trailer[] trailers) {
        return Arrays.stream(trailers)
                .filter(trailer -> trailer.site().equals("YouTube") && trailer.type().equals("Trailer"))
                .findFirst()
                .map(this::getTrailerLink)
                .orElse("");
    }

    public Mono<String> getMovieTrailer(int tmdbId){
        return webClient.get().uri(tmdbPath, uriBuilder -> uriBuilder
                .pathSegment("movie")
                .pathSegment(Integer.toString(tmdbId))
                .pathSegment("videos")
                .queryParam("api_key", tmdbApiKey)
                .build()).retrieve().bodyToMono(TrailerResponse.class)
                .map(TrailerResponse::results)
                .map(this::getYoutubeTrailer);
    }

    public Mono<String> getSeriesTrailers(int tmdbId){
        return webClient.get().uri(tmdbPath, uriBuilder -> uriBuilder
                .pathSegment("tv")
                .pathSegment(Integer.toString(tmdbId))
                .pathSegment("videos")
                .queryParam("api_key", tmdbApiKey)
                .build()).retrieve().bodyToMono(TrailerResponse.class)
                .map(TrailerResponse::results)
                .map(this::getYoutubeTrailer);
    }

    public Mono<MovieDetailsResponse> getMovieDetails(int tmdbId) {
        return webClient.get().uri(tmdbPath, uriBuilder -> uriBuilder
                .pathSegment("movie")
                .pathSegment(Integer.toString(tmdbId))
                .queryParam("api_key", tmdbApiKey)
                .build()).retrieve().bodyToMono(MovieDetailsResponse.class);
    }

    public Mono<SeriesDetailsResponse> getSeriesDetails(int tmdbId) {
        return webClient.get().uri(tmdbPath, uriBuilder -> uriBuilder
                .pathSegment("tv")
                .pathSegment(Integer.toString(tmdbId))
                .queryParam("api_key", tmdbApiKey)
                .build()).retrieve().bodyToMono(SeriesDetailsResponse.class);
    }

    public Flux<Integer> getPopularMovieIds(int page) {
        return webClient.get().uri(tmdbPath, uriBuilder -> uriBuilder
                .pathSegment("discover")
                .pathSegment("movie")
                .queryParam("api_key", tmdbApiKey)
                .queryParam("page", page)
                .build()).retrieve().bodyToMono(DiscoverResponse.class)
                .flatMapMany(discoverResponse -> Flux.fromArray(discoverResponse.results()))
                .map(TmdbIdResponse::id);
    }
}
