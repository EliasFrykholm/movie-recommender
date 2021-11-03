package com.example.moviedataapi.dtos.tmdb;

import java.util.Date;

public record SeriesDetailsResponse(String name,
                                    String overview,
                                    Date first_air_date,
                                    Genre[] genres,
                                    double vote_average,
                                    String poster_path,
                                    int number_of_episodes) {
}
