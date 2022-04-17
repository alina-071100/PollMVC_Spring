package com.epam.pollWebApp.controller;


import com.epam.pollWebApp.model.Answer;
import com.epam.pollWebApp.model.Question;
import com.epam.pollWebApp.service.AnswerService;
import com.epam.pollWebApp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class QuestionAnswerController {

    private QuestionService questionDAO;
    private AnswerService answerDAO;

    @Autowired
    public QuestionAnswerController(QuestionService questionDAO, AnswerService answerDAO) {
        this.questionDAO = questionDAO;
        this.answerDAO = answerDAO;
    }

    @GetMapping("/questions/{id}")
    public String questionsAndAnswers(@PathVariable("id") int id, Model model) {
        Question question = new Question();

        question.setPoll_id(id);
        List<Question> questions = questionDAO.findByPollId(question.getPoll_id());

        Answer answer = new Answer();
        List<Answer> answers = new ArrayList<>();

        for (Question quest : questions) {
            long questId = quest.getId();
            answer.setQuestion_id(questId);
            answers.addAll(answerDAO.findByQuestionId(answer.getQuestion_id()));
        }
        model.addAttribute("questions", questions);
        model.addAttribute("answers", answers);
        return "question";
    }
}
