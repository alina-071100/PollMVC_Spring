package com.epam.pollWebApp.controller;


import com.epam.pollWebApp.model.Poll;
import com.epam.pollWebApp.model.UserRegister;
import com.epam.pollWebApp.service.EmployeeManagerService;
import com.epam.pollWebApp.service.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.sql.Date;
import java.util.List;

@Controller
@SessionAttributes("user")
public class PollController {

    PollService pollService;
    EmployeeManagerService employeeManagerService;

    int today = 0;
    int day = 7;
    Date date = new Date(0000,00,00);

    @Autowired
    public  PollController(PollService pollService, EmployeeManagerService employeeManagerService){
        this.pollService = pollService;
        this.employeeManagerService = employeeManagerService;
    }

    @GetMapping("/poll")
    public  String openPollPage(@ModelAttribute("user")UserRegister userRegister, Model model){
        int id = userRegister.getId();
        Date result_date = userRegister.getDate();

        if(result_date == null || result_date.toLocalDate().getDayOfMonth() + 7 < today) {
            employeeManagerService.updateDate(id, date);
            List<Poll> all = pollService.findAll();
            model.addAttribute("poll", all);
            return "loginpoll";
        }else  {
            int resultDate = result_date.toLocalDate().getDayOfMonth();
            today = LocalDate.now().getDayOfMonth();
            day += resultDate;
            if (day >= today) {
                UserRegister byId = employeeManagerService.pollResultById(id);
                model.addAttribute("poll_error", "Вы можете снова принять участие в опросе через неделю \n Ваш результат -");
                model.addAttribute("poll_result", byId);
                return "results";
            }
        }
        return null;
   }
        
}
