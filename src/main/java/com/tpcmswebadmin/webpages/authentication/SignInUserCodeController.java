package com.tpcmswebadmin.webpages.authentication;

import com.tpcmswebadmin.service.authentication.domain.model.SignInUserCodeModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SignInUserCodeController {

    @GetMapping("/signInUserCode")
    public String getSignInUsernamePage(Model model) {
        model.addAttribute("signInUserCodeModel", new SignInUserCodeModel());

        return "signInUserCode";
    }
}
