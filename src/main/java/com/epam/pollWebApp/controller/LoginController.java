package com.epam.pollWebApp.controller;

import com.epam.pollWebApp.model.UserRegister;
import com.epam.pollWebApp.service.EmployeeManagerService;
import com.epam.pollWebApp.service.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@SessionAttributes("user")
public class LoginController {
    UserRegister userRegister = new UserRegister();

    private final EmployeeManagerService employeeManagerService;
    private final PollService pollService;

    @Autowired
    public LoginController(EmployeeManagerService employeeManagerService, PollService pollService) {
        this.employeeManagerService = employeeManagerService;
        this.pollService = pollService;
    }

    @GetMapping("/login")
    public String openLoginPage() {
        return "index";
    }

    @PostMapping("/login")
    public String validDate(@RequestParam(value = "username", required = false)
                                    String username, @RequestParam(value = "password", required = false) String password,
                            Model model) {
        if (!employeeManagerService.existEmailAndPass(username, password)) {
            model.addAttribute("errorMassage", "Something wrong");
            return "index";
        } else {
            userRegister.setUsername(username);
            userRegister.setPassword(password);
            if (employeeManagerService.getByUsernameAndPassword(userRegister)) {
                model.addAttribute("username", username);
                model.addAttribute("password", password);
                List<UserRegister> userRegisterList = employeeManagerService.getAll();
                for (UserRegister register : userRegisterList) {
                    model.addAttribute("user", register);
                }
                model.addAttribute("polls", pollService.findAll());
            }else{
                model.addAttribute("errorMeesage","Something wrong");
                return "index";
            }

            return "loginpoll";
        }
    }
}


