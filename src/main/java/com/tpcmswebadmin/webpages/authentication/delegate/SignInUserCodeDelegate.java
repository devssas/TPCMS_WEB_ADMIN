package com.tpcmswebadmin.webpages.authentication.delegate;

import com.ssas.tpcms.engine.vo.response.TPEngineResponse;
import com.tpcmswebadmin.infrastructure.utils.StringUtility;
import com.tpcmswebadmin.service.authentication.AuthenticationService;
import com.tpcmswebadmin.service.authentication.domain.model.SignInUserCodeModel;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;


@Component
public class SignInUserCodeDelegate {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(SignInUserCodeDelegate.class);

    private final AuthenticationService authenticationService;

    public SignInUserCodeDelegate(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    public boolean signInUserCode(SignInUserCodeModel signInUserCodeModel) {
        String userCode = StringUtility.concat(signInUserCodeModel.getUserCode1(), signInUserCodeModel.getUserCode2(), signInUserCodeModel.getUserCode3(), signInUserCodeModel.getUserCode4(),
                                           signInUserCodeModel.getUserCode5());

        logger.info(StringUtility.concat("UserCode for username : ", userCode, ",", signInUserCodeModel.getUsername()));

        TPEngineResponse response = authenticationService.signInUserCode(userCode);
        if (response.getResponseCodeVO().getResponseCode().startsWith("OPS"))
            return true;
        else
            return false;
    }
}
