package com.tpcmswebadmin.webpages.authentication.delegate;

import com.tpcmswebadmin.service.authentication.AuthenticationService;
import com.tpcmswebadmin.service.authentication.domain.model.SignInUsernameModel;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class SignInUsernameDelegate {

    private static final Logger logger = Logger.getLogger(SignInUsernameDelegate.class.getName());

    private final AuthenticationService authenticationService;

    public SignInUsernameDelegate(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    public boolean signIn(SignInUsernameModel signInUsernameModel) {
        authenticationService.signInUserName(signInUsernameModel);

        return true;
    }

}
