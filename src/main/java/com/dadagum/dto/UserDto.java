package com.dadagum.dto;

import com.dadagum.bean.User;

public class UserDto {

    private String username;
    private String email;

    public UserDto(){

    }

    public UserDto(User user){
        username = user.getUsername();
        email = user.getEmail();
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
}
