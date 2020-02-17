package com.tpcmswebadmin.webpages.dashboard.controller;

import com.tpcmswebadmin.webpages.dashboard.delegate.DashboardDelegate;
import com.tpcmswebadmin.webpages.dashboard.domain.model.DashboardModel;
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

        model.addAttribute("missionPermitCount", dashboard.getMissionPermitCount());
        model.addAttribute("caseCount", dashboard.getCaseCount());
        model.addAttribute("notificationCount", dashboard.getNotificationCount());
        model.addAttribute("sosCount", dashboard.getSosCount());

        return "dashboard";
    }

}
