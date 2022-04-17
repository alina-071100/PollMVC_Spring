package com.epam.pollWebApp.controller;

import com.epam.pollWebApp.model.UserRegister;
import com.epam.pollWebApp.service.EmployeeManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserRegisterController {

    private EmployeeManagerService employeeManagerService;

    @Autowired
    public UserRegisterController(EmployeeManagerService employeeManagerService) {
        this.employeeManagerService = employeeManagerService;
    }

    @GetMapping("/register")
    public String openRegisterPage(Model model) {
        model.addAttribute("user", new UserRegister());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") UserRegister user, Model model ) {
       employeeManagerService.createUser(user);
        model.addAttribute("registerMessage", "You have successfully registered!");
        return "registersuccessfully";
    }
}
