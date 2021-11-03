package com.example.recommendationapi.repository.neo4j;

import com.example.recommendationapi.models.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

public interface UserRepository extends Neo4jRepository<User, String> {
    boolean existsByUserId(String userId);

    @Query("MERGE (user:User {userId: $userId}) " +
            "MERGE (movie:Movie {tmdbId: $tmdbId}) " +
            "MERGE (user)-[r:RATED]->(movie) " +
            "SET r.liked = $liked")
    void addMovieRelationship(String userId, int tmdbId, boolean liked);

    @Query("MERGE (user:User {userId: $userId}) " +
            "MERGE (series:Series {tmdbId: $tmdbId}) " +
            "MERGE (user)-[r:RATED]->(series) " +
            "SET r.liked = $liked")
    void addSeriesRelationship(String userId, int tmdbId, boolean liked);

    @Query("MERGE (user:User {userId: $userId}) " +
            "MERGE (genre:Genre {genre: $genre})" +
            "MERGE (user)-[r:RATED]->(genre) " +
            "SET r.liked = $liked")
    void addGenreRelationship(String userId, String genre, boolean liked);

    @Query("MATCH (user:User {userId: $userId}) RETURN exists((user)-[:RATED]->({tmdbId: $tmdbId}))")
    boolean hasRated(String userId, int tmdbId);
}