package com.demo.library_management.controller;

import com.demo.library_management.dto.AuthorResponse;
import com.demo.library_management.entity.Author;
import com.demo.library_management.service.AuthorService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/{id}")
    public AuthorResponse getAuthor(@PathVariable Long id) {

        Author author = authorService.getAuthorWithBooks(id);

        return new AuthorResponse(
                author.getId(),
                author.getName(),
                author.getBooks()
                        .stream()
                        .map(book -> book.getTitle())
                        .toList()
        );
    }
}
