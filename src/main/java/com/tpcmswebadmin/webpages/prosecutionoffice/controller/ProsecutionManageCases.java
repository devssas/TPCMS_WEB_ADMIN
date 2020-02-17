package com.tpcmswebadmin.webpages.prosecutionoffice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProsecutionManageCases {

    @GetMapping("/prosecutionManageCases")
    public String getProsecutionOffice() {

        return "prosecution_manage_cases";
    }
}
