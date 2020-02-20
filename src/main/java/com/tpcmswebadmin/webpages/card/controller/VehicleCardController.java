package com.tpcmswebadmin.webpages.card.controller;

import com.tpcmswebadmin.infrastructure.utils.DateUtility;
import com.tpcmswebadmin.webpages.card.delegate.VehicleCardDelegate;
import com.tpcmswebadmin.webpages.card.domain.VehicleCardModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class VehicleCardController {

    private final VehicleCardDelegate vehicleCardDelegate;

    @GetMapping("/vehicle/card")
    public String getDashboard(@RequestParam("vehicleId") String vehicleId, HttpServletRequest httpServletRequest, Model model) {
        VehicleCardModel vehicleCardModel = vehicleCardDelegate.getVehicleDetailsByVehicleId(vehicleId, httpServletRequest);

        model.addAttribute("vehicleName", vehicleCardModel.getName());
        model.addAttribute("commandCenter", vehicleCardModel.getCommandCenter() == null ? "-" :vehicleCardModel.getCommandCenter());
        model.addAttribute("vehicleId", vehicleCardModel.getVehicleId() == null ? "-" :vehicleCardModel.getVehicleId());
        model.addAttribute("unit", vehicleCardModel.getUnit() == null ? "-" :vehicleCardModel.getUnit());
        model.addAttribute("plateNumber", vehicleCardModel.getPlateNumber() == null ? "-" :vehicleCardModel.getPlateNumber());
        model.addAttribute("expiryDate", DateUtility.convertToFormat(vehicleCardModel.getExpiryDate(), "dd/MM/YYYY"));
        model.addAttribute("weaponType", vehicleCardModel.getWeaponType() == null ? "-" :vehicleCardModel.getWeaponType());
        model.addAttribute("weaponSrl", vehicleCardModel.getWeaponSrl() == null ? "-" :vehicleCardModel.getWeaponSrl());
        model.addAttribute("hasPermissionToCarryWeapon", vehicleCardModel.getHasPermissionToCarryWeapon().equals("Y") ? "Yes" : "No");
        model.addAttribute("hasPermissionToCarryCivilians", vehicleCardModel.getHasPermissionToCarryCivilians());
        model.addAttribute("hasPermissionToCarryPrisoner", vehicleCardModel.getHasPermissionToCarryPrisoners());
        model.addAttribute("hasPermissionToNightPatrol", vehicleCardModel.getHasPermissionToNightPatrol());
        model.addAttribute("hasPermissionToDriveOutsideCity", vehicleCardModel.getHasPermissionToDriverOutsideCity());
        model.addAttribute("image", vehicleCardModel.getImage());

        return "card_vehicle";
    }

}
