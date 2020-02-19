package com.tpcmswebadmin.webpages.administrator.controller;

import com.tpcmswebadmin.service.authentication.domain.model.SignInPassCodeModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SystemAdministratorViewProfilesController {

    @GetMapping("/systemAdministrator")
    public String getSystemAdministrator() {

        return "system_administrator_view_profiles";
    }
}
