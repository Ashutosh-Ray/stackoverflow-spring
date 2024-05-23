package com.stackoverflow.dtos;

public class GTPDto {
    String query;

    public GTPDto() {
    }

    public GTPDto(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
