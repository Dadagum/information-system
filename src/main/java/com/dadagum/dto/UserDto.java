package com.dadagum.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

public class UserDto {

    @Pattern(regexp = "\\w{6,20}", message = "illegal username")
    private String username;

    @Pattern(regexp = "\\S{6,30}", message = "illegal password")
    private String password;

    /**
     * default construction
     */
    public UserDto() {
    }

    public UserDto(@Pattern(regexp = "\\w{6,20}", message = "illegal username") String username, @Pattern(regexp = "\\S{6,30}", message = "illegal password") String password, @Email String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    @Email

    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
