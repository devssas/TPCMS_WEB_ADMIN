package com.tpcmswebadmin.service.policevehicles.controller;

import com.tpcmswebadmin.infrastructure.client.response.ResponseAPIDto;
import com.tpcmswebadmin.service.policevehicles.domain.dto.PoliceVehicleDto;
import com.tpcmswebadmin.service.policevehicles.service.PoliceVehicleClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("api")
public class PoliceVehicleControllerAPI {

    private final PoliceVehicleClientService policeVehicleClientService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("policeVehicle")
    public ResponseAPIDto<PoliceVehicleDto> getPoliceVehicles(@RequestParam(value = "search", required = false) String search,
                                                              @RequestParam(value = "status", required = false) String status,
                                                              HttpServletRequest httpServletRequest) {

        if(search != null)
            httpServletRequest.getSession().setAttribute("search", search);

        if(status != null)
            httpServletRequest.getSession().setAttribute("status", status);

        return policeVehicleClientService.getResponseDto(httpServletRequest);
    }

}
