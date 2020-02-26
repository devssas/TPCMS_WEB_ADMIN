package com.tpcmswebadmin.webpages.dashboard.controller;

import com.tpcmswebadmin.infrastructure.domain.constant.Pages;
import com.tpcmswebadmin.infrastructure.domain.constant.TpCmsConstants;
import com.tpcmswebadmin.webpages.dashboard.delegate.DashboardDelegate;
import com.tpcmswebadmin.webpages.dashboard.domain.model.DashboardModel;
import com.tpcmswebadmin.webpages.dashboard.domain.model.DashboardNotificationModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardDelegate dashboardDelegate;

    @GetMapping("/dashboard")
    public String getDashboard(Model model, HttpServletRequest httpServletRequest) {
        DashboardModel dashboard = dashboardDelegate.getDashboard(httpServletRequest);
        DashboardNotificationModel notificationModel = dashboardDelegate.getDashboardNotifications(httpServletRequest);

        model.addAttribute("missionPermitCount", dashboard.getMissionPermitCount());
        model.addAttribute("caseCount", dashboard.getCaseCount());
        model.addAttribute("notificationCount", dashboard.getNotificationCount());
        model.addAttribute("sosCount", dashboard.getSosCount());

        model.addAttribute("sosNotificationCount", notificationModel.getSosCount());
        model.addAttribute("notificationCount", notificationModel.getNotificationCount());

        model.addAttribute("officerName", httpServletRequest.getSession().getAttribute(TpCmsConstants.OFFICER_NAME));
        model.addAttribute("officerProfilePicture", httpServletRequest.getSession().getAttribute(TpCmsConstants.OFFICER_PROFILE_PICTURE));
        model.addAttribute("accessRole", httpServletRequest.getSession().getAttribute(TpCmsConstants.ACCESS_ROLE));

        return Pages.DASHBOARD;
    }

}
