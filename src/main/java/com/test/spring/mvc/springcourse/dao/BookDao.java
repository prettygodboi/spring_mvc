package com.test.spring.mvc.springcourse.dao;

import com.test.spring.mvc.springcourse.models.Book;
import com.test.spring.mvc.springcourse.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getAllBooks() {
        return jdbcTemplate.query("SELECT * FROM books", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book getBookById(int id) {
        return jdbcTemplate.query("SELECT * FROM books WHERE book_id=?", new BeanPropertyRowMapper<>(Book.class), new Object[]{id})
                .stream().findAny().orElse(null);
    }

    public void addNew(Book book) {
        jdbcTemplate.update("INSERT INTO books(title,author,age) VALUES (?,?,?)", book.getTitle(), book.getAuthor(), book.getAge());
    }

    public void editBook(int id, Book book) {
        jdbcTemplate.update("UPDATE books SET title=?, author=?, age=? WHERE book_id=?", book.getTitle(), book.getAuthor(), book.getAge(), id);
    }

    public void deleteBook(int id) {
        jdbcTemplate.update("DELETE FROM books WHERE book_id=?", id);
    }


    public Optional<Person> getBookOwner(int id) {
        return jdbcTemplate.query("SELECT people.* FROM people JOIN books ON people.id = books.person_id WHERE people.id=?", new BeanPropertyRowMapper<>(Person.class), new Object[]{id})
                .stream().findAny();
    }

    public void assign(int id, Person selectedPerson) {
        jdbcTemplate.update("UPDATE books SET person_id=? WHERE book_id=?",selectedPerson.getId() ,id);
    }

    public void realise(int id) {
        jdbcTemplate.update("UPDATE books SET person_id=NULL WHERE book_id=?", id);
    }
}
