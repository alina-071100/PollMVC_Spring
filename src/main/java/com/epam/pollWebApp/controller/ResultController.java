package com.epam.pollWebApp.controller;

import com.epam.pollWebApp.model.Result;
import com.epam.pollWebApp.model.UserRegister;
import com.epam.pollWebApp.service.AnswerService;
import com.epam.pollWebApp.service.EmployeeManagerService;
import com.epam.pollWebApp.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Controller
@SessionAttributes("user")
public class ResultController {

    AnswerService answerService;
    ResultService resultService;
    EmployeeManagerService employeeManagerService;

    long sum = 0;


    @Autowired
    public ResultController(AnswerService answerService, ResultService resultService, EmployeeManagerService employeeManagerService) {
        this.answerService = answerService;
        this.resultService = resultService;
        this.employeeManagerService = employeeManagerService;
    }

    @GetMapping("/results")
    public String results(@ModelAttribute("user") UserRegister userRegister, HttpServletRequest req, Model model) {

        String[] questionIds = req.getParameterValues("questionId");
        for (String questionId : questionIds) {
            String parameter = req.getParameter("marked" + questionId);
            if (parameter == null) {
                model.addAttribute("error", "You have not filled in all the fields, try again");
                return "errorpage";
            } else {
                long value = Long.parseLong(parameter);
                sum += value;
            }
        }

        String username = userRegister.getUsername();
        String password = userRegister.getPassword();
        int id = userRegister.getId();
        Result expByScore = resultService.findByScore(1, sum);

        Date now = Date.valueOf(LocalDate.now());

        employeeManagerService.getUsernameAndPass(username, password);
        employeeManagerService.updateDate(id, now);

        String explanation = expByScore.getExplanation();
        employeeManagerService.updateResult(id, explanation);
        UserRegister userResult = employeeManagerService.pollResultById(id);

        model.addAttribute("userResult", userResult);
        return "results";
    }
}
