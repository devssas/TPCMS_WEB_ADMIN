package com.tpcmswebadmin.webpages.administrator.controller;

import com.tpcmswebadmin.service.authentication.domain.model.SignInPassCodeModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SystemAdministratorMenuController {

    @GetMapping("/systemAdministrator")
    public String getSystemAdministrator(Model model) {
        model.addAttribute("signInPassCodeModel", new SignInPassCodeModel());

        return "system_administrator_menu";
    }
}
