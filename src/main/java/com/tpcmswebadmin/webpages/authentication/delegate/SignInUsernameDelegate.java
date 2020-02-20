package com.tpcmswebadmin.webpages.authentication.delegate;

import com.ssas.tpcms.engine.vo.response.TPEngineResponse;
import com.tpcmswebadmin.service.authentication.AuthenticationService;
import com.tpcmswebadmin.service.authentication.domain.model.SignInUsernameModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SignInUsernameDelegate {

    private final AuthenticationService authenticationService;

    public SignInUsernameDelegate(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    public boolean signInUsername(SignInUsernameModel signInUsernameModel) {
        TPEngineResponse response = authenticationService.signInUserName(signInUsernameModel);

        if (response.getResponseCodeVO().getResponseCode().startsWith("OPS"))
            return true;
        else
            return false;
    }

}
