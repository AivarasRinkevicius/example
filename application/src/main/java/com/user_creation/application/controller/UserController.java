package com.user_creation.application.controller;

import com.user_creation.application.model.User;
import com.user_creation.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    // localhost:8080/list-users
    @GetMapping("/list-users")
    public String showAll(ModelMap model) {
        model.put("users", userService.findAll());
        return "list-users";
    }

    @PostMapping("/add-user")
    public String add(@ModelAttribute("user") User user, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            return "user";
        }
        if (userService.add(user) != null) {
            return "redirect:/list-users";
        }
        else {
            return "redirect:/error";
        }
    }

    @GetMapping("/add-user")
    public String showAddPage(ModelMap model) {
        model.addAttribute("user", new User("","","","","",""));
        return "user";
    }

    @GetMapping("/update-user/{userId}")
    public String showUpdatePage(ModelMap model, @PathVariable int userId) {
        model.addAttribute("user", userService.findById(userId));
        return "user";
    }

    @PostMapping("/update-user/{userId}")
    public String update(@ModelAttribute("user") User user, BindingResult result) throws Exception {
        if(result.hasErrors()) {
            return "user";
        }
        userService.update(user);
        return "redirect:/list-users";
    }

    @GetMapping("/delete-user/{userId}")
    public String showUpdatePage(@PathVariable int userId) {
        userService.deleteById(userId);
        return "redirect:/list-users";
    }
}
