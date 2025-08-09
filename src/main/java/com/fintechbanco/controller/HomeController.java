// src/main/java/com/fintechbanco/controller/HomeController.java
package com.fintechbanco.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping({"/", "/home"})
    public String home() {
        return "home"; // templates/home.html
    }
}

