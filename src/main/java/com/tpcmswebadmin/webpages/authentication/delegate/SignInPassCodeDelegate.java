package com.tpcmswebadmin.webpages.authentication.delegate;

import com.ssas.tpcms.engine.vo.response.TPEngineResponse;
import com.tpcmswebadmin.service.authentication.AuthenticationService;
import com.tpcmswebadmin.service.authentication.domain.model.SignInPassCodeModel;
import com.tpcmswebadmin.webpages.authentication.domain.SignInPassCodeDto;
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

    public SignInPassCodeDto signInPassCode(SignInPassCodeModel signInPassCodeModel) {
        TPEngineResponse response = authenticationService.signInPassCode(signInPassCodeModel);
        SignInPassCodeDto signInPassCodeDto = new SignInPassCodeDto();

        if (response.getResponseCodeVO().getResponseCode().startsWith("OPS")) {
            signInPassCodeDto.setHasResult(true);
            signInPassCodeDto.setOfficerCode(response.getOfficerCode());
            signInPassCodeDto.setReportUnit(response.getOfficersProfileResponseVO().getReportingUnit());
        }

        return signInPassCodeDto;
    }

}
