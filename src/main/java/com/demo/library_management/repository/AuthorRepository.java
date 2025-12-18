package com.demo.library_management.repository;


import com.demo.library_management.entity.Author;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    Optional<Author> findById(Long id);


    @Query("""
        select a from Author a
        left join fetch a.books
        where a.id = :id
    """)
    Optional<Author> findByIdWithBooks(@Param("id") Long id);
}
