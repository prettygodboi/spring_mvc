package com.test.spring.mvc.springcourse.controllers;

import com.test.spring.mvc.springcourse.dao.BookDao;
import com.test.spring.mvc.springcourse.dao.PersonDao;
import com.test.spring.mvc.springcourse.models.Book;
import com.test.spring.mvc.springcourse.models.Person;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BooksController {
    BookDao bookDao;
    PersonDao personDao;

    public BooksController(BookDao bookDao, PersonDao personDao) {
        this.bookDao = bookDao;
        this.personDao = personDao;
    }

    @GetMapping()
    public String showBooks(Model model) {
        model.addAttribute("books", bookDao.getAllBooks());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String showBook(@PathVariable(value = "id") int id, Model model, @ModelAttribute(value = "person") Person person) {
        model.addAttribute("book", bookDao.getBookById(id));
        Optional<Person> bookOwner = bookDao.getBookOwner(id);

        if (bookOwner.isPresent()) {
            model.addAttribute("owner", bookOwner.get());
        } else {
            model.addAttribute("people", personDao.getPeople());
        }
        return "books/show";
    }

    @GetMapping("/new")
    public String addNewBookPage(@ModelAttribute(value = "book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String addNewBook(@ModelAttribute(value = "book") @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return "books/new";
        }
        bookDao.addNew(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String editBookPage(@PathVariable(value = "id") int id, Model model) {
        model.addAttribute("book", bookDao.getBookById(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String editBook(@PathVariable(value = "id") int id, @ModelAttribute(value = "book") Book book) {
        bookDao.editBook(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable(value = "id")int id) {
        bookDao.deleteBook(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/realise")
    public String release(@PathVariable(value = "id")int id) {
        bookDao.realise(id);
        return "redirect:/books/"+id;
    }

    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable(value = "id")int id, @ModelAttribute("person")Person person) {
        bookDao.assign(id,person);
        return "redirect:books/"+id;
    }
}
