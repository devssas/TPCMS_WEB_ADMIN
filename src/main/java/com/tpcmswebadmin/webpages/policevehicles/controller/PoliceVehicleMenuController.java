package com.tpcmswebadmin.webpages.policevehicles.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PoliceVehicleMenuController {

    @GetMapping("/policeVehiclesMenu")
    public String getPoliceVehicles() {

        return "police_vehicles_menu";
    }
}
