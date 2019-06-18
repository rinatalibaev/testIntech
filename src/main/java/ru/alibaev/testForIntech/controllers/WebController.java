package ru.alibaev.testForIntech.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
	
    @GetMapping(value= {"/"})
    public String themespage(){
        return "redirect:/themes";
    }

    @GetMapping(value="/login")
    public String loginDialog () {
        return "login";
    }

    @GetMapping(value="/registration")
    public String registrationDialog () {
        return "registration";
    }
}
