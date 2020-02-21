package com.tpcmswebadmin.service.policevehicles.controller;

import com.tpcmswebadmin.infrastructure.client.response.ResponseDto;
import com.tpcmswebadmin.service.policevehicles.domain.dto.PoliceVehicleDto;
import com.tpcmswebadmin.service.policevehicles.service.PoliceVehicleClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("api")
public class PoliceVehicleControllerAPI {

    private final PoliceVehicleClientService policeVehicleClientService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("policeVehicle")
    public ResponseDto<PoliceVehicleDto> getPoliceVehicles(HttpServletRequest httpServletRequest) {
        return policeVehicleClientService.getResponseDto(httpServletRequest);
    }

}
