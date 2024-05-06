package com.test.spring.mvc.springcourse.controllers;

import com.test.spring.mvc.springcourse.dao.PersonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/batch")
public class BatchController {
    private final PersonDao personDao;

    @Autowired
    public BatchController(PersonDao personDao) {
        this.personDao = personDao;
    }

    @GetMapping()
    public String index() {
        return "batch/index";
    }

    @GetMapping("/without")
    public String noBatch() {
        personDao.testMultipleUpdate();
        return "redirect:/people";
    }

    @GetMapping("/with")
    public String nBatch() {
        personDao.testBatchUpdate();
        return "redirect:/people";
    }
}
