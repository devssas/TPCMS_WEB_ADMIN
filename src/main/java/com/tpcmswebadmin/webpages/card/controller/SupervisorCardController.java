package com.tpcmswebadmin.webpages.card.controller;


import com.tpcmswebadmin.infrastructure.utils.DateUtility;
import com.tpcmswebadmin.webpages.card.delegate.SupervisorCardDelegate;
import com.tpcmswebadmin.webpages.card.domain.SupervisorCardModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class SupervisorCardController {

    private final SupervisorCardDelegate supervisorCardDelegate;

    @GetMapping("/card/supervisor")
    public String getSupervisorCard(@RequestParam("supervisorId") String supervisorId, HttpServletRequest httpServletRequest, Model model) {
        SupervisorCardModel supervisorCardModel = supervisorCardDelegate.getSupervisorBySupervisorId(supervisorId, httpServletRequest);

        model.addAttribute("commandCenter", supervisorCardModel.getCommandCenter());
        model.addAttribute("officerId", supervisorCardModel.getOfficerId());
        model.addAttribute("officerName", supervisorCardModel.getOfficerName());
        model.addAttribute("expiryDate", DateUtility.convertToFormat(supervisorCardModel.getExpiryDate(), "dd/MM/YYYY"));
        model.addAttribute("unit", supervisorCardModel.getUnit());
        model.addAttribute("rank", supervisorCardModel.getRank());
        model.addAttribute("image", supervisorCardModel.getImage());

        return "card_supervisor";
    }
}
