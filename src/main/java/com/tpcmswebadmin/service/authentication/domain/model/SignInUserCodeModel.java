package com.tpcmswebadmin.service.authentication.domain.model;

public class SignInUserCodeModel {

    private String userCode;

    public SignInUserCodeModel() {
    }

    public SignInUserCodeModel(String userCode) {
        this.userCode = userCode;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
}
