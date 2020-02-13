package com.tpcmswebadmin.webpages.policestaff.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PoliceStaffViewController {

    @GetMapping("/officerProfiles")
    public String getPoliceStaff() {

        return "police_staff_list_view";
    }

}
