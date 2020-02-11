package com.tpcmswebadmin.webpages.authentication.delegate;

import com.ssas.tpcms.engine.vo.response.TPEngineResponse;
import com.tpcmswebadmin.infrastructure.utils.StringUtility;
import com.tpcmswebadmin.service.authentication.AuthenticationService;
import com.tpcmswebadmin.service.authentication.domain.model.SignInUserCodeModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
public class SignInUserCodeDelegate {

    private static final Logger logger = LoggerFactory.getLogger(SignInUserCodeDelegate.class);

    private final AuthenticationService authenticationService;

    public SignInUserCodeDelegate(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    public boolean signInUserCode(SignInUserCodeModel signInUserCodeModel) {
        logger.info(StringUtility.concat("UserCode for username : ", signInUserCodeModel.getUserCodeFull(), ",", signInUserCodeModel.getUsername()));

        TPEngineResponse response = authenticationService.signInUserCode(signInUserCodeModel);
        if (response.getResponseCodeVO().getResponseCode().startsWith("OPS"))
            return true;
        else
            return false;
    }
}
