package com.tpcmswebadmin.service.authentication.controller;


import com.tpcmswebadmin.infrastructure.domain.constant.TpCmsConstants;
import com.tpcmswebadmin.service.authentication.service.AuthenticationService;
import com.tpcmswebadmin.service.authentication.domain.model.SignInPassCodeModel;
import com.tpcmswebadmin.service.authentication.domain.model.SignInUserCodeModel;
import com.tpcmswebadmin.service.authentication.domain.model.SignInUsernameModel;
import com.tpcmswebadmin.service.authentication.domain.response.SignInResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("api")
public class AuthenticationControllerAPI {

    private final AuthenticationService authenticationService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("signInUsername")
    public SignInResponse signInUsername(@RequestParam("username") String username, HttpServletRequest httpServletRequest) {
        SignInUsernameModel signInUsernameModel = new SignInUsernameModel(username);

        return authenticationService.signInWithUserName(signInUsernameModel, httpServletRequest);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("signInUserCode")
    public SignInResponse signInUserCode(@RequestParam("code") String code, HttpServletRequest httpServletRequest) {
        SignInUserCodeModel signInUserCodeModel = new SignInUserCodeModel();

        signInUserCodeModel.setUsername((String) httpServletRequest.getSession().getAttribute(TpCmsConstants.USERNAME));
        signInUserCodeModel.setMobileAppDeviceId((String) httpServletRequest.getSession().getAttribute(TpCmsConstants.MOBILE_APP_DEVICE_ID));
        signInUserCodeModel.setUserCodeFull(code);

        return authenticationService.signInWithUserCode(signInUserCodeModel, httpServletRequest);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("signInPassCode")
    public SignInResponse signInPasscode(@RequestParam("code") String code, HttpServletRequest httpServletRequest) {
        SignInPassCodeModel signInPassCodeModel = new SignInPassCodeModel();
        signInPassCodeModel.setPassCodeFull(code);
        signInPassCodeModel.setUserName((String) httpServletRequest.getSession().getAttribute(TpCmsConstants.USERNAME));
        signInPassCodeModel.setUserCode((String) httpServletRequest.getSession().getAttribute(TpCmsConstants.USERCODE));
        signInPassCodeModel.setMobileAppDeviceId((String) httpServletRequest.getSession().getAttribute(TpCmsConstants.MOBILE_APP_DEVICE_ID));

        return authenticationService.signInWithPassCode(signInPassCodeModel, httpServletRequest);
    }
}
