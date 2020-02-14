package com.tpcmswebadmin.webpages.criminals.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CrimeReportsViewController {

    @GetMapping("/crimeReports")
    public String getCriminalsDatabase() {

        return "crime_reports_view";
    }
}
