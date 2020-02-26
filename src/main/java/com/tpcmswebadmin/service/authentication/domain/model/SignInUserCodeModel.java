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
public class SignInUserCodeModel {

    @NotEmpty(message = "{userCode.error.notEmpty}")
    @Pattern(regexp = "^[0-9]*$")
    private String userCode1;

    @NotEmpty(message = "{userCode.error.notEmpty}")
    @Pattern(regexp = "^[0-9]*$")
    private String userCode2;

    @NotEmpty(message = "{userCode.error.notEmpty}")
    @Pattern(regexp = "^[0-9]*$")
    private String userCode3;

    @NotEmpty(message = "{userCode.error.notEmpty}")
    @Pattern(regexp = "^[0-9]*$")
    private String userCode4;

    @NotEmpty(message = "{userCode.error.notEmpty}")
    @Pattern(regexp = "^[0-9]*$")
    private String userCode5;

    private String username;

    @Pattern(regexp = "^[0-9]*$", message = "{userCode.error.pattern}")
    private String userCodeFull;

    private String mobileAppDeviceId;

}
