package com.epam.pollWebApp.service;



import com.epam.pollWebApp.connection.DBConnectionProvider;
import com.epam.pollWebApp.model.Poll;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component("pollService")
public class PollServiceImpl implements PollService<Poll> {

    @Override
    public List<Poll> findAll() {
        List<Poll> polls = new ArrayList<>();

        try {
            String query = "SELECT * From poll";
            PreparedStatement preparedStatement = DBConnectionProvider.connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Poll poll = new Poll();
                poll.setId(resultSet.getLong("id"));
                poll.setName(resultSet.getString("name"));
                poll.setDescription(resultSet.getString("description"));
                polls.add(poll);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return polls;
    }

    @Override
    public Poll findById(long id) {
        Poll poll= new Poll();

        try {
            String query = "SELECT * FROM poll WHERE id = ? ";

            PreparedStatement preparedStatement = DBConnectionProvider.connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                poll.setId(resultSet.getLong("id"));
                poll.setName(resultSet.getString("name"));
                poll.setDescription(resultSet.getString("description"));

//                return poll;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return poll;
    }
}

