package com.example.recommendationapi.controllers;

import com.example.recommendationapi.dtos.RecommendationRequest;
import com.example.recommendationapi.repository.neo4j.UserRepository;
import com.example.recommendationapi.services.RecommendationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("recommendation")
public class RecommendationController {
    private final UserRepository userRepo;
    private final RecommendationService recommendationService;

    public RecommendationController(UserRepository userRepo, RecommendationService recommendationService) {
        this.userRepo = userRepo;
        this.recommendationService = recommendationService;
    }

    @GetMapping("/movie")
    public ResponseEntity<String> getMovieRecommendation(@RequestBody RecommendationRequest request){
        if(!userRepo.existsByUserId(request.userId)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(recommendationService.getMovieRecommendation(request.userId));
    }

    @GetMapping("/series")
    public ResponseEntity<String> getSeriesRecommendation(@RequestBody RecommendationRequest request) {
        if(!userRepo.existsByUserId(request.userId)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(recommendationService.getSeriesRecommendation(request.userId));
    }
}
