package com.example.recommendationapi.models;

import org.springframework.data.neo4j.core.schema.Id;

public class User {
    @Id
    public final String userId;

    public User(String userId) {
        this.userId = userId;
    }
}
