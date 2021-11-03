package com.example.recommendationapi.repository.neo4j;

import com.example.recommendationapi.models.Movie;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import reactor.core.publisher.Flux;

public interface MovieRepository extends ReactiveNeo4jRepository<Movie, Long> {
    @Query("MATCH (user:User {userId: $userId})-[myRate:RATED]->()<-[theirRate:RATED]-(similarUser:User)-[:RATED {liked: true}]->(potentialMovie:Movie) " +
            "WHERE myRate.liked = theirRate.liked " +
            "AND NOT exists((user)-[:RATED]->(potentialMovie)) " +
            "RETURN potentialMovie, count(similarUser) as frequency " +
            "ORDER BY frequency DESC " +
            "LIMIT 5")
    Flux<Movie> getMovieRecommendations(String userId);
}