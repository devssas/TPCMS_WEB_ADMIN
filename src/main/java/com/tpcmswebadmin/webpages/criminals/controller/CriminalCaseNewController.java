package com.tpcmswebadmin.webpages.criminals.controller;

import com.tpcmswebadmin.infrastructure.domain.constant.TpCmsConstants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

import static com.tpcmswebadmin.infrastructure.domain.enums.Roles.ADMIN;

@Controller
public class CriminalCaseNewController {

    @GetMapping("/fileCriminalCase")
    public String getFileCrimeCasePage(Model model, HttpServletRequest httpServletRequest) {
        model.addAttribute("officerName", httpServletRequest.getSession().getAttribute(TpCmsConstants.OFFICER_NAME));
        model.addAttribute("officerProfilePicture", httpServletRequest.getSession().getAttribute(TpCmsConstants.OFFICER_PROFILE_PICTURE));

        String adminRole = (String) httpServletRequest.getSession().getAttribute(TpCmsConstants.ACCESS_ROLE);
        model.addAttribute("accessRole", adminRole);

        if(adminRole.equals(ADMIN.name()))
            model.addAttribute("disabled", TpCmsConstants.LIST_DISABLE);

        return "criminal_file_criminal_case";
    }
}
