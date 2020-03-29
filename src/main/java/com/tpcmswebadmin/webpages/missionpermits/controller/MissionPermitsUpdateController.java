package com.tpcmswebadmin.webpages.missionpermits.controller;

import com.tpcmswebadmin.infrastructure.domain.constant.Pages;
import com.tpcmswebadmin.infrastructure.domain.constant.TpCmsConstants;
import com.tpcmswebadmin.infrastructure.domain.dto.ResponseMVCDto;
import com.tpcmswebadmin.webpages.missionpermits.delegate.MissionPermitsUpdateDelegate;
import com.tpcmswebadmin.webpages.missionpermits.model.MissionPermitCardCreateModel;
import com.tpcmswebadmin.webpages.missionpermits.model.MissionPermitCardUpdateModel;
import com.tpcmswebadmin.webpages.reference.delegate.ReferenceDelegate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static com.tpcmswebadmin.infrastructure.domain.enums.Roles.ADMIN;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MissionPermitsUpdateController {

    private final MissionPermitsUpdateDelegate missionPermitsUpdateDelegate;

    private final ReferenceDelegate referenceDelegate;

    @GetMapping("missionPermitsUpdate")
    public String getNewMissionCardPage(@RequestParam("missionId") String missionId, @RequestParam("missionQrCode") String missionQrCode,
                                        Model model, HttpServletRequest httpServletRequest) {
        MissionPermitCardUpdateModel missionPermitCardUpdateModel = missionPermitsUpdateDelegate.getMissionCard(missionId, missionQrCode,httpServletRequest);
        callAttributes(model, httpServletRequest, missionPermitCardUpdateModel);

        return "mission_permits_update";
    }

    @PostMapping("missionPermitsUpdate")
    public String updateMissionCard(@RequestParam("missionId") String missionId, @RequestParam("missionQrCode") String missionQrCode,
                                    @Valid @ModelAttribute("updateMissionPermit") MissionPermitCardUpdateModel missionPermitCardUpdateModel,
                                    BindingResult bindingResult, Model model, HttpServletRequest httpServletRequest) {

        if (bindingResult.hasErrors()) {
            log.warn("Errors {}", bindingResult.getAllErrors());

            return "mission_permits_update";
        }

        ResponseMVCDto response = missionPermitsUpdateDelegate.updateMissionPermitCard(missionId, missionQrCode, missionPermitCardUpdateModel, httpServletRequest);

        if(response.isResult()) {
            return "redirect:/missionPermitsView";
        } else {
            callAttributes(model, httpServletRequest, missionPermitCardUpdateModel);
            model.addAttribute("httpError", response.getMessage());
            return "mission_permits_update";
        }
    }

    private void callAttributes(Model model, HttpServletRequest httpServletRequest, MissionPermitCardUpdateModel missionPermitCardUpdateModel) {
        model.addAttribute("updateMissionPermit", missionPermitCardUpdateModel);
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
