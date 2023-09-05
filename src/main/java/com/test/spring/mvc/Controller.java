package com.test.spring.mvc;

import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.stereotype.Controller
public class Controller {

    @RequestMapping("/")
    public String showFirstView(){
        return "first-view";
    }
}
