package com.demo.library_management.dto;

import java.util.List;

public class AuthorResponse {

    private Long id;
    private String name;
    private List<String> books;

    public AuthorResponse(Long id, String name, List<String> books) {
        this.id = id;
        this.name = name;
        this.books = books;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getBooks() {
        return books;
    }
}
