package com.demo.library_management.controller;

import com.demo.library_management.dto.BookResponse;
import com.demo.library_management.entity.Book;
import com.demo.library_management.repository.BookRepository;
import com.demo.library_management.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public Page<BookResponse> getAllBooks(
            @PageableDefault(size = 10, sort = "title") Pageable pageable
    ) {
        return bookService.getAllBooks(pageable);
    }

    @GetMapping("/search")
    public List<BookResponse> search(@RequestParam String title) {
        return bookService.searchByTitle(title);
    }

    @GetMapping("/by-author")
    public List<BookResponse> byAuthor(@RequestParam String authorName) {
        return bookService.getByAuthorName(authorName);
    }
}
