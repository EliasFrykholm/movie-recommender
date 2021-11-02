package com.example.recommendationapi.models;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

import java.util.Objects;

@RelationshipProperties
public class RatedGenreRelationship {
    @Id
    @GeneratedValue
    private Long id;

    @TargetNode
    public final Genre genre;

    public final boolean liked;

    public RatedGenreRelationship(Genre genre, boolean liked) {
        this.genre = genre;
        this.liked = liked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RatedGenreRelationship that = (RatedGenreRelationship) o;
        return Objects.equals(genre, that.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(genre);
    }
}