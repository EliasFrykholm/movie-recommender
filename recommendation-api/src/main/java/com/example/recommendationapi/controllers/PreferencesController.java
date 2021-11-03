package com.example.recommendationapi.controllers;

import com.example.recommendationapi.dtos.RateGenresRequest;
import com.example.recommendationapi.dtos.RatingRequest;
import com.example.recommendationapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rate")
public class PreferencesController {
    @Autowired
    private final UserService userService;

    public PreferencesController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/genres")
    public void rateGenres(@RequestBody RateGenresRequest request) {
        userService.addGenreRatings(request.userId, request.ratings);
    }

    @PostMapping("/movie")
    public void rateMovie(@RequestBody RatingRequest request) {
        userService.addMovieRating(request.userId, request.tmdbId, request.liked);
    }

    @PostMapping("/series")
    public void rateSeries(@RequestBody RatingRequest request) {
        userService.addSeriesRating(request.userId, request.tmdbId, request.liked);
    }
}
