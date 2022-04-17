package com.epam.pollWebApp.controller;

import com.epam.pollWebApp.model.UserRegister;
import com.epam.pollWebApp.service.EmployeeManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@SessionAttributes("user")
public class Change {
    UserRegister userRegister = new UserRegister();
    private EmployeeManagerService employeeDAO;

    @Autowired
    public Change(EmployeeManagerService employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @PostMapping("changePassword")
    public String changePass(@RequestParam(value = "password", required = false) String password,
                             @RequestParam(value = "passwordConfirm", required = false) String passwordConfirm,
                             @RequestParam(value = "email", required = false) String email,
                             Model model) {

        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        String encodePassword = Base64.getEncoder().encodeToString(password.getBytes());
        String encodePasswordConfirm = Base64.getEncoder().encodeToString(passwordConfirm.getBytes());
        userRegister.setEmail(email);
        userRegister.setPassword(encodePassword);
        if (matcher.matches() && email.equals(userRegister.getEmail()) && encodePassword.contentEquals(encodePasswordConfirm)) {
            employeeDAO.updateUserPassword(userRegister);
            model.addAttribute("changePass", "Your password has been changed");
            return "index";
        } else {
            return "errorpage";
        }
    }

    @PostMapping("changeUsername")
    public String changeUsername(@RequestParam(value = "email", required = false) String email,
                                 @RequestParam(value = "username", required = false) String username,
                                 @RequestParam(value = "usernameConfirm", required = false) String usernameConfirm,
                                 Model model){
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        userRegister.setEmail(email);
        userRegister.setUsername(username);
        if (matcher.matches() && email.equals(userRegister.getEmail()) && username.contentEquals(usernameConfirm)) {
            employeeDAO.updateUser(userRegister);
            model.addAttribute("changeUsername", "Your username has been changed");
            return "index";
        } else {
            return "errorpage";
        }
    }
}
