package com.tpcmswebadmin.webpages.policeofficer.controller;

import com.tpcmswebadmin.infrastructure.domain.constant.Pages;
import com.tpcmswebadmin.infrastructure.domain.constant.TpCmsConstants;
import com.tpcmswebadmin.infrastructure.domain.dto.ResponseMVCDto;
import com.tpcmswebadmin.webpages.policeofficer.delegate.PoliceOfficerUpdateDelegate;
import com.tpcmswebadmin.webpages.policeofficer.model.PoliceOfficerUpdateModel;
import com.tpcmswebadmin.webpages.policevehicles.delegate.PoliceVehicleUpdateDelegate;
import com.tpcmswebadmin.webpages.policevehicles.model.PoliceVehicleUpdateModel;
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
public class PoliceOfficerUpdateController {

    private final PoliceOfficerUpdateDelegate policeOfficerUpdateDelegate;

    private final ReferenceDelegate referenceDelegate;

    @GetMapping("updateOfficer")
    public String getPoliceOfficerUpdatePage(@RequestParam("officerId") String officerId, Model model, HttpServletRequest httpServletRequest) {
        PoliceOfficerUpdateModel policeOfficerUpdateModel = policeOfficerUpdateDelegate.getOfficerById(officerId, httpServletRequest);
        callAttributes(model, httpServletRequest, policeOfficerUpdateModel);

        return "police_staff_update";
    }

    @PostMapping("updateOfficer")
    public String updatePoliceVehicle(@Valid @ModelAttribute("updatePoliceStaff") PoliceOfficerUpdateModel policeOfficerUpdateModel,
                                      BindingResult bindingResult, Model model, HttpServletRequest httpServletRequest) {

        if (bindingResult.hasErrors()) {
            log.warn("Errors {}", bindingResult.getAllErrors());

            return "police_staff_update";
        }

        ResponseMVCDto response = policeOfficerUpdateDelegate.updateOfficer(policeOfficerUpdateModel, httpServletRequest);

        if(response.isResult()) {
            return "redirect:/officerProfiles";
        } else {
            callAttributes(model, httpServletRequest, policeOfficerUpdateModel);
            model.addAttribute("httpError", response.getMessage());
            return "police_staff_update";
        }
    }

    private void callAttributes(Model model, HttpServletRequest httpServletRequest, PoliceOfficerUpdateModel policeOfficerUpdateModel) {
        model.addAttribute("updatePoliceStaff", policeOfficerUpdateModel);
        model.addAttribute("officerName", httpServletRequest.getSession().getAttribute(TpCmsConstants.OFFICER_NAME));
        model.addAttribute("officerProfilePicture", httpServletRequest.getSession().getAttribute(TpCmsConstants.OFFICER_PROFILE_PICTURE));
        model.addAttribute("id", policeOfficerUpdateModel.getId());
        model.addAttribute("officerCode", policeOfficerUpdateModel.getOfficerCode());
        model.addAttribute("weaponTypes", referenceDelegate.getAllWeaponTypes());
        model.addAttribute("officerUnits", referenceDelegate.getOfficerUnits());
        model.addAttribute("commandCenters", referenceDelegate.getCommandCenter());
        model.addAttribute("officerGrades", referenceDelegate.getOfficerGrades());
        model.addAttribute("officerRanks", referenceDelegate.getOfficersRanks());
        model.addAttribute("reportingUnits", referenceDelegate.getOfficerUnits());

        String adminRole = (String) httpServletRequest.getSession().getAttribute(TpCmsConstants.ACCESS_ROLE);
        model.addAttribute("accessRole", adminRole);

        if(adminRole.equals(ADMIN.name())) {
            model.addAttribute("reportingUnits", referenceDelegate.getOfficerUnitsForAdmin(httpServletRequest));
            model.addAttribute("accessRoles", referenceDelegate.getAccessRolesForAdmin());
            model.addAttribute("disabled", TpCmsConstants.LIST_DISABLE);
            model.addAttribute("dashboardPage", Pages.DASHBOARD_ADMIN_JSON);
        } else {
            model.addAttribute("reportingUnits", referenceDelegate.getOfficerUnits());
            model.addAttribute("accessRoles", referenceDelegate.getAccessRoles());
            model.addAttribute("dashboardPage", Pages.DASHBOARD_SUPERADMIN_JSON);
            model.addAttribute("prosecutorPage", Pages.MENU_BAR_SUPERADMIN_PROSECUTION_HOME);
        }
    }
}
