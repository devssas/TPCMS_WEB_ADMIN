package com.tpcmswebadmin.webpages.card.controller;

import com.tpcmswebadmin.infrastructure.utils.DateUtility;
import com.tpcmswebadmin.webpages.card.delegate.MissionCardDelegate;
import com.tpcmswebadmin.webpages.card.domain.MissionCardModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class MissionCardController {

    private final MissionCardDelegate missionCardDelegate;

    @GetMapping("/card/mission")
    public String getMissionCard(@RequestParam("missionId") String missionId,
                                 @RequestParam("missionQrCode") String missionQrCode,
                                 HttpServletRequest httpServletRequest, Model model) {
        MissionCardModel missionCardModel = missionCardDelegate.getMissionCardByMissionId(missionId, missionQrCode, httpServletRequest);

        model.addAttribute("commandCenter", missionCardModel.getCommandCenter());
        model.addAttribute("officerId", missionCardModel.getOfficerId());
        model.addAttribute("officerName", missionCardModel.getOfficerName());
        model.addAttribute("expiryDate", DateUtility.convertToFormat(missionCardModel.getExpiryDate(), "dd/MM/YYYY"));
        model.addAttribute("unit", missionCardModel.getUnit());
        model.addAttribute("rank", missionCardModel.getRank());
        model.addAttribute("isPermittedCarryWeapon", missionCardModel.getIsPermittedCarryWeapon().equals("Y") ? "Yes" : "No");
        model.addAttribute("weaponType", missionCardModel.getWeaponType());
        model.addAttribute("missionType", missionCardModel.getMissionType());
        model.addAttribute("missionDescription", missionCardModel.getMissionDescription());
        model.addAttribute("image", missionCardModel.getImage());

        return "card_mission";
    }
}
