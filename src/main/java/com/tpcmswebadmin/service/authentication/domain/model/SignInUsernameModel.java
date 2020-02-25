package com.tpcmswebadmin.service.authentication.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInUsernameModel {

    @NotEmpty(message = "{userName.error.notEmpty}")
    private String username;

}
