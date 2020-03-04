package com.tpcmswebadmin.webpages.authentication.delegate;

import com.ssas.tpcms.engine.vo.response.TPEngineResponse;
import com.tpcmswebadmin.infrastructure.domain.constant.TpCmsConstants;
import com.tpcmswebadmin.service.authentication.service.AuthenticationService;
import com.tpcmswebadmin.service.authentication.domain.model.SignInUsernameModel;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class SignInUsernameDelegate {

    private final AuthenticationService authenticationService;

    public SignInUsernameDelegate(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    public boolean signInUsername(SignInUsernameModel signInUsernameModel, HttpServletRequest httpServletRequest) {
        TPEngineResponse response = authenticationService.signInUserName(signInUsernameModel);

        if (response.getResponseCodeVO().getResponseCode().startsWith("OPS")) {
            httpServletRequest.getSession().setAttribute(TpCmsConstants.USERNAME, signInUsernameModel.getUsername());
            httpServletRequest.getSession().setAttribute(TpCmsConstants.MOBILE_APP_DEVICE_ID, response.getOfficersProfileResponseVO().getMobileDeviceId());

            return true;
        } else {
            return false;
        }
    }

}
