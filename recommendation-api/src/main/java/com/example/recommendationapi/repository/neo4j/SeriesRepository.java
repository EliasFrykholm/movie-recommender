package com.example.recommendationapi.repository.neo4j;

import com.example.recommendationapi.models.Series;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import reactor.core.publisher.Flux;

public interface SeriesRepository extends ReactiveNeo4jRepository<Series, Long> {
    @Query("MATCH (user:User {userId: $userId})-[myRate:RATED]->()<-[theirRate:RATED]-(similarUser:User)-[:RATED {liked: true}]->(potentialSeries:Series) " +
            "WHERE myRate.liked = theirRate.liked " +
            "AND NOT exists((user)-[:RATED]->(potentialSeries)) " +
            "RETURN potentialSeries, count(similarUser) as frequency " +
            "ORDER BY frequency DESC " +
            "LIMIT 5")
    Flux<Series> getSeriesRecommendations(String userId);
}