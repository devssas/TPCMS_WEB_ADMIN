package com.tpcmswebadmin.webpages.sos.controller;

import com.tpcmswebadmin.infrastructure.domain.constant.TpCmsConstants;
import com.tpcmswebadmin.webpages.sos.delegate.SosViewDelegate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

import static com.tpcmswebadmin.infrastructure.domain.enums.Roles.ADMIN;

@Controller
@RequiredArgsConstructor
public class SosViewController {

    private final SosViewDelegate sosViewDelegate;

    @GetMapping("/sos")
    public String getMissionPermits(Model model, HttpServletRequest httpServletRequest) {
        sosViewDelegate.updateSOSNotifications(httpServletRequest);

        model.addAttribute("officerName", httpServletRequest.getSession().getAttribute(TpCmsConstants.OFFICER_NAME));
        model.addAttribute("officerProfilePicture", httpServletRequest.getSession().getAttribute(TpCmsConstants.OFFICER_PROFILE_PICTURE));

        String adminRole = (String) httpServletRequest.getSession().getAttribute(TpCmsConstants.ACCESS_ROLE);
        model.addAttribute("accessRole", adminRole);

        if(adminRole.equals(ADMIN.name()))
            model.addAttribute("disabled", TpCmsConstants.LIST_DISABLE);

        return "sos_calls";
    }
}
