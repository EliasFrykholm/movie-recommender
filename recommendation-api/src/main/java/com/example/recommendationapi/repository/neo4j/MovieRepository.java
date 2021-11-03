package com.example.recommendationapi.repository.neo4j;

import com.example.recommendationapi.models.Movie;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.Collection;

public interface MovieRepository extends Neo4jRepository<Movie, Long> {
    @Query("MERGE (n:Movie {tmdbId: $tmdbId}) RETURN n")
    Movie addMovie(int tmdbId);

    @Query("MATCH (user:User {userId: $userId})-[myRate:RATED]->()<-[theirRate:RATED]-(similarUser:User)-[:RATED {liked: true}]->(potentialMovie:Movie) " +
            "WHERE myRate.liked = theirRate.liked " +
            "AND NOT exists((user)-[:RATED]->(potentialMovie)) " +
            "RETURN potentialMovie, count(similarUser) as frequency " +
            "ORDER BY frequency DESC " +
            "LIMIT 5")
    Collection<Movie> getMovieRecommendations(String userId);
}