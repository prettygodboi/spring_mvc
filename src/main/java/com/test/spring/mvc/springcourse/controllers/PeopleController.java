package com.test.spring.mvc.springcourse.controllers;

import com.test.spring.mvc.springcourse.dao.PersonDao;
import com.test.spring.mvc.springcourse.models.Person;
import com.test.spring.mvc.springcourse.util.PersonValidator;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PersonDao dao;
    private final PersonValidator personValidator;

    public PeopleController(PersonDao dao, PersonValidator personValidator) {
        this.dao = dao;
        this.personValidator = personValidator;
    }

    @GetMapping()
    public String fetchAll(Model model){
        model.addAttribute("peopleList", dao.getPeople());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String fetchById(@PathVariable(value = "id") int id, Model model) {
        model.addAttribute("person", dao.getPersonById(id));
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
        dao.createNewPerson(person);
        return "redirect:people";
    }

    @GetMapping("/{id}/edit")
    public String editPage(@PathVariable(value = "id") int id, Model model){
        model.addAttribute("person", dao.getPersonById(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String updatePerson(@PathVariable(value = "id") int id, @ModelAttribute(value = "person") @Valid Person person, BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) {
            return "people/edit";
        }
        dao.editPerson(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable(value = "id") int id) {
        dao.deletePerson(id);
        return "redirect:/people";
    }
}
