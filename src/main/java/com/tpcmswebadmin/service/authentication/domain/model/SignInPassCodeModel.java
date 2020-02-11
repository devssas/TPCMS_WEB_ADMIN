package com.tpcmswebadmin.service.authentication.domain.model;

public class SignInPassCodeModel {

    private String passCode1;

    private String passCode2;

    private String passCode3;

    private String passCodeFull;

    private String userCode;

    private String userName;

    public SignInPassCodeModel() {
    }

    public SignInPassCodeModel(String passCode1, String passCode2, String passCode3, String passCodeFull, String userCode, String userName) {
        this.passCode1 = passCode1;
        this.passCode2 = passCode2;
        this.passCode3 = passCode3;
        this.passCodeFull = passCodeFull;
        this.userCode = userCode;
        this.userName = userName;
    }

    public String getPassCode1() {
        return passCode1;
    }

    public void setPassCode1(String passCode1) {
        this.passCode1 = passCode1;
    }

    public String getPassCode2() {
        return passCode2;
    }

    public void setPassCode2(String passCode2) {
        this.passCode2 = passCode2;
    }

    public String getPassCode3() {
        return passCode3;
    }

    public void setPassCode3(String passCode3) {
        this.passCode3 = passCode3;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassCodeFull() {
        return passCodeFull;
    }

    public void setPassCodeFull(String passCodeFull) {
        this.passCodeFull = passCodeFull;
    }
}
