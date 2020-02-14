package com.tpcmswebadmin.webpages.prosecutionoffice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProsecutionCasesHistory {

    @GetMapping("/prosecutionCasesHistory")
    public String getProsecutionOffice() {

        return "prosecution_cases_history";
    }
}
