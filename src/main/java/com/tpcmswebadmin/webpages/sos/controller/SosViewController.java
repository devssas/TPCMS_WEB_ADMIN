package com.tpcmswebadmin.webpages.sos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SosViewController {

    @GetMapping("/sos")
    public String getMissionPermits() {

        return "sos_calls";
    }
}
