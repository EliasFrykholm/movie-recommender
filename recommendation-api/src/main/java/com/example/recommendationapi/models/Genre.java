package com.example.recommendationapi.models;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node("Genre")
public class Genre {
    @Id
    public final String genre;

    public Genre(String genre) {
        this.genre = genre;
    }
}
