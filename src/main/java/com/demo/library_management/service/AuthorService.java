package com.demo.library_management.service;

import com.demo.library_management.entity.Author;
import com.demo.library_management.entity.Book;
import com.demo.library_management.repository.AuthorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    // single source of truth for reading
    public Author getAuthorWithBooks(Long id) {
        return authorRepository.findByIdWithBooks(id)
                .orElseThrow(() -> new RuntimeException("Author not found"));
    }

    // write operation -> transactional
    @Transactional
    public void addBookToAuthor(Long authorId, String title) {
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new RuntimeException("Author not found"));

        Book book = new Book(title);
        author.addBook(book);
        // cascade handles persistence
    }
}
