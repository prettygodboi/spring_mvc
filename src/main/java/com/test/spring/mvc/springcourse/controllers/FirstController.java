package com.test.spring.mvc.springcourse.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FirstController {
    @GetMapping("/hello")
    public String sayHello(@RequestParam(value = "name", required = false) String name, Model model) {
        model.addAttribute("name", name);
        System.out.println("?????????");
        System.out.println(name);
        return "first/hello_world";
    }

    @GetMapping("/goodbye")
    public String goodByePage() {
        return "first/goodbye";
    }

    @GetMapping("/calculator")
    public String calculator(@RequestParam(value = "a", required = false) Integer a, @RequestParam(value = "b", required = false) Integer b, @RequestParam(value = "action", required = false) String action, Model model) {
        int result;
        switch (action) {
            case "multiplication":
                result = a * b;
                break;
            case "addition":
                result = a + b;
                break;
            case "subtraction":
                result = a - b;
                break;
            case  "division":
                result = a / b;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + action);
        }
        model.addAttribute("result", result);
        return "first/calculator";
    }
}
