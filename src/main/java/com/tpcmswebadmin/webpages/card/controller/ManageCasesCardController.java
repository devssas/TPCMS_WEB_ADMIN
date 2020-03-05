package com.tpcmswebadmin.webpages.card.controller;

import com.tpcmswebadmin.infrastructure.utils.DateUtility;
import com.tpcmswebadmin.webpages.card.delegate.ManageCasesDelegate;
import com.tpcmswebadmin.webpages.card.domain.ManageCasesCardModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class ManageCasesCardController {

    private final ManageCasesDelegate manageCasesDelegate;

    @GetMapping("/card/manageCases")
    public String getMissionCard(@RequestParam("criminalCode") String criminalCode, HttpServletRequest httpServletRequest, Model model) {
        ManageCasesCardModel manageCasesCardModel = manageCasesDelegate.getManageCasesCardByCriminalsCode(criminalCode, httpServletRequest);

        model.addAttribute("criminalName", manageCasesCardModel.getCriminalName());
        model.addAttribute("date", DateUtility.convertToFormat(manageCasesCardModel.getDate(), "dd/MM/YYYY"));
        model.addAttribute("requestUnit", manageCasesCardModel.getRequestUnit());
        model.addAttribute("crimeType", manageCasesCardModel.getCrimeType());
        model.addAttribute("caseId", manageCasesCardModel.getCaseId());
        model.addAttribute("status", manageCasesCardModel.getStatus());
        model.addAttribute("caseBrief", manageCasesCardModel.getCaseBrief());
        model.addAttribute("image", manageCasesCardModel.getImage());

        return "card_manage_cases";
    }
}
