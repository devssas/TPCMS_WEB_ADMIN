package com.tpcmswebadmin.service.authentication.domain.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignInResponse implements Serializable {

    private static final long serialVersionUID = -2831460571910056043L;

    private String message;

    private boolean status;

    private String nextUrl;
}
