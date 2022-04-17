package com.epam.pollWebApp.service;



import com.epam.pollWebApp.connection.DBConnectionProvider;
import com.epam.pollWebApp.model.Answer;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component("answerService")
public class AnswerServiceImpl implements AnswerService {

    @Override
    public List<Answer> findAll() {
        List<Answer> listAnswerAdd = new ArrayList<>();
        try {
            String query = "SELECT * From answer";
            PreparedStatement preparedStatement = DBConnectionProvider.connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Answer answer = new Answer();
                answer.setId(resultSet.getLong("id"));
                answer.setText(resultSet.getString("answer_text"));
                answer.setWeight(resultSet.getLong("weight"));
                answer.setQuestion_id(resultSet.getLong("question_id"));
                listAnswerAdd.add(answer);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listAnswerAdd;
    }

    @Override
    public Answer findById(long id) {
        Answer answer= new Answer();

        try {
            String query = "SELECT * FROM answer WHERE id = ? ";

            PreparedStatement preparedStatement = DBConnectionProvider.connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                answer.setId(resultSet.getLong("id"));
                answer.setText(resultSet.getString("answer_text"));
                answer.setWeight(resultSet.getLong("weight"));
                answer.setQuestion_id(resultSet.getLong("question_id"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return answer;
    }


    @Override
    public List<Answer> findByQuestionId(long questionId) {
        List<Answer> listAnswerAdd = new ArrayList<>();
        try {
//            String query = "SELECT * FROM answer WHERE question_id = ?";
            String query = "SELECT * FROM question INNER JOIN answer ON question.quest_id=answer.question_id WHERE question_id=?";
            PreparedStatement preparedStatement = DBConnectionProvider.connection.prepareStatement(query);
            preparedStatement.setLong(1, questionId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Answer answer= new Answer();
                answer.setId(resultSet.getLong("id"));
                answer.setText(resultSet.getString("answer_text"));
                answer.setWeight(resultSet.getLong("weight"));
                answer.setQuestion_id(resultSet.getLong("question_id"));
listAnswerAdd.add(answer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

       return listAnswerAdd;
    }}