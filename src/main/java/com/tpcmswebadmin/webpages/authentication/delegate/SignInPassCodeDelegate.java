package com.tpcmswebadmin.webpages.authentication.delegate;

import com.ssas.tpcms.engine.vo.response.TPEngineResponse;
import com.tpcmswebadmin.service.authentication.AuthenticationService;
import com.tpcmswebadmin.service.authentication.domain.model.SignInPassCodeModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SignInPassCodeDelegate {

    private static final Logger logger = LoggerFactory.getLogger(SignInPassCodeDelegate.class);

    private final AuthenticationService authenticationService;

    public SignInPassCodeDelegate(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    public boolean signInPassCode(SignInPassCodeModel signInPassCodeModel) {
        TPEngineResponse response = authenticationService.signInPassCode(signInPassCodeModel);

        if (response.getResponseCodeVO().getResponseCode().startsWith("OPS"))
            return true;
        else
            return false;
    }

}
