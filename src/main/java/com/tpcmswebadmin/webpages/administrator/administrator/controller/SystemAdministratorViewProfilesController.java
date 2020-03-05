package com.tpcmswebadmin.webpages.administrator.administrator.controller;

import com.tpcmswebadmin.infrastructure.domain.constant.TpCmsConstants;
import com.tpcmswebadmin.service.authentication.domain.model.SignInPassCodeModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SystemAdministratorViewProfilesController {

    @GetMapping("/systemAdministrator")
    public String getSystemAdministrator(Model model, HttpServletRequest httpServletRequest) {
        model.addAttribute("officerName", httpServletRequest.getSession().getAttribute(TpCmsConstants.OFFICER_NAME));
        model.addAttribute("officerProfilePicture", httpServletRequest.getSession().getAttribute(TpCmsConstants.OFFICER_PROFILE_PICTURE));
        model.addAttribute("accessRole", httpServletRequest.getSession().getAttribute(TpCmsConstants.ACCESS_ROLE));

        return "system_administrator_view_profiles";
    }
}
