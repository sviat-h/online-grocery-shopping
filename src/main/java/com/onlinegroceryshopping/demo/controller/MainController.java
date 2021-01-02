package com.onlinegroceryshopping.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/main")
    public String mainPage(Model model) {
        model.addAttribute("msg", "Welcome, user!");

        return "main";
    }
}
