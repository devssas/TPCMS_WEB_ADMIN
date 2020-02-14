package com.tpcmswebadmin.webpages.criminals.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CriminalCasesViewController {

    @GetMapping("/criminalCases")
    public String getCriminalsDatabase() {

        return "criminal_manage_cases_view";
    }
}
