package com.tpcmswebadmin.domain.authentication;

public class SignInUsernameModel {

    private String username;

    public SignInUsernameModel() {
    }

    public SignInUsernameModel(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
