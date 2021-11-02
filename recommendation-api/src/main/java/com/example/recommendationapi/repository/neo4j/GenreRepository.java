package com.example.recommendationapi.repository.neo4j;

import com.example.recommendationapi.models.Genre;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

public interface GenreRepository extends Neo4jRepository<Genre, String> {
    @Query("MERGE (n:Genre {genre: $genre}) RETURN n")
    Genre addGenre(String genre);
}