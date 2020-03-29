package com.tpcmswebadmin.webpages.calender.controller;

import com.tpcmswebadmin.infrastructure.domain.constant.Pages;
import com.tpcmswebadmin.infrastructure.domain.constant.TpCmsConstants;
import com.tpcmswebadmin.webpages.calender.delegate.CalenderEventDelegate;
import com.tpcmswebadmin.webpages.calender.domain.CalenderEventModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.tpcmswebadmin.infrastructure.domain.enums.Roles.PROSECUTION;

@Controller
@RequiredArgsConstructor
public class CalenderEventController {

    private final CalenderEventDelegate calenderEventDelegate;

    @GetMapping("/calendarEvent")
    public String getCalender(@RequestParam("calendarDate") String calendarDate, Model model, HttpServletRequest httpServletRequest) {
        String adminRole = (String) httpServletRequest.getSession().getAttribute(TpCmsConstants.ACCESS_ROLE);
        model.addAttribute("accessRole", adminRole);

        if(adminRole.equals(PROSECUTION.name())) {
            model.addAttribute("prosecutorName", httpServletRequest.getSession().getAttribute(TpCmsConstants.PROSECUTOR_NAME));
            model.addAttribute("prosecutorProfilePicture", httpServletRequest.getSession().getAttribute(TpCmsConstants.PROSECUTOR_PROFILE_PICTURE));
            model.addAttribute("calendarDate", calendarDate);
        } else {
            model.addAttribute("officerName", httpServletRequest.getSession().getAttribute(TpCmsConstants.OFFICER_NAME));
            model.addAttribute("officerProfilePicture", httpServletRequest.getSession().getAttribute(TpCmsConstants.OFFICER_PROFILE_PICTURE));
            model.addAttribute("dashboardPage", Pages.DASHBOARD_SUPERADMIN_JSON);
            model.addAttribute("prosecutorPage", Pages.MENU_BAR_SUPERADMIN_PROSECUTION_HOME);
            model.addAttribute("calendarDate", calendarDate);
        }

        return "calendar_event";
    }
}
