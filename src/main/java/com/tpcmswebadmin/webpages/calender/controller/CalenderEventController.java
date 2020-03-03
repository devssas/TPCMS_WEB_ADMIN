package com.tpcmswebadmin.webpages.calender.controller;

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

@Controller
@RequiredArgsConstructor
public class CalenderEventController {

    private final CalenderEventDelegate calenderEventDelegate;

    @GetMapping("/calenderEvent")
    public String getCalender(@RequestParam("date") String date, Model model, HttpServletRequest httpServletRequest) {
    //    List<CalenderEventModel> list =  calenderEventDelegate.getEventsOnDate(date, httpServletRequest);

        model.addAttribute("prosecutorName", httpServletRequest.getSession().getAttribute(TpCmsConstants.PROSECUTOR_NAME));
        model.addAttribute("prosecutorProfilePicture", httpServletRequest.getSession().getAttribute(TpCmsConstants.PROSECUTOR_PROFILE_PICTURE));
        model.addAttribute("accessRole", httpServletRequest.getSession().getAttribute(TpCmsConstants.ACCESS_ROLE));

        return "calendar_event";
    }
}
