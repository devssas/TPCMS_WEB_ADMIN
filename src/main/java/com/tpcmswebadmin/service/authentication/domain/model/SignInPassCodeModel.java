package com.tpcmswebadmin.service.authentication.domain.model;

public class SignInPassCodeModel {

    private String passCode;

    public SignInPassCodeModel() {
    }

    public SignInPassCodeModel(String passCode) {
        this.passCode = passCode;
    }

    public String getPassCode() {
        return passCode;
    }

    public void setPassCode(String passCode) {
        this.passCode = passCode;
    }
}
