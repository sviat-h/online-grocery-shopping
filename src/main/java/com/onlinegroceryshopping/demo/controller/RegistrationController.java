package com.onlinegroceryshopping.demo.controller;

import com.onlinegroceryshopping.demo.model.User;
import com.onlinegroceryshopping.demo.model.enums.Role;
import com.onlinegroceryshopping.demo.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Objects;

@RequiredArgsConstructor
@Controller
public class RegistrationController {

    private final UserRepo userRepo;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Model model) {
        User userFromDb = userRepo.findByUsername(user.getUsername());

        if (Objects.nonNull(userFromDb)) {
            model.addAttribute("message", "Username already exist!");
            return "registration";
        }

        user.setActive(false);
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);

        return "redirect:/login";
    }
}
