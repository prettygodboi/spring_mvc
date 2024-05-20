package com.test.spring.mvc.services;

import com.test.spring.mvc.models.Book;
import com.test.spring.mvc.models.Person;
import com.test.spring.mvc.repositories.BooksRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BooksRepository booksRepository;

    public List<Book> findAll(boolean sortByYear) {
        if (sortByYear) {
            return booksRepository.findAll(Sort.by("age"));
        } else {
            return booksRepository.findAll();
        }
    }

    public List<Book> findWithPagination(Integer page, Integer booksPerPage, boolean sortByYear) {
        if (sortByYear) {
            return booksRepository.findAll(PageRequest.of(page,booksPerPage, Sort.by("age"))).getContent();
        } else {
            return booksRepository.findAll(PageRequest.of(page,booksPerPage)).getContent();
        }
    }

    public Book findOne(Long id) {
        return booksRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public List<Book> findByTitle(String title) {
        return booksRepository.findByTitleStartingWith(title);
    }

    public void save(Book book) {
        booksRepository.save(book);
    }

    public void update(Long id, Book updatedBook) {
        Book book = booksRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        updatedBook.setId(id);
        updatedBook.setOwner(book.getOwner());
        booksRepository.save(updatedBook);
    }

    public void remove(Long id) {
        booksRepository.deleteById(id);
    }

    public void assign(Long id, Person selectedPerson) {
        booksRepository.findById(id).ifPresent(book -> {
            book.setOwner(selectedPerson);
            booksRepository.save(book);
        });
    }

    public void release(Long id) {
        booksRepository.findById(id).ifPresent(book -> {
            book.setOwner(null);
            booksRepository.save(book);
        });
    }
}
