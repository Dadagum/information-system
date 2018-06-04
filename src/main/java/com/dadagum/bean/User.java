package com.dadagum.bean;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;

public class User {

    private int user_id;

    @Length(min = 3, max = 20)
    private String username;

    @Email
    private String email;

    @Length(min = 5, max = 25)
    private String password;

    private String priority;

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", priority='" + priority + '\'' +
                '}';
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
