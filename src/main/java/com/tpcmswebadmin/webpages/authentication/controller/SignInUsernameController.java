package com.tpcmswebadmin.webpages.authentication.controller;

import com.tpcmswebadmin.infrastructure.domain.constant.Pages;
import com.tpcmswebadmin.service.authentication.domain.model.SignInUsernameModel;
import com.tpcmswebadmin.webpages.authentication.delegate.SignInUsernameDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class SignInUsernameController {

    private static final Logger logger = LoggerFactory.getLogger(SignInUsernameController.class);

    private final SignInUsernameDelegate signInUsernameDelegate;

    public SignInUsernameController(SignInUsernameDelegate signInUsernameDelegate) {
        this.signInUsernameDelegate = signInUsernameDelegate;
    }

    @GetMapping("/signInUsername")
    public String getSignInUsername(Model model, HttpServletRequest request) {
        request.getSession().invalidate();
        model.addAttribute("signInUsernameModel", new SignInUsernameModel());

        return Pages.SIGN_IN_USERNAME;
    }

    @PostMapping("/signInUsername")
    public String signInWithUsername(@Valid @ModelAttribute("signInUsernameModel") SignInUsernameModel signInUsernameModel, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            logger.warn("Error signin username. No name is present. {}", bindingResult.getAllErrors());

            return Pages.SIGN_IN_USERNAME;
        }

        if (signInUsernameDelegate.signInUsername(signInUsernameModel, request)) {
            return Pages.REDIRECT_SIGN_IN_USERCODE;
        } else {
            logger.error("Errors on signin username. username cant be found {} ", signInUsernameModel.getUsername());

            return Pages.SIGN_IN_USERNAME;
        }

    }
}
