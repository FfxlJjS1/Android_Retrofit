package com.example.retrofit.service;

public class UserToken {
    public String userToken;
    public String login;
    public String email;

    public UserToken(String userToken, String login, String email) {
        this.userToken = userToken;
        this.login = login;
        this.email = email;
    }
}
