package com.test.spring.mvc.services;

import com.test.spring.mvc.models.Book;
import com.test.spring.mvc.models.Person;
import com.test.spring.mvc.repositories.BooksRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BookService {
    private final BooksRepository booksRepository;

    public BookService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

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
        Optional<Book> book = booksRepository.findById(id);
        return book.orElse(null);
    }

    public Person findBookOwner(Long id) {
        Optional<Book> book = booksRepository.findById(id);

        return book.get().getOwner();
    }

    public List<Book> findByTitle(String title) {
        return booksRepository.findByTitleStartingWith(title);
    }

    @Transactional
    public void save(Book book) {
        booksRepository.save(book);
    }

    @Transactional
    public void update(Long id, Book updatedBook) {
        Book book = booksRepository.findById(id).get();
        updatedBook.setId(id);
        updatedBook.setOwner(book.getOwner());
        booksRepository.save(updatedBook);
    }

    @Transactional
    public void remove(Long id) {
        booksRepository.deleteById(id);
    }

    @Transactional
    public void assign(Long id, Person selectedPerson) {
        booksRepository.findById(id).ifPresent(book -> {
            book.setOwner(selectedPerson);
        });
        this.findOne(id).setOwner(selectedPerson);
    }

    @Transactional
    public void release(Long id) {
        booksRepository.findById(id).ifPresent(book -> {
            book.setOwner(null);
        });
        this.findOne(id).setOwner(null);
    }
}
