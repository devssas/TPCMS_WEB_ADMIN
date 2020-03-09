package com.tpcmswebadmin.webpages.sos.controller;

import com.tpcmswebadmin.infrastructure.domain.constant.Pages;
import com.tpcmswebadmin.infrastructure.domain.constant.TpCmsConstants;
import com.tpcmswebadmin.service.sos.domain.SosCallDetailDto;
import com.tpcmswebadmin.webpages.sos.delegate.SosDetailsDelegate;
import com.tpcmswebadmin.webpages.sos.model.SosCallDetailModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

import static com.tpcmswebadmin.infrastructure.domain.enums.Roles.ADMIN;


@Controller
@RequiredArgsConstructor
public class SosDetailsController {

    private final SosDetailsDelegate sosDetailsDelegate;

    @GetMapping("/sosDetails")
    public String getMissionPermits(@RequestParam("sosId") String sosId, Model model, HttpServletRequest httpServletRequest) {
        SosCallDetailModel details = sosDetailsDelegate.getSosDetailsById(sosId, httpServletRequest);

        model.addAttribute("officerName", httpServletRequest.getSession().getAttribute(TpCmsConstants.OFFICER_NAME));
        model.addAttribute("officerProfilePicture", httpServletRequest.getSession().getAttribute(TpCmsConstants.OFFICER_PROFILE_PICTURE));
        model.addAttribute("location", details.getLocation());
        model.addAttribute("phoneNumber", details.getPhoneNumber());
        model.addAttribute("username", details.getUsername());
        model.addAttribute("requestDate", details.getRequestDate());
        model.addAttribute("requestSerialNumber", details.getRequestSerialNumber());
        model.addAttribute("remarks", details.getRemarks());


        String adminRole = (String) httpServletRequest.getSession().getAttribute(TpCmsConstants.ACCESS_ROLE);
        model.addAttribute("accessRole", adminRole);

        if(adminRole.equals(ADMIN.name())) {
            model.addAttribute("disabled", TpCmsConstants.LIST_DISABLE);
            model.addAttribute("dashboardPage", Pages.DASHBOARD_ADMIN_JSON);
        } else {
            model.addAttribute("dashboardPage", Pages.DASHBOARD_SUPERADMIN_JSON);
            model.addAttribute("prosecutorPage", Pages.MENU_BAR_SUPERADMIN_PROSECUTION_HOME);
        }

        return "sos_details";
    }
}
