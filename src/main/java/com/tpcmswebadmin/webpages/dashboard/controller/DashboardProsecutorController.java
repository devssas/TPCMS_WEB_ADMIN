package com.tpcmswebadmin.webpages.dashboard.controller;

import com.tpcmswebadmin.infrastructure.domain.constant.Pages;
import com.tpcmswebadmin.infrastructure.domain.constant.TpCmsConstants;
import com.tpcmswebadmin.webpages.dashboard.delegate.DashboardProsecutorDelegate;
import com.tpcmswebadmin.webpages.dashboard.domain.model.DashboardNotificationModel;
import com.tpcmswebadmin.webpages.dashboard.domain.model.DashboardProsecutorModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequiredArgsConstructor
public class DashboardProsecutorController {

    private final DashboardProsecutorDelegate dashboardProsecutorDelegate;

    @GetMapping("/dashboardProsecutor")
    public String getDashboard(Model model, HttpServletRequest httpServletRequest) {
        DashboardProsecutorModel dashboard = dashboardProsecutorDelegate.getDashboardProsecutor(httpServletRequest);

        String adminRole = (String) httpServletRequest.getSession().getAttribute(TpCmsConstants.ACCESS_ROLE);
        model.addAttribute("accessRole", adminRole);

        if(adminRole.equals("PROSECUTION")) {
            model.addAttribute("totalEvidenceCount", dashboard.getTotalEvidenceCount());
            model.addAttribute("notificationCount", dashboard.getNotificationCount());
            model.addAttribute("prosecutorName", httpServletRequest.getSession().getAttribute(TpCmsConstants.PROSECUTOR_NAME));
            model.addAttribute("prosecutorProfilePicture", httpServletRequest.getSession().getAttribute(TpCmsConstants.PROSECUTOR_PROFILE_PICTURE));
        } else {
            return Pages.ERROR_500;
        }

        return Pages.DASHBOARD_PROSECUTOR;
    }
}
