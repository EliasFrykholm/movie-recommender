package com.example.recommendationapi.controllers;

import com.example.recommendationapi.dtos.RateGenresRequest;
import com.example.recommendationapi.dtos.RatingRequest;
import com.example.recommendationapi.services.PreferencesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rate")
public class PreferencesController {
    @Autowired
    private final PreferencesService preferencesService;

    public PreferencesController(PreferencesService preferencesService) {
        this.preferencesService = preferencesService;
    }

    @PostMapping("/genres")
    public void rateGenres(@RequestBody RateGenresRequest request) {
        preferencesService.addGenreRatings(request.userId, request.ratings);
    }

    @PostMapping("/movie")
    public void rateMovie(@RequestBody RatingRequest request) {
        preferencesService.addMovieRating(request.userId, request.tmdbId, request.liked);
    }

    @PostMapping("/series")
    public void rateSeries(@RequestBody RatingRequest request) {
        preferencesService.addSeriesRating(request.userId, request.tmdbId, request.liked);
    }
}
