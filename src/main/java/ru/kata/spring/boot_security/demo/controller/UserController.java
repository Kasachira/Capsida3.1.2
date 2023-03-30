package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;


import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String getHomePage(Model model) {
        List<String> messages = new ArrayList<>();
        messages.add("Hello World!");
        model.addAttribute("messages", messages);
        return "hello/index";
    }

    @GetMapping(value = "/login")
    public String getLoginPage() {
        return "hello/login";
    }

    @GetMapping(value = "/user")
    public String getUserPage(Model model, Authentication auth) {

        if (auth.isAuthenticated()) {
            String userName = auth.getName();
            User user = (User) userService.loadUserByUsername(userName);
            model.addAttribute("userOne", user);
        }
        return "user/user";
    }
}
