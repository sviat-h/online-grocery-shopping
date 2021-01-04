package com.onlinegroceryshopping.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GreetingController {

    @GetMapping("/")
    public String greetingPage(Model model) {
        model.addAttribute("welcome", "Welcome to online grocery shopping!");

        return "greeting";
    }
}
