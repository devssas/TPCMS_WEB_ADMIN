package com.tpcmswebadmin.webpages.authentication.controller;

import com.tpcmswebadmin.infrastructure.domain.constant.Pages;
import com.tpcmswebadmin.service.authentication.domain.model.SignInPassCodeModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SignInPassCodeController {

    @GetMapping("/signInPassCode")
    public String getSignInPassCode(Model model) {
        model.addAttribute("signInPassCodeModel", new SignInPassCodeModel());

        return Pages.SIGN_IN_PASSCODE;
    }

}
