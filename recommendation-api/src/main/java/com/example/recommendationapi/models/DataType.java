package com.example.recommendationapi.models;

public enum DataType {
    MOVIE("movie"),
    SERIES("tv");

    private String type;

    DataType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
