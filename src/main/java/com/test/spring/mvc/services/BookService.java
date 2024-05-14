package com.test.spring.mvc.services;

import com.test.spring.mvc.models.Book;
import com.test.spring.mvc.models.Person;
import com.test.spring.mvc.repositories.BooksRepository;
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

    public List<Book> findAll() {
        return booksRepository.findAll();
    }

    public Book findOne(Long id) {
        Optional<Book> book = booksRepository.findById(id);
        return book.orElse(null);
    }

    public Optional<Person> findBookOwner(Long id) {
        return Optional.ofNullable(this.findOne(id).getOwner());
    }

    @Transactional
    public void save(Book book) {
        booksRepository.save(book);
    }

    @Transactional
    public void update(Long id, Book updatedBook) {
        updatedBook.setId(id);
        booksRepository.save(updatedBook);
    }

    @Transactional
    public void remove(Long id) {
        booksRepository.deleteById(id);
    }

    @Transactional
    public void assign(Long id, Person selectedPerson) {
        this.findOne(id).setOwner(selectedPerson);
    }

    @Transactional
    public void release(Long id) {
        this.findOne(id).setOwner(null);
    }
}
