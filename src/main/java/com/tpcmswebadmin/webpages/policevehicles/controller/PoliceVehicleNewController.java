package com.tpcmswebadmin.webpages.policevehicles.controller;

import com.tpcmswebadmin.infrastructure.domain.constant.Pages;
import com.tpcmswebadmin.infrastructure.domain.constant.TpCmsConstants;
import com.tpcmswebadmin.webpages.card.domain.VehicleCardModel;
import com.tpcmswebadmin.webpages.policevehicles.delegate.PoliceVehicleNewDelegate;
import com.tpcmswebadmin.webpages.policevehicles.dto.ResponseDto;
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
import static com.tpcmswebadmin.infrastructure.domain.enums.Roles.PROSECUTION;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PoliceVehicleNewController {

    private final PoliceVehicleNewDelegate policeVehicleNewDelegate;

    private final ReferenceDelegate referenceDelegate;

    @GetMapping("/newVehicle")
    public String getNewVehiclePage(Model model, HttpServletRequest httpServletRequest) {
        model.addAttribute("newPoliceVehicle", new PoliceVehicleNewModel());

        callAttributes(model, httpServletRequest);

        return "police_vehicle_create";
    }

    @PostMapping("/newVehicle")
    public String createNewVehicle(@Valid @ModelAttribute("newPoliceVehicle") PoliceVehicleNewModel policeVehicleNewModel,
                                   BindingResult bindingResult, Model model, HttpServletRequest httpServletRequest) {

        if (bindingResult.hasErrors()) {
            log.warn("Error on creating police vehicle {}", bindingResult.getAllErrors());
            return "police_vehicle_create";
        }

        ResponseDto response = policeVehicleNewDelegate.createVehicle(policeVehicleNewModel, httpServletRequest);

        if(response.isResult()) {
            return "redirect:/police_vehicles_view";
        } else {
            callAttributes(model, httpServletRequest);
            model.addAttribute("httpError", response.getMessage());
            return "police_vehicle_create";
        }
    }

    private void callAttributes(Model model, HttpServletRequest httpServletRequest) {
        model.addAttribute("officerName", httpServletRequest.getSession().getAttribute(TpCmsConstants.OFFICER_NAME));
        model.addAttribute("officerProfilePicture", httpServletRequest.getSession().getAttribute(TpCmsConstants.OFFICER_PROFILE_PICTURE));

        String adminRole = (String) httpServletRequest.getSession().getAttribute(TpCmsConstants.ACCESS_ROLE);
        model.addAttribute("accessRole", adminRole);
        model.addAttribute("weaponTypes", referenceDelegate.getAllWeaponTypes());
        model.addAttribute("officerUnits", referenceDelegate.getOfficerUnits());
        model.addAttribute("commandCenters", referenceDelegate.getCommandCenter());


        if (adminRole.equals(ADMIN.name())) {
            model.addAttribute("disabled", TpCmsConstants.LIST_DISABLE);
            model.addAttribute("dashboardPage", Pages.DASHBOARD_ADMIN_JSON);
        } else {
            model.addAttribute("dashboardPage", Pages.DASHBOARD_SUPERADMIN_JSON);
            model.addAttribute("prosecutorPage", Pages.MENU_BAR_SUPERADMIN_PROSECUTION_HOME);
        }
    }
}
