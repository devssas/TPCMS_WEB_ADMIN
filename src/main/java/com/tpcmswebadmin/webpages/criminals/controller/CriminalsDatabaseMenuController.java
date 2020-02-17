package com.tpcmswebadmin.webpages.criminals.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class CriminalsDatabaseMenuController {

    @GetMapping("/criminalsMenu")
    public String getCriminalsDatabase() {

        return "criminal_database_menu";
    }
}
