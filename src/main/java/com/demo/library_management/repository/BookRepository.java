package com.demo.library_management.repository;

import com.demo.library_management.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("""
        select b from Book b
        join fetch b.author
    """)
    List<Book> findAllWithAuthor();

    @Query("""
        select b from Book b
        join fetch b.author
        where lower(b.title) like lower(concat('%', :title, '%'))
    """)
    List<Book> findByTitleWithAuthor(@Param("title") String title);

    @Query("""
        select b from Book b
        join fetch b.author
        where b.author.name = :authorName
    """)
    List<Book> findByAuthorNameWithAuthor(@Param("authorName") String authorName);

    @Query(
            value = """
        select b from Book b
        join fetch b.author
    """,
            countQuery = """
        select count(b) from Book b
    """
    )
    Page<Book> findAllWithAuthor(Pageable pageable);


}
