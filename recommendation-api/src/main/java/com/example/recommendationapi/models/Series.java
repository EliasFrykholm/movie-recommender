package com.example.recommendationapi.models;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node("Series")
public class Series {
    @Id @GeneratedValue
    public long id;

    public final int tmdbId;

    public Series(int tmdbId) {
        this.tmdbId = tmdbId;
    }
}
