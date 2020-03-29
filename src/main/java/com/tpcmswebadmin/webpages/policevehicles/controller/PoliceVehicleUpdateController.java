package com.tpcmswebadmin.webpages.policevehicles.controller;

import com.tpcmswebadmin.infrastructure.domain.constant.Pages;
import com.tpcmswebadmin.infrastructure.domain.constant.TpCmsConstants;
import com.tpcmswebadmin.infrastructure.domain.dto.ResponseMVCDto;
import com.tpcmswebadmin.webpages.missionpermits.model.MissionPermitCardUpdateModel;
import com.tpcmswebadmin.webpages.policevehicles.delegate.PoliceVehicleDeleteDelegate;
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
public class PoliceVehicleUpdateController {

    private final PoliceVehicleUpdateDelegate policeVehicleUpdateDelegate;

    private final ReferenceDelegate referenceDelegate;

    @GetMapping("policeVehicleUpdate")
    public String getPoliceVehicleUpdatePage(@RequestParam("vehicleId") String vehicleId,
                                        Model model, HttpServletRequest httpServletRequest) {
        PoliceVehicleUpdateModel policeVehicleUpdateModel = policeVehicleUpdateDelegate.getVehicle(vehicleId, httpServletRequest);
        callAttributes(model, httpServletRequest, policeVehicleUpdateModel);

        return "police_vehicle_update";
    }

    @PostMapping("policeVehicleUpdate")
    public String updatePoliceVehicle(@Valid @ModelAttribute("updatePoliceVehicle") PoliceVehicleUpdateModel policeVehicleUpdateModel,
                                    BindingResult bindingResult, Model model, HttpServletRequest httpServletRequest) {

        if (bindingResult.hasErrors()) {
            log.warn("Errors {}", bindingResult.getAllErrors());

            return "police_vehicle_update";
        }

        ResponseMVCDto response = policeVehicleUpdateDelegate.updateVehicle(policeVehicleUpdateModel, httpServletRequest);

        if(response.isResult()) {
            return "redirect:/policeVehicles";
        } else {
            callAttributes(model, httpServletRequest, policeVehicleUpdateModel);
            model.addAttribute("httpError", response.getMessage());
            return "police_vehicle_update";
        }
    }

    private void callAttributes(Model model, HttpServletRequest httpServletRequest, PoliceVehicleUpdateModel policeVehicleUpdateModel) {
        model.addAttribute("updatePoliceVehicle", policeVehicleUpdateModel);
        model.addAttribute("officerName", httpServletRequest.getSession().getAttribute(TpCmsConstants.OFFICER_NAME));
        model.addAttribute("officerProfilePicture", httpServletRequest.getSession().getAttribute(TpCmsConstants.OFFICER_PROFILE_PICTURE));
        model.addAttribute("weaponTypes", referenceDelegate.getAllWeaponTypes());

        String adminRole = (String) httpServletRequest.getSession().getAttribute(TpCmsConstants.ACCESS_ROLE);
        model.addAttribute("accessRole", adminRole);

        if(adminRole.equals(ADMIN.name())) {
            model.addAttribute("reportingUnits", referenceDelegate.getOfficerUnitsForAdmin(httpServletRequest));
            model.addAttribute("accessRoles", referenceDelegate.getAccessRolesForAdmin());
            model.addAttribute("disabled", TpCmsConstants.LIST_DISABLE);
            model.addAttribute("dashboardPage", Pages.DASHBOARD_ADMIN_JSON);
            model.addAttribute("commandCenters", referenceDelegate.getCommandCenterForAdmin(httpServletRequest));
        } else {
            model.addAttribute("reportingUnits", referenceDelegate.getOfficerUnits());
            model.addAttribute("accessRoles", referenceDelegate.getAccessRoles());
            model.addAttribute("dashboardPage", Pages.DASHBOARD_SUPERADMIN_JSON);
            model.addAttribute("prosecutorPage", Pages.MENU_BAR_SUPERADMIN_PROSECUTION_HOME);
            model.addAttribute("commandCenters", referenceDelegate.getCommandCenter());
        }
    }
}
