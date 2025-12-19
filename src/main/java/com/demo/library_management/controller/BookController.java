package com.demo.library_management.controller;

import com.demo.library_management.dto.BookResponse;
import com.demo.library_management.entity.Book;
import com.demo.library_management.repository.BookRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping
    public List<BookResponse> getAllBooks() {
        return bookRepository.findAllWithAuthor()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @GetMapping("/search")
    public List<BookResponse> search(@RequestParam String title) {
        return bookRepository.findByTitleWithAuthor(title)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @GetMapping("/by-author")
    public List<BookResponse> byAuthor(@RequestParam String authorName) {
        return bookRepository.findByAuthorNameWithAuthor(authorName)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private BookResponse toResponse(Book book) {
        return new BookResponse(
                book.getId(),
                book.getTitle(),
                book.getAuthor().getName()
        );
    }
}