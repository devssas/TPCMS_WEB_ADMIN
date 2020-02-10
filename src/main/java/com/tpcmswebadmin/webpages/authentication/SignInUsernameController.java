package com.tpcmswebadmin.webpages.authentication;

import com.tpcmswebadmin.service.authentication.domain.model.SignInUsernameModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SignInUsernameController {

    @GetMapping("/signInUsername")
    public String getSignInUsernamePage(Model model) {
        model.addAttribute("signInUsernameModel", new SignInUsernameModel());

        return "signInUsername";
    }
}
