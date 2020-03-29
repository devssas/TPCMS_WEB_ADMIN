package com.tpcmswebadmin.webpages.missionpermits.controller;

import com.tpcmswebadmin.webpages.missionpermits.delegate.MissionPermitsDeleteDelegate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MissionPermitsDeleteController {

    private final MissionPermitsDeleteDelegate missionPermitsDeleteDelegate;

    @GetMapping("missionPermitsDelete")
    public String deleteMissionCard(@RequestParam("missionId") String missionId,
                                    @RequestParam("missionQrCode") String missionQrCode,
                                    @RequestParam("officerId") String officerId,
                                        Model model, HttpServletRequest httpServletRequest) {
        missionPermitsDeleteDelegate.delete(missionId, missionQrCode, officerId, httpServletRequest);

        return "redirect:/missionPermitsView";
    }
}
