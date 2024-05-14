package com.test.spring.mvc.controllers;

import com.test.spring.mvc.models.Book;
import com.test.spring.mvc.models.Person;
import com.test.spring.mvc.services.BookService;
import com.test.spring.mvc.services.PeopleService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final PeopleService peopleService;
    private final BookService bookService;

    public BooksController(PeopleService peopleService, BookService bookService) {
        this.peopleService = peopleService;
        this.bookService = bookService;
    }

    @GetMapping()
    public String showBooks(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String showBook(@PathVariable(value = "id") Long id, @ModelAttribute(value = "person") Person person, Model model) {
        model.addAttribute("book", bookService.findOne(id));
        Optional<Person> bookOwner = bookService.findBookOwner(id);

        if (bookOwner.isPresent()) {
            model.addAttribute("owner", bookOwner.get());
        } else {
            model.addAttribute("people", peopleService.findAll());
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
        bookService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String editBookPage(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("book", bookService.findOne(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String editBook(@PathVariable(value = "id") Long id, @ModelAttribute(value = "book") Book book) {
        bookService.update(id,book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable(value = "id")Long id) {
        bookService.remove(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable(value = "id")Long id) {
        bookService.release(id);
        return "redirect:/books/"+id;
    }

    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable(value = "id")Long id, @ModelAttribute("person")Person person) {
        bookService.assign(id,person);
        return "redirect:/books/"+id;
    }
}
