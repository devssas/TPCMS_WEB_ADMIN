package com.tpcmswebadmin.webpages.missionpermits.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MissionPermitsMenuController {

    @GetMapping("/missionPermits")
    public String getMissionPermits() {

        return "mission_permits_menu";
    }
}