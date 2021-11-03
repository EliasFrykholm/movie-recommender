package com.example.recommendationapi.models;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node("Movie")
public class Movie {
    @Id
    public final String tmdbId;

    public Movie(String tmdbId) {
        this.tmdbId = tmdbId;
    }
}
