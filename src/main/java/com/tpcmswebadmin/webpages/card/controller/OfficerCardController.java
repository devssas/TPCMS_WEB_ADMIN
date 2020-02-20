package com.tpcmswebadmin.webpages.card.controller;

import com.tpcmswebadmin.infrastructure.utils.DateUtility;
import com.tpcmswebadmin.webpages.card.delegate.OfficerCardDelegate;
import com.tpcmswebadmin.webpages.card.domain.OfficerCardModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequiredArgsConstructor
public class OfficerCardController {

    private final OfficerCardDelegate officerCardDelegate;

    @GetMapping("/officer/card")
    public String getDashboard(@RequestParam("officerId") String officerId, HttpServletRequest httpServletRequest, Model model) {
        OfficerCardModel officerCardModel = officerCardDelegate.getPoliceOfficerByOfficerId(officerId, httpServletRequest);

        model.addAttribute("commandCenter", officerCardModel.getCommandCenter());
        model.addAttribute("officerId", officerCardModel.getOfficerId());
        model.addAttribute("officerName", officerCardModel.getOfficerName());
        model.addAttribute("expiryDate", DateUtility.convertToFormat(officerCardModel.getExpiryDate(), "dd/MM/YYYY"));
        model.addAttribute("unit", officerCardModel.getUnit());
        model.addAttribute("rank", officerCardModel.getRank());
        model.addAttribute("weaponType", officerCardModel.getWeaponType());
        model.addAttribute("weaponSrl", officerCardModel.getWeaponSrl());
        model.addAttribute("isCarryWeapon", officerCardModel.getIsCarryWeapon().equals("Y") ? "Yes" : "No");
        model.addAttribute("bloodGroup", officerCardModel.getBloodGroup().toUpperCase());
        model.addAttribute("image", officerCardModel.getImage());

        return "card_officer";
    }
}

