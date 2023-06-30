package com.example.extra.domain.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping(value="/", method= {RequestMethod.POST})
@Controller
public class HomeController {

    @GetMapping("/")
    public String indexForm() {

        return "index";
    }
}
