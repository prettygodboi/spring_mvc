package com.test.spring.mvc.springcourse.controllers;

import com.test.spring.mvc.springcourse.dao.PersonDao;
import com.test.spring.mvc.springcourse.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PersonDao dao;

    public PeopleController(PersonDao dao) {
        this.dao = dao;
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
    public String createPersonPage(Model model) {
        model.addAttribute("person", new Person());
        return "people/new";
    }

    @PostMapping
    public String createPerson(@ModelAttribute(value = "person") Person person) {
        dao.createNewPerson(person);
        return "redirect:people";
    }
}
