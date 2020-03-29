package com.tpcmswebadmin.webpages.policevehicles.controller;

import com.tpcmswebadmin.webpages.policevehicles.delegate.PoliceVehicleDeleteDelegate;
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
public class PoliceVehicleDeleteController {

    private final PoliceVehicleDeleteDelegate policeVehicleDeleteDelegate;

    @GetMapping("policeVehicleDelete")
    public String deletePoliceVehicle(@RequestParam("vehicleId") String vehicleId,
                                    Model model, HttpServletRequest httpServletRequest) {

        policeVehicleDeleteDelegate.delete(vehicleId, httpServletRequest);

        return "redirect:/policeVehicles";
    }
}
