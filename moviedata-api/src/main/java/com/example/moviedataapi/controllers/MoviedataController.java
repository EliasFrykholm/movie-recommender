package com.example.moviedataapi.controllers;

import com.example.moviedataapi.dtos.MovieResponse;
import com.example.moviedataapi.dtos.SeriesResponse;
import com.example.moviedataapi.services.MovieDataService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("moviedata")
public class MoviedataController {
    public final MovieDataService movieDataService;

    public MoviedataController(MovieDataService movieDataService) {
        this.movieDataService = movieDataService;
    }

    @GetMapping("/movie/{id}")
    public Mono<MovieResponse> getMovieData(@PathVariable int id){
        return movieDataService.getMovieDetails(id)
                .zipWith(movieDataService.getMovieTrailer(id))
                .map(tuple -> new MovieResponse(id, tuple.getT1(), tuple.getT2()));
    }

    @GetMapping("/series/{id}")
    public Mono<SeriesResponse> getSeriesData(@PathVariable int id){
        return movieDataService.getSeriesDetails(id)
                .zipWith(movieDataService.getSeriesTrailers(id))
                .map(tuple -> new SeriesResponse(id, tuple.getT1(), tuple.getT2()));
    }

    @GetMapping("/{type}/popular/{page}")
    public Flux<Integer> getPopular(@PathVariable String type, @PathVariable int page){
        return movieDataService.getPopularIds(type, page);
    }
}
