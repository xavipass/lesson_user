package com.example.extra.domain.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

public class HomeController {

    @GetMapping("/")
    public String indexForm() {

        return "index";
    }
}
