package com.example.moviedataapi.controllers;

import com.example.moviedataapi.dtos.MovieResponse;
import com.example.moviedataapi.dtos.SeriesResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("moviedata")
public class MoviedataController {

    @GetMapping("/movie")
    public ResponseEntity<MovieResponse> getMovieData(String tmdbId){
        return ResponseEntity.internalServerError().build();
    }

    @GetMapping("/series")
    public ResponseEntity<SeriesResponse> getSeriesData(String tmdbId){
        return ResponseEntity.internalServerError().build();
    }
}
