package com.tpcmswebadmin.webpages.policevehicles.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PoliceVehicleViewController {

    @GetMapping("/policeVehicles")
    public String getPoliceVehicles() {

        return "police_vehicles_view";
    }
}
