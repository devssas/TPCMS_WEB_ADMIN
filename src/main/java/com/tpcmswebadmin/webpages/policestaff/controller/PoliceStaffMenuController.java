package com.tpcmswebadmin.webpages.policestaff.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PoliceStaffMenuController {

    @GetMapping("/policeStaff")
    public String getPoliceStaff() {

        return "police_staff_menu";
    }
}
