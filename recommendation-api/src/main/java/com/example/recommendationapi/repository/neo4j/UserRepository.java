package com.example.recommendationapi.repository.neo4j;

import com.example.recommendationapi.models.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

public interface UserRepository extends Neo4jRepository<User, String> {
    boolean existsByUserId(String userId);

    @Query("MERGE (n:User {userId: $userId}) RETURN n")
    User addUser(String userId);

    @Query("MATCH (user:User {userId: $userId}) " +
            "WITH user " +
            "MATCH (movie:Movie {tmdbId: $tmdbId}) " +
            "MERGE (user)-[r:RATED]->(movie) " +
            "SET r.liked = $liked")
    void addMovieRelationship(String userId, String tmdbId, boolean liked);

    @Query("MATCH (user:User {userId: $userId}) " +
            "WITH user " +
            "MATCH (series:Series {tmdbId: $tmdbId}) " +
            "MERGE (user)-[r:RATED]->(series) " +
            "SET r.liked = $liked")
    void addSeriesRelationship(String userId, String tmdbId, boolean liked);

    @Query("MATCH (user:User {userId: $userId}) " +
            "WITH user " +
            "MATCH (genre:Genre {genre: $genre}) " +
            "MERGE (user)-[r:RATED]->(genre) " +
            "SET r.liked = $liked")
    void addGenreRelationship(String userId, String genre, boolean liked);
}