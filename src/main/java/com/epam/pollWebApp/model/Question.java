package com.epam.pollWebApp.model;

import java.util.List;


public class Question {

    private long id;

    private String text;

    private List<Answer> answers;

    private long poll_id;


    public Question(long id, String text, List<Answer> answers, long pollId) {
        this.id = id;
        this.text = text;
        this.answers = answers;
        this.poll_id = pollId;
    }

    public Question() {
    }

    public long getPoll_id() {
        return poll_id;
    }

    public void setPoll_id(long poll_id) {
        this.poll_id = poll_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }


    @Override
    public String toString() {
        return "Question{" + "id=" + id + ", text='" + text + '\'' + ", answers=" + answers + '}';
    }
}


