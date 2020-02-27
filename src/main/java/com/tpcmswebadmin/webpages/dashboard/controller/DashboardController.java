package com.tpcmswebadmin.webpages.dashboard.controller;

import com.tpcmswebadmin.infrastructure.domain.constant.Pages;
import com.tpcmswebadmin.infrastructure.domain.constant.TpCmsConstants;
import com.tpcmswebadmin.infrastructure.domain.enums.Roles;
import com.tpcmswebadmin.service.policeofficer.controller.PoliceOfficerControllerAPI;
import com.tpcmswebadmin.webpages.dashboard.delegate.DashboardDelegate;
import com.tpcmswebadmin.webpages.dashboard.domain.model.DashboardModel;
import com.tpcmswebadmin.webpages.dashboard.domain.model.DashboardNotificationModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

import static com.tpcmswebadmin.infrastructure.domain.enums.Roles.*;

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

        String adminRole = (String) httpServletRequest.getSession().getAttribute(TpCmsConstants.ACCESS_ROLE);
        model.addAttribute("accessRole", adminRole);

        switch (adminRole) {
            case "ADMIN":
            case "SUPER_ADMIN":
                model.addAttribute("officerName", httpServletRequest.getSession().getAttribute(TpCmsConstants.OFFICER_NAME));
                model.addAttribute("officerProfilePicture", httpServletRequest.getSession().getAttribute(TpCmsConstants.OFFICER_PROFILE_PICTURE));

                if(adminRole.equals(ADMIN.name()))
                    model.addAttribute("disabled", TpCmsConstants.LIST_DISABLE);

                return Pages.DASHBOARD_ADMIN;
            case "PROSECUTION":
                model.addAttribute("prosecutorName", httpServletRequest.getSession().getAttribute(TpCmsConstants.PROSECUTOR_NAME));
                model.addAttribute("prosecutorProfilePicture", httpServletRequest.getSession().getAttribute(TpCmsConstants.PROSECUTOR_PROFILE_PICTURE));
                return Pages.DASHBOARD_PROSECUTOR;
            default:
                return Pages.ERROR_500;
        }
    }

}
