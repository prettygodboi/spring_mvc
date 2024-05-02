package com.test.spring.mvc.springcourse.controllers;

import com.test.spring.mvc.springcourse.dao.PersonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
