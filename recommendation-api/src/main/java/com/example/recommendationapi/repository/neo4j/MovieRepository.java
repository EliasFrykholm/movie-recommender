package com.example.recommendationapi.repository.neo4j;

import com.example.recommendationapi.models.Movie;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

public interface MovieRepository extends Neo4jRepository<Movie, String> {
    @Query("MERGE (n:Movie {tmdbId: $tmdbId}) RETURN n")
    Movie addMovie(String tmdbId);
}