package com.test.spring.mvc.repositories;

import com.test.spring.mvc.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BooksRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitleStartingWith(String title);
}
