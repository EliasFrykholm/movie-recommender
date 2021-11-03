package com.example.recommendationapi.models;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node("Series")
public class Series {
    @Id
    public final String tmdbId;

    public Series(String tmdbId) {
        this.tmdbId = tmdbId;
    }
}
