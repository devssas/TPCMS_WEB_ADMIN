package com.tpcmswebadmin.webpages.authentication.controller;

import com.tpcmswebadmin.infrastructure.domain.constant.Pages;
import com.tpcmswebadmin.infrastructure.domain.constant.TpCmsConstants;
import com.tpcmswebadmin.infrastructure.utils.StringUtility;
import com.tpcmswebadmin.service.authentication.domain.model.SignInUserCodeModel;
import com.tpcmswebadmin.webpages.authentication.delegate.SignInUserCodeDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class SignInUserCodeController {

    private static final Logger logger = LoggerFactory.getLogger(SignInUserCodeController.class);

    private final SignInUserCodeDelegate signInUserCodeDelegate;

    public SignInUserCodeController(SignInUserCodeDelegate signInUserCodeDelegate) {
        this.signInUserCodeDelegate = signInUserCodeDelegate;
    }

    @GetMapping("/signInUserCode")
    public String getSignInUserCode(Model model) {
        model.addAttribute("signInUserCodeModel", new SignInUserCodeModel());

        return Pages.SIGN_IN_USERCODE;
    }

    @PostMapping("/signInUserCode")
    public String signInWithUserCode(@Valid @ModelAttribute("signInUserCodeModel") SignInUserCodeModel signInUserCodeModel, BindingResult bindingResult, HttpServletRequest request) {
        String userCode = generateUserCode(signInUserCodeModel);

        if (bindingResult.hasErrors()) {
            logger.warn("Errors {}", bindingResult.getAllErrors());

            SignInUserCodeModel emptyModel = new SignInUserCodeModel();
            BeanUtils.copyProperties(emptyModel, signInUserCodeModel);

            return Pages.SIGN_IN_USERCODE;
        }

        signInUserCodeModel.setUsername((String) request.getSession().getAttribute(TpCmsConstants.USERNAME));
        signInUserCodeModel.setMobileAppDeviceId((String) request.getSession().getAttribute(TpCmsConstants.MOBILE_APP_DEVICE_ID));
        signInUserCodeModel.setUserCodeFull(userCode);

        if (signInUserCodeDelegate.signInUserCode(signInUserCodeModel)) {
            request.getSession().setAttribute(TpCmsConstants.USERCODE, userCode);

            return Pages.REDIRECT_SIGN_IN_PASSCODE;
        } else {

            return Pages.SIGN_IN_USERCODE;
        }
    }

    private String generateUserCode(SignInUserCodeModel signInUserCodeModel) {
        return StringUtility.concat(signInUserCodeModel.getUserCode1(), signInUserCodeModel.getUserCode2(), signInUserCodeModel.getUserCode3(), signInUserCodeModel.getUserCode4(),
                                    signInUserCodeModel.getUserCode5());
    }

}
