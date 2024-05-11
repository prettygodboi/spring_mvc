package com.test.spring.mvc.springcourse.dao;

import com.test.spring.mvc.springcourse.models.Book;
import com.test.spring.mvc.springcourse.models.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class BookDao {
    private final JdbcTemplate jdbcTemplate;

    private final SessionFactory sessionFactory;

    @Autowired
    public BookDao(JdbcTemplate jdbcTemplate, SessionFactory sessionFactory) {
        this.jdbcTemplate = jdbcTemplate;
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public List<Book> getAllBooks() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select b from Book b", Book.class).getResultList();
//        return jdbcTemplate.query("SELECT * FROM books", new BeanPropertyRowMapper<>(Book.class));
    }

    @Transactional
    public Book getBookById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Book.class, id);
//        return jdbcTemplate.query("SELECT * FROM books WHERE book_id=?", new BeanPropertyRowMapper<>(Book.class), new Object[]{id})
//                .stream().findAny().orElse(null);
    }

    @Transactional
    public void addNew(Book book) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(book);
//        jdbcTemplate.update("INSERT INTO books(title,author,age) VALUES (?,?,?)", book.getTitle(), book.getAuthor(), book.getAge());
    }

    @Transactional
    public void editBook(int id, Book updatedBook) {
        Session session = sessionFactory.getCurrentSession();
        Book book = session.get(Book.class,id);
        book.setTitle(updatedBook.getTitle());
        book.setAuthor(updatedBook.getAuthor());
        book.setAge(updatedBook.getAge());
//        jdbcTemplate.update("UPDATE books SET title=?, author=?, age=? WHERE book_id=?", book.getTitle(), book.getAuthor(), book.getAge(), id);
    }

    @Transactional
    public void deleteBook(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Book.class, id));
//        jdbcTemplate.update("DELETE FROM books WHERE book_id=?", id);
    }


    public Optional<Person> getBookOwner(int id) {
        return jdbcTemplate.query("SELECT people.* FROM people JOIN books ON people.id = books.person_id WHERE people.id=?", new BeanPropertyRowMapper<>(Person.class), new Object[]{id})
                .stream().findAny();
    }

    public void assign(int id, Person selectedPerson) {
        jdbcTemplate.update("UPDATE books SET person_id=? WHERE book_id=?", selectedPerson.getId(), id);
    }

    public void realise(int id) {
        jdbcTemplate.update("UPDATE books SET person_id=NULL WHERE book_id=?", id);
    }
}
