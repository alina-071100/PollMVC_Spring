package com.epam.pollWebApp.service;

import com.epam.pollWebApp.model.Question;

import java.util.List;

public interface QuestionService extends PollService<Question> {

    List<Question> findByPollId(long pollId);
}
