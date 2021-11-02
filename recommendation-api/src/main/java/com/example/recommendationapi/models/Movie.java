package com.example.recommendationapi.models;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashSet;
import java.util.Set;

@Node("Movie")
public class Movie {
    @Id
    public final int tmdbId;

    @Relationship(type = "LIKED_BY", direction = Relationship.Direction.INCOMING)
    private Set<User> likedBy = new HashSet<>();

    public Movie(int tmdbId) {
        this.tmdbId = tmdbId;
    }
}
