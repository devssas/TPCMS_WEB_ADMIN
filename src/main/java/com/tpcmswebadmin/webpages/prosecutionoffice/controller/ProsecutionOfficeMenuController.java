package com.tpcmswebadmin.webpages.prosecutionoffice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProsecutionOfficeMenuController {

    @GetMapping("/prosecutionOfficeMenu")
    public String getProsecutionOffice() {

        return "prosecution_office_menu";
    }

}
