package com.epam.pollWebApp.model;

import java.sql.Date;

public class UserRegister {
    private int id;
    private String first_name;
    private String last_name;
    private String username;
    private String password;
    private String address;
    private String email;
    private Date date;
    private String poll_result;

    public UserRegister(int id, String first_name, String last_name, String username, String password, String address, String email, Date date, String poll_result) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.username = username;
        this.password = password;
        this.address = address;
        this.email = email;
        this.date = date;
        this.poll_result = poll_result;
    }

    public UserRegister() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPoll_result() {
        return poll_result;
    }

    public void setPoll_result(String poll_result) {
        this.poll_result = poll_result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserRegister{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", date=" + date +
                ", poll_result='" + poll_result + '\'' +
                '}';
    }
}
