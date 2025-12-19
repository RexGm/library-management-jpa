package com.demo.library_management.service;

import com.demo.library_management.dto.BookResponse;
import com.demo.library_management.entity.Book;
import com.demo.library_management.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Page<BookResponse> getAllBooks(Pageable pageable) {
        return bookRepository.findAllWithAuthor(pageable)
                .map(this::toResponse);
    }

    public List<BookResponse> searchByTitle(String title) {
        return bookRepository.findByTitleWithAuthor(title)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public List<BookResponse> getByAuthorName(String authorName) {
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
