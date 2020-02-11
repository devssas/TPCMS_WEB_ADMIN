package com.tpcmswebadmin.service.authentication.domain.model;

public class SignInUserCodeModel {

    private String userCode1;

    private String userCode2;

    private String userCode3;

    private String userCode4;

    private String userCode5;

    private String username;

    public SignInUserCodeModel() {
    }

    public SignInUserCodeModel(String userCode1, String userCode2, String userCode3, String userCode4, String userCode5,  String username) {
        this.userCode1 = userCode1;
        this.userCode2 = userCode2;
        this.userCode3 = userCode3;
        this.userCode4 = userCode4;
        this.userCode5 = userCode5;
        this.username = username;
    }

    public String getUserCode1() {
        return userCode1;
    }

    public void setUserCode1(String userCode1) {
        this.userCode1 = userCode1;
    }

    public String getUserCode2() {
        return userCode2;
    }

    public void setUserCode2(String userCode2) {
        this.userCode2 = userCode2;
    }

    public String getUserCode3() {
        return userCode3;
    }

    public void setUserCode3(String userCode3) {
        this.userCode3 = userCode3;
    }

    public String getUserCode4() {
        return userCode4;
    }

    public void setUserCode4(String userCode4) {
        this.userCode4 = userCode4;
    }

    public String getUserCode5() {
        return userCode5;
    }

    public void setUserCode5(String userCode5) {
        this.userCode5 = userCode5;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
