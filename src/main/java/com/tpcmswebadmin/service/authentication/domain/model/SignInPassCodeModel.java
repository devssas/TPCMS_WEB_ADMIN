package com.tpcmswebadmin.service.authentication.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignInPassCodeModel {

    @NotEmpty(message = "{passCode.error.notEmpty}")
    @Pattern(regexp = "^[0-9]*$")
    private String passCode1;

    @NotEmpty(message = "{passCode.error.notEmpty}")
    @Pattern(regexp = "^[0-9]*$")
    private String passCode2;

    @NotEmpty(message = "{passCode.error.notEmpty}")
    @Pattern(regexp = "^[0-9]*$")
    private String passCode3;

    @Pattern(regexp = "^[0-9]*$", message = "{passCode.error.pattern}")
    private String passCodeFull;

    private String userCode;

    private String userName;

    private String mobileAppDeviceId;
}
