package com.example.recommendationapi.models;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashSet;
import java.util.Set;

public class User {
    @Id
    public final String userId;

    @Relationship(type = "RATING", direction = Relationship.Direction.OUTGOING)
    private Set<RatedMovieRelationship> ratedMovies = new HashSet<>();

    @Relationship(type = "RATING", direction = Relationship.Direction.OUTGOING)
    private Set<RatedGenreRelationship> ratedGenres = new HashSet<>();

    public User(String userId) {
        this.userId = userId;
    }

    public Set<RatedMovieRelationship> getRatedMovies() {
        return ratedMovies;
    }

    public Set<RatedGenreRelationship> getRatedGenres() {
        return ratedGenres;
    }
}
