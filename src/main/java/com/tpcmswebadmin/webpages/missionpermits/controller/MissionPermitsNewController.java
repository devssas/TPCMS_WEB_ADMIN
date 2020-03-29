package com.tpcmswebadmin.webpages.missionpermits.controller;

import com.tpcmswebadmin.infrastructure.domain.constant.Pages;
import com.tpcmswebadmin.infrastructure.domain.constant.TpCmsConstants;
import com.tpcmswebadmin.infrastructure.domain.dto.ResponseMVCDto;
import com.tpcmswebadmin.webpages.missionpermits.delegate.MissionPermitsNewDelegate;
import com.tpcmswebadmin.webpages.missionpermits.model.MissionPermitCardCreateModel;
import com.tpcmswebadmin.webpages.reference.delegate.ReferenceDelegate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static com.tpcmswebadmin.infrastructure.domain.enums.Roles.ADMIN;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MissionPermitsNewController {

    private final MissionPermitsNewDelegate missionPermitsNewDelegate;

    private final ReferenceDelegate referenceDelegate;

    @GetMapping("missionPermitsNew")
    public String getNewMissionCardPage(Model model, HttpServletRequest httpServletRequest) {
        callAttributes(model, httpServletRequest, new MissionPermitCardCreateModel());

        return "mission_permits_new";
    }

    @PostMapping("missionPermitsNew")
    public String newMissionCardPage(@Valid @ModelAttribute("newMissionPermit") MissionPermitCardCreateModel missionPermitCardCreateModel,
                                                 BindingResult bindingResult, Model model, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            log.warn("Errors {}", bindingResult.getAllErrors());

            return "mission_permits_new";
        }

        ResponseMVCDto response = missionPermitsNewDelegate.createNewMissionCard(missionPermitCardCreateModel, httpServletRequest);

        if(response.isResult()) {
            return "redirect:/missionPermitsView";
        } else {
            callAttributes(model, httpServletRequest, missionPermitCardCreateModel);
            model.addAttribute("httpError", response.getMessage());
            return "mission_permits_new";
        }
    }

    private void callAttributes(Model model, HttpServletRequest httpServletRequest, MissionPermitCardCreateModel missionPermitCardCreateModel) {
        model.addAttribute("newMissionPermit", missionPermitCardCreateModel);
        model.addAttribute("officerName", httpServletRequest.getSession().getAttribute(TpCmsConstants.OFFICER_NAME));
        model.addAttribute("officerProfilePicture", httpServletRequest.getSession().getAttribute(TpCmsConstants.OFFICER_PROFILE_PICTURE));
        model.addAttribute("weaponTypes", referenceDelegate.getAllWeaponTypes());

        String adminRole = (String) httpServletRequest.getSession().getAttribute(TpCmsConstants.ACCESS_ROLE);
        model.addAttribute("accessRole", adminRole);

        if(adminRole.equals(ADMIN.name())) {
            model.addAttribute("disabled", TpCmsConstants.LIST_DISABLE);
            model.addAttribute("dashboardPage", Pages.DASHBOARD_ADMIN_JSON);
        } else {
            model.addAttribute("dashboardPage", Pages.DASHBOARD_SUPERADMIN_JSON);
            model.addAttribute("prosecutorPage", Pages.MENU_BAR_SUPERADMIN_PROSECUTION_HOME);
        }
    }

}
