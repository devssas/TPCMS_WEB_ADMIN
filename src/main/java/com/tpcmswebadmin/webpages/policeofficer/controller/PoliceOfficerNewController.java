package com.tpcmswebadmin.webpages.policeofficer.controller;

import com.tpcmswebadmin.infrastructure.domain.constant.Pages;
import com.tpcmswebadmin.infrastructure.domain.constant.TpCmsConstants;
import com.tpcmswebadmin.infrastructure.domain.dto.ResponseMVCDto;
import com.tpcmswebadmin.webpages.policeofficer.delegate.PoliceOfficerNewDelegate;
import com.tpcmswebadmin.webpages.policeofficer.model.PoliceOfficerCreateModel;
import com.tpcmswebadmin.webpages.policevehicles.model.PoliceVehicleNewModel;
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
public class PoliceOfficerNewController {

    private final PoliceOfficerNewDelegate policeOfficerNewDelegate;

    private final ReferenceDelegate referenceDelegate;

    @GetMapping("/newOfficer")
    public String createNewOfficer(Model model, HttpServletRequest httpServletRequest) {
        callAttributes(model, httpServletRequest, new PoliceOfficerCreateModel());

        return "police_staff_create";
    }

    @PostMapping("/newOfficer")
    public String createOfficer(@Valid @ModelAttribute("newPoliceStaff") PoliceOfficerCreateModel policeOfficerCreateModel,
                                   BindingResult bindingResult, Model model, HttpServletRequest httpServletRequest) {

        if (bindingResult.hasErrors()) {
            log.warn("Error on creating police vehicle {}", bindingResult.getAllErrors());
            return "police_staff_create";
        }

        ResponseMVCDto response = policeOfficerNewDelegate.createOfficer(policeOfficerCreateModel, httpServletRequest);

        if(response.isResult()) {
            return "redirect:/officerProfiles";
        } else {
            callAttributes(model, httpServletRequest, policeOfficerCreateModel);
            model.addAttribute("httpError", response.getMessage());
            return "police_staff_create";
        }
    }

    private void callAttributes(Model model, HttpServletRequest httpServletRequest, PoliceOfficerCreateModel policeOfficerCreateModel) {
        model.addAttribute("newPoliceStaff", policeOfficerCreateModel);
        model.addAttribute("officerName", httpServletRequest.getSession().getAttribute(TpCmsConstants.OFFICER_NAME));
        model.addAttribute("officerProfilePicture", httpServletRequest.getSession().getAttribute(TpCmsConstants.OFFICER_PROFILE_PICTURE));
        model.addAttribute("weaponTypes", referenceDelegate.getAllWeaponTypes());
        model.addAttribute("officerGrades", referenceDelegate.getOfficerGrades());
        model.addAttribute("officerRanks", referenceDelegate.getOfficersRanks());


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
