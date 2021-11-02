package com.example.recommendationapi.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;

public class RecommendationRequest {
    public final String userId;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public RecommendationRequest(String userId) {
        this.userId = userId;
    }
}
