package com.tpcmswebadmin.service.authentication.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignInPassCodeModel {

    private String passCode1;

    private String passCode2;

    private String passCode3;

    private String passCodeFull;

    private String userCode;

    private String userName;

   private String mobileAppDeviceId;
}
