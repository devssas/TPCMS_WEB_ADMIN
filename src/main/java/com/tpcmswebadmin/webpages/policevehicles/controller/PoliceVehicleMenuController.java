package com.tpcmswebadmin.webpages.policevehicles.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PoliceVehicleMenuController {

    @GetMapping("/policeVehicles")
    public String getPoliceVehicles() {

        return "police_vehicle_menu";
    }
}
