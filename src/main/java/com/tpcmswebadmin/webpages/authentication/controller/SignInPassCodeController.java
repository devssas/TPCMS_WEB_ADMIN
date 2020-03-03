package com.tpcmswebadmin.webpages.authentication.controller;

import com.tpcmswebadmin.infrastructure.domain.LoginUserDo;
import com.tpcmswebadmin.infrastructure.domain.constant.Pages;
import com.tpcmswebadmin.infrastructure.domain.constant.TpCmsConstants;
import com.tpcmswebadmin.infrastructure.utils.StringUtility;
import com.tpcmswebadmin.service.authentication.domain.model.SignInPassCodeModel;
import com.tpcmswebadmin.service.authentication.domain.model.SignInUserCodeModel;
import com.tpcmswebadmin.webpages.authentication.delegate.SignInPassCodeDelegate;
import com.tpcmswebadmin.webpages.authentication.domain.SignInPassCodeDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Slf4j
@Controller
public class SignInPassCodeController {

    private final SignInPassCodeDelegate signInPassCodeDelegate;

    public SignInPassCodeController(SignInPassCodeDelegate signInPassCodeDelegate) {
        this.signInPassCodeDelegate = signInPassCodeDelegate;
    }

    @GetMapping("/signInPassCode")
    public String getSignInPassCode(Model model) {
        model.addAttribute("signInPassCodeModel", new SignInPassCodeModel());

        return Pages.SIGN_IN_PASSCODE;
    }

    @PostMapping("/signInPassCode")
    public String getSignInPassCode(@Valid @ModelAttribute("signInPassCodeModel") SignInPassCodeModel signInPassCodeModel, HttpServletRequest request) {
        signInPassCodeModel.setUserName((String) request.getSession().getAttribute(TpCmsConstants.USERNAME));
        signInPassCodeModel.setUserCode((String) request.getSession().getAttribute(TpCmsConstants.USERCODE));
        signInPassCodeModel.setMobileAppDeviceId((String) request.getSession().getAttribute(TpCmsConstants.MOBILE_APP_DEVICE_ID));

        String passCode = generatePassCode(signInPassCodeModel);
        signInPassCodeModel.setPassCodeFull(passCode);

        SignInPassCodeDto signInPassCodeDto = signInPassCodeDelegate.signInPassCode(signInPassCodeModel);

        if (signInPassCodeDto.isHasResult()) {
            request.getSession().setAttribute(TpCmsConstants.OFFICER_CODE, signInPassCodeDto.getOfficerCode());
            request.getSession().setAttribute(TpCmsConstants.REPORT_UNIT, signInPassCodeDto.getReportUnit());

            if(signInPassCodeDto.getAccessRole().equals("PROSECUTION")) {
                request.getSession().setAttribute(TpCmsConstants.PROSECUTOR_NAME, signInPassCodeDto.getOfficerName());
                request.getSession().setAttribute(TpCmsConstants.PROSECUTOR_PROFILE_PICTURE, signInPassCodeDto.getProfilePicture());
            } else {
                request.getSession().setAttribute(TpCmsConstants.OFFICER_NAME, signInPassCodeDto.getOfficerName());
                request.getSession().setAttribute(TpCmsConstants.OFFICER_PROFILE_PICTURE, signInPassCodeDto.getProfilePicture());
            }

            request.getSession().setAttribute(TpCmsConstants.ACCESS_ROLE, signInPassCodeDto.getAccessRole());

            return signInPassCodeDto.getAccessRole().equals("PROSECUTION") ? Pages.REDIRECT_PROSECUTOR_DASHBOARD : Pages.REDIRECT_DASHBOARD;
        } else {
            return Pages.SIGN_IN_PASSCODE;
        }
    }

    private String generatePassCode(SignInPassCodeModel signInPassCodeModel) {
        return StringUtility.concat(signInPassCodeModel.getPassCode1(), signInPassCodeModel.getPassCode2(), signInPassCodeModel.getPassCode3());
    }

}
