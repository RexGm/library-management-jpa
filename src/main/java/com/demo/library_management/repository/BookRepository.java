package com.demo.library_management.repository;

import com.demo.library_management.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByAuthor_Name(String name);
    
    List<Book> findByTitleContainingIgnoreCase(String title);
}
