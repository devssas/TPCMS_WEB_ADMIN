package com.tpcmswebadmin.webpages.authentication.controller;

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

        return "signin_username";
    }

    @PostMapping("/signInUsername")
    public String signInWithUsername(@Valid @ModelAttribute("signInUsernameModel") SignInUsernameModel signInUsernameModel, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            logger.warn("Errors {}", bindingResult.getAllErrors());

            return "signin_username";
        }

        if (signInUsernameDelegate.signInUsername(signInUsernameModel, request)) {
            return "redirect:signInUserCode";
        } else {
            return "redirect:signInUsername";
        }

    }
}
