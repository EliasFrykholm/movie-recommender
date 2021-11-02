package com.example.recommendationapi.models;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

import java.util.Objects;

@RelationshipProperties
public class RatedMovieRelationship {
    @Id
    @GeneratedValue
    private Long id;

    @TargetNode
    public final Movie movie;

    public final boolean liked;

    public RatedMovieRelationship(Movie movie, boolean liked) {
        this.movie= movie;
        this.liked = liked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RatedMovieRelationship that = (RatedMovieRelationship) o;
        return Objects.equals(movie, that.movie);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movie);
    }
}
