package com.test.spring.mvc.controllers;


import com.test.spring.mvc.models.Person;
import com.test.spring.mvc.services.BookService;
import com.test.spring.mvc.services.PeopleService;

import com.test.spring.mvc.util.PersonValidator;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PersonValidator personValidator;
    private final PeopleService peopleService;

    public PeopleController(PeopleService peopleService, PersonValidator personValidator) {
        this.personValidator = personValidator;
        this.peopleService = peopleService;
    }

    @GetMapping()
    public String fetchAll(Model model){
        model.addAttribute("peopleList", peopleService.findAll());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String fetchById(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("person", peopleService.findOne(id));
        model.addAttribute("books", peopleService.findBooksByPersonId(id));
        return "people/show";
    }

    @GetMapping("/new")
    public String createPersonPage(@ModelAttribute(value = "person") Person person) {
        return "people/new";
    }

    @PostMapping()
    public String createPerson(@ModelAttribute(value = "person") @Valid Person person, BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) {
            return "people/new";
        }
        peopleService.save(person);
        return "redirect:people";
    }

    @GetMapping("/{id}/edit")
    public String editPage(@PathVariable(value = "id") Long id, Model model){
        model.addAttribute("person", peopleService.findOne(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String updatePerson(@PathVariable(value = "id") Long id, @ModelAttribute(value = "person") @Valid Person person, BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) {
            return "people/edit";
        }
        peopleService.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable(value = "id") Long id) {
        peopleService.remove(id);
        return "redirect:/people";
    }
}
