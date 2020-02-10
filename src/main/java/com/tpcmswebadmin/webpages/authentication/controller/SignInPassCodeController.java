package com.tpcmswebadmin.webpages.authentication.controller;

import com.tpcmswebadmin.service.authentication.domain.model.SignInPassCodeModel;
import com.tpcmswebadmin.service.authentication.domain.model.SignInUserCodeModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SignInPassCodeController {

    @GetMapping("/signInPassCode")
    public String getSignInUsernamePage(Model model) {
        model.addAttribute("signInPassCodeModel", new SignInPassCodeModel());

        return "signInPassCode";
    }
}
