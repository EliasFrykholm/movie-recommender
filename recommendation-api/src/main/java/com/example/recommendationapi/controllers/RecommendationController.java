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
        Flux<Integer> recommendations = recommendationService.getMovieRecommendations(request.userId).switchIfEmpty(getPopular(request.userId, 1));
        return ResponseEntity.ok(recommendations.flatMap(movieDataService::fetchMovieData));
    }

    public Flux<Integer> getPopular(String userId, int page){
        if(page > 500)
            return null;
        return movieDataService.fetchPopularMovies(page).filter(id -> !userRepo.hasRated(userId, id)).switchIfEmpty(Flux.defer(() -> getPopular(userId, page+1)));
    }

    @GetMapping("/series")
    public ResponseEntity<Flux<Integer>> getSeriesRecommendation(@RequestBody RecommendationRequest request) {
        if(!userRepo.existsByUserId(request.userId)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(recommendationService.getSeriesRecommendation(request.userId));
    }
}
