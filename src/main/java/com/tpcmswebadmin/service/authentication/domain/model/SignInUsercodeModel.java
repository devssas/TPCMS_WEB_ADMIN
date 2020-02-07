package com.tpcmswebadmin.service.authentication.domain.model;

public class SignInUsercodeModel {

    private String userCode;

    public SignInUsercodeModel() {
    }

    public SignInUsercodeModel(String userCode) {
        this.userCode = userCode;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
}
