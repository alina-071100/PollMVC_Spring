package com.epam.pollWebApp.service;


import com.epam.pollWebApp.model.Answer;

import java.util.List;
public interface AnswerService extends PollService<Answer> {

    List<Answer> findByQuestionId(long questionId);
}
