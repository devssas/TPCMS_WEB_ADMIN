package com.tpcmswebadmin.webpages.prosecutionoffice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProsecutionOfficeController {

    @GetMapping("/prosecutionOffice")
    public String getProsecutionOffice() {

        return "prosecution_office";
    }

}
