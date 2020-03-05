package com.tpcmswebadmin.webpages.authentication.controller;

import com.tpcmswebadmin.infrastructure.domain.constant.Pages;
import com.tpcmswebadmin.service.authentication.domain.model.SignInUsernameModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SignInUsernameController {


    @GetMapping("/signInUsername")
    public String getSignInUsername(Model model, HttpServletRequest request) {
        request.getSession().invalidate();
        model.addAttribute("signInUsernameModel", new SignInUsernameModel());

        return Pages.SIGN_IN_USERNAME;
    }

}
