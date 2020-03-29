package com.tpcmswebadmin.webpages.superadmin.controller;

import com.tpcmswebadmin.infrastructure.domain.constant.Pages;
import com.tpcmswebadmin.infrastructure.domain.constant.TpCmsConstants;
import com.tpcmswebadmin.webpages.superadmin.delegate.SupervisorViewDelegate;
import com.tpcmswebadmin.webpages.superadmin.model.ScreenInfoModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class SuperAdminViewProfilesController {

    private final SupervisorViewDelegate supervisorViewDelegate;

    @GetMapping("/systemAdministrator")
    public String getSystemAdministrator(Model model, HttpServletRequest httpServletRequest) {
        model.addAttribute("officerName", httpServletRequest.getSession().getAttribute(TpCmsConstants.OFFICER_NAME));
        model.addAttribute("officerProfilePicture", httpServletRequest.getSession().getAttribute(TpCmsConstants.OFFICER_PROFILE_PICTURE));
        model.addAttribute("accessRole", httpServletRequest.getSession().getAttribute(TpCmsConstants.ACCESS_ROLE));
        model.addAttribute("dashboardPage", Pages.DASHBOARD_SUPERADMIN_JSON);
        model.addAttribute("prosecutorPage", Pages.MENU_BAR_SUPERADMIN_PROSECUTION_HOME);

        ScreenInfoModel screenInfoModel = supervisorViewDelegate.getScreenInfo(httpServletRequest);

        model.addAttribute("statuses", screenInfoModel.getStatuses());
        model.addAttribute("userTypes", screenInfoModel.getUserTypes());

        return "system_administrator_view_profiles";
    }
}
