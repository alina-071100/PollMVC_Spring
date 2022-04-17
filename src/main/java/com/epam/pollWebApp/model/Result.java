package com.epam.pollWebApp.model;

public class Result {

    private long id;

    private String explanation;

    private long minScore;

    private long maxScore;

    private  long resultpoll_id;


    public Result(long id, String explanation, long minScore, long maxScore) {
        this.id = id;
        this.explanation = explanation;
        this.minScore = minScore;
        this.maxScore = maxScore;
    }

    public Result() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public long getMinScore() {
        return minScore;
    }

    public void setMinScore(long minScore) {
        this.minScore = minScore;
    }

    public long getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(long maxScore) {
        this.maxScore = maxScore;
    }

    public long getResultpoll_id() {
        return resultpoll_id;
    }

    public void setResultpoll_id(long resultpoll_id) {
        this.resultpoll_id = resultpoll_id;
    }

    @Override
    public String toString() {
        return "Result{" +
                "id=" + id +
                ", explanation='" + explanation + '\'' +
                ", minScore=" + minScore +
                ", maxScore=" + maxScore +
                '}';
    }
}