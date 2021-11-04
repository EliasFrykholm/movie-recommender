package com.example.recommendationapi.controllers;

import com.example.recommendationapi.dtos.MovieResponse;
import com.example.recommendationapi.dtos.RecommendationRequest;
import com.example.recommendationapi.dtos.SeriesResponse;
import com.example.recommendationapi.models.DataType;
import com.example.recommendationapi.repository.neo4j.UserRepository;
import com.example.recommendationapi.services.MovieDataService;
import com.example.recommendationapi.services.RecommendationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("recommendation")
public class RecommendationController {
    private final UserRepository userRepo;
    private final RecommendationService recommendationService;
    private final MovieDataService movieDataService;

    public RecommendationController(UserRepository userRepo,
                                    RecommendationService recommendationService,
                                    MovieDataService movieDataService) {
        this.userRepo = userRepo;
        this.recommendationService = recommendationService;
        this.movieDataService = movieDataService;
    }

    @GetMapping("/movie/{userId}")
    public ResponseEntity<Flux<MovieResponse>> getMovieRecommendation(@PathVariable String userId){
        if(!userRepo.existsByUserId(userId)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Flux<Integer> recommendations = recommendationService.getMovieRecommendations(userId).switchIfEmpty(getPopular(DataType.MOVIE, userId, 1));
        return ResponseEntity.ok(recommendations.flatMap(movieDataService::fetchMovieData));
    }

    public Flux<Integer> getPopular(DataType type, String userId, int page){
        if(page > 500)
            return null;
        return movieDataService.fetchPopular(type, page).filter(id -> !userRepo.hasRated(userId, id)).switchIfEmpty(Flux.defer(() -> getPopular(type, userId, page+1)));
    }

    @GetMapping("/series")
    public ResponseEntity<Flux<SeriesResponse>> getSeriesRecommendation(@RequestBody RecommendationRequest request) {
        if(!userRepo.existsByUserId(request.userId)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Flux<Integer> recommendations = recommendationService.getSeriesRecommendation(request.userId).switchIfEmpty(getPopular(DataType.SERIES, request.userId, 1));
        return ResponseEntity.ok(recommendations.flatMap(movieDataService::fetchSeriesData));
    }
}
