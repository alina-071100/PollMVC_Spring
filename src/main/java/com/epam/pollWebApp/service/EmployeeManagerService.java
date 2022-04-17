package com.epam.pollWebApp.service;

import com.epam.pollWebApp.model.UserRegister;

import java.sql.Date;
import java.util.List;

public interface EmployeeManagerService<T,E> {
    List<T> getAll();
    void createUser(T obj);

    void updateUser(T obj);
    UserRegister pollResultById(long id);

    boolean existEmailAndPass(String username, String password);

    boolean getByUsernameAndPassword(UserRegister userRegister);

    UserRegister getUsernameAndPass(String username, String password);

    void updateUserPassword(T obj);

    void updateDate(int id, Date date);

    void updateResult(int id, String description);

}
