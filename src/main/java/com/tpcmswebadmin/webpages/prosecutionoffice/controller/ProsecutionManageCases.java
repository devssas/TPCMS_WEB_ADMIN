package com.tpcmswebadmin.webpages.prosecutionoffice.controller;

import com.tpcmswebadmin.infrastructure.domain.constant.TpCmsConstants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProsecutionManageCases {

    @GetMapping("/prosecutionManageCases")
    public String getProsecutionOffice(Model model, HttpServletRequest httpServletRequest) {
        model.addAttribute("prosecutorName", httpServletRequest.getSession().getAttribute(TpCmsConstants.PROSECUTOR_NAME));
        model.addAttribute("prosecutorProfilePicture", httpServletRequest.getSession().getAttribute(TpCmsConstants.PROSECUTOR_PROFILE_PICTURE));
        model.addAttribute("accessRole", httpServletRequest.getSession().getAttribute(TpCmsConstants.ACCESS_ROLE));

        return "prosecution_manage_cases";
    }
}
