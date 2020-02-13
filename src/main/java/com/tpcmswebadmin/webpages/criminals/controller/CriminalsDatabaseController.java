package com.tpcmswebadmin.webpages.criminals.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class CriminalsDatabaseController {

    @GetMapping("/criminals")
    public String getCriminalsDatabase() {

        return "criminal_database";
    }
}
