package com.tpcmswebadmin.webpages.card.controller;

import com.tpcmswebadmin.infrastructure.utils.DateUtility;
import com.tpcmswebadmin.webpages.card.delegate.CrimeReportDelegate;
import com.tpcmswebadmin.webpages.card.domain.CrimeReportCardModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class CrimeReportCardController {

    private final CrimeReportDelegate crimeReportDelegate;

    @GetMapping("/card/crime-report")
    public String getCrimeReportCard(@RequestParam("crimeReportId") String crimeReportId, HttpServletRequest httpServletRequest, Model model) {
        CrimeReportCardModel crimeReportCardModel = crimeReportDelegate.getCrimeReportCardByCrimeReportId(crimeReportId, httpServletRequest);

        model.addAttribute("crimeTitle", crimeReportCardModel.getCrimeTitle());
        model.addAttribute("reportId", crimeReportCardModel.getReportId());
        model.addAttribute("status", crimeReportCardModel.getStatus());
        model.addAttribute("crimeScene", crimeReportCardModel.getCrimeScene());
        model.addAttribute("suspects", crimeReportCardModel.getSuspects());
        model.addAttribute("casePhotos", crimeReportCardModel.getImages());
        model.addAttribute("caseBrief", crimeReportCardModel.getCaseBrief());
        model.addAttribute("reportedDate", DateUtility.convertToFormat(crimeReportCardModel.getReportedDate(), "dd.MM.YYYY HH:MM"));
        model.addAttribute("images", crimeReportCardModel.getImages());

        return "card_crime_report";
    }
}
