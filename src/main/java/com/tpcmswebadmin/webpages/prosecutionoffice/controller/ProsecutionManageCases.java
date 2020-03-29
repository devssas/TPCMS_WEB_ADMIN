package com.tpcmswebadmin.webpages.prosecutionoffice.controller;

import com.tpcmswebadmin.infrastructure.domain.constant.Pages;
import com.tpcmswebadmin.infrastructure.domain.constant.TpCmsConstants;
import com.tpcmswebadmin.webpages.prosecutionoffice.delegate.ProsecutionManageCasesDelegate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

import static com.tpcmswebadmin.infrastructure.domain.enums.Roles.PROSECUTION;

@Controller
@RequiredArgsConstructor
public class ProsecutionManageCases {

    private final ProsecutionManageCasesDelegate prosecutionManageCasesDelegate;

    @GetMapping("/prosecutionManageCases")
    public String getProsecutionOffice(Model model, HttpServletRequest httpServletRequest) {
        String adminRole = (String) httpServletRequest.getSession().getAttribute(TpCmsConstants.ACCESS_ROLE);
        model.addAttribute("accessRole", adminRole);

        if(adminRole.equals(PROSECUTION.name())) {
            model.addAttribute("prosecutorName", httpServletRequest.getSession().getAttribute(TpCmsConstants.PROSECUTOR_NAME));
            model.addAttribute("prosecutorProfilePicture", httpServletRequest.getSession().getAttribute(TpCmsConstants.PROSECUTOR_PROFILE_PICTURE));
            model.addAttribute("redirect", "prosecutionOfficeMenu");
        } else {
            model.addAttribute("officerName", httpServletRequest.getSession().getAttribute(TpCmsConstants.OFFICER_NAME));
            model.addAttribute("officerProfilePicture", httpServletRequest.getSession().getAttribute(TpCmsConstants.OFFICER_PROFILE_PICTURE));
            model.addAttribute("dashboardPage", Pages.DASHBOARD_SUPERADMIN_JSON);
            model.addAttribute("prosecutorPage", Pages.MENU_BAR_SUPERADMIN_PROSECUTION_HOME);
            model.addAttribute("redirect", "prosecutionOfficeMenuAdmin");
        }

        model.addAttribute("statuses", prosecutionManageCasesDelegate.getCriminalCaseStatuses(httpServletRequest));

        return "prosecution_manage_cases";
    }
}
