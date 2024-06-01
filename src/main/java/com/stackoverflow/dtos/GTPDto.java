package com.stackoverflow.dtos;

public class GTPDto {
    private String query;

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
