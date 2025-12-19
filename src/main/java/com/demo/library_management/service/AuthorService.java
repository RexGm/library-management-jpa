package com.demo.library_management.service;

import com.demo.library_management.entity.Author;
import com.demo.library_management.entity.Book;
import com.demo.library_management.repository.AuthorRepository;
import com.demo.library_management.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public AuthorService(AuthorRepository authorRepository,
                         BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    // no transaction (but it has to be)
    public Author getAuthor(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found"));
    }

    // correct
    @Transactional
    public Author getAuthorWithBooks(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found"));
    }

    // fetch join
    public Author getAuthorWithBooksFetchJoin(Long id) {
        return authorRepository.findByIdWithBooks(id)
                .orElseThrow(() -> new RuntimeException("Author not found"));
    }

    // add book to author
    @Transactional
    public void addBookToAuthor(Long authorId, String title) {
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new RuntimeException("Author not found"));

        Book book = new Book(title);
        author.addBook(book);

        // there is no save because it's cascade (I'm just testing situtaions)
    }
}
