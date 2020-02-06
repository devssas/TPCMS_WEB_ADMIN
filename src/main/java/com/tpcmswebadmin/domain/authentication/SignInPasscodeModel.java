package com.tpcmswebadmin.domain.authentication;

public class SignInPasscodeModel {

    private String passCode;

    public SignInPasscodeModel() {
    }

    public SignInPasscodeModel(String passCode) {
        this.passCode = passCode;
    }

    public String getPassCode() {
        return passCode;
    }

    public void setPassCode(String passCode) {
        this.passCode = passCode;
    }
}
