package com.epam.pollWebApp.service;

import com.epam.pollWebApp.model.Result;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.epam.pollWebApp.connection.DBConnectionProvider.connection;

@Component("resultService")
public class ResultServiceImpl implements ResultService {

    @Override
    public List<Result> findAll() {
        List<Result> listResultAdd = new ArrayList<>();
        try {
            String query = "SELECT * From Result";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Result result = new Result();
                result.setId(resultSet.getInt("id"));
                result.setExplanation(resultSet.getString("explanation"));
                result.setMinScore(resultSet.getInt("min_score"));
                result.setMaxScore(resultSet.getInt("max_score"));
                result.setResultpoll_id(resultSet.getInt("resultpoll_id"));
                listResultAdd.add(result)  ;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listResultAdd;
    }


    @Override
    public Result findById(long id) {
        Result result= new Result();
        try {
            String query = "SELECT * FROM result WHERE id = ? ";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                result.setId(resultSet.getInt("id"));
                result.setExplanation(resultSet.getString("explanation"));
                result.setMinScore(resultSet.getInt("min_score"));
                result.setMaxScore(resultSet.getInt("max_score"));
                result.setResultpoll_id(resultSet.getInt("resultpoll_id"));


//                return result;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Result> findByPollId(long pollId) {

        List<Result> listResultAdd = new ArrayList<>();
        try {
            String query = "SELECT * FROM result WHERE resultpoll_id = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, pollId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Result result = new Result();
                result.setId(resultSet.getInt("id"));
                result.setExplanation(resultSet.getString("explanation"));
                result.setMinScore(resultSet.getInt("min_score"));
                result.setMaxScore(resultSet.getInt("max_score"));
                result.setResultpoll_id(resultSet.getInt("resultpoll_id"));
                listResultAdd.add(result)  ;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listResultAdd;
    }

    @Override
    public Result findByScore(long pollId, long score) {
        List<Result> resultList = new ArrayList<>();
        Result result = new Result();
        try {
            String query = "SELECT * FROM result WHERE resultpoll_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, pollId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result.setId(resultSet.getInt("id"));
                result.setExplanation(resultSet.getString("explanation"));
                result.setMinScore(resultSet.getLong("min_score"));
                result.setMaxScore(resultSet.getLong("max_score"));
                resultList.add(result);
                if (score >= result.getMinScore() && score <= result.getMaxScore()) {
                    return result;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    }





