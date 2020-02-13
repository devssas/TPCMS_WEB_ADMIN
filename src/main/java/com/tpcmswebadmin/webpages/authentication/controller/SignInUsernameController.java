package com.tpcmswebadmin.webpages.authentication.controller;

import com.tpcmswebadmin.service.authentication.domain.model.SignInUsernameModel;
import com.tpcmswebadmin.webpages.authentication.delegate.SignInUsernameDelegate;
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

    private final SignInUsernameDelegate signInUsernameDelegate;

    public SignInUsernameController(SignInUsernameDelegate signInUsernameDelegate) {
        this.signInUsernameDelegate = signInUsernameDelegate;
    }

    @GetMapping("/signInUsername")
    public String getSignInUsername(Model model) {
        model.addAttribute("signInUsernameModel", new SignInUsernameModel());

        return "signin_username";
    }

    @PostMapping("/signInUsername")
    public String signInWithUsername(@Valid @ModelAttribute("signInUsernameModel") SignInUsernameModel signInUsernameModel, BindingResult bindingResult, HttpServletRequest request) {
        if (signInUsernameDelegate.signInUsername(signInUsernameModel)) {
            request.getSession().setAttribute("username", signInUsernameModel.getUsername());

            return "redirect:signInUserCode";
        } else {
            return "redirect:signInUsername";
        }

    }
}
