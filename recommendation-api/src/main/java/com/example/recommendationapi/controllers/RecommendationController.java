package com.example.recommendationapi.controllers;

import com.example.recommendationapi.dtos.MovieResponse;
import com.example.recommendationapi.dtos.RecommendationRequest;
import com.example.recommendationapi.repository.neo4j.UserRepository;
import com.example.recommendationapi.services.MovieDataService;
import com.example.recommendationapi.services.RecommendationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.Optional;

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

    @GetMapping("/movie")
    public ResponseEntity<Flux<MovieResponse>> getMovieRecommendation(@RequestBody RecommendationRequest request){
        if(!userRepo.existsByUserId(request.userId)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Collection<Integer> recommendations = recommendationService.getMovieRecommendations(request.userId);
        if(!recommendations.isEmpty()){
            Flux<MovieResponse> movies = Flux.fromStream(recommendations.stream())
                    .flatMap(movieDataService::fetchMovieData);
            return ResponseEntity.ok(movies);
        } else {
            Flux<Integer> popularNotSeen = movieDataService.fetchPopularMovies(1).filter(id -> !userRepo.hasRated(request.userId, id));
            return ResponseEntity.ok(popularNotSeen.flatMap(movieDataService::fetchMovieData));
        }
    }

    @GetMapping("/series")
    public ResponseEntity<String> getSeriesRecommendation(@RequestBody RecommendationRequest request) {
        if(!userRepo.existsByUserId(request.userId)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(recommendationService.getSeriesRecommendation(request.userId));
    }
}
