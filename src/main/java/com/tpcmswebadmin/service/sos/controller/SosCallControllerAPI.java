package com.tpcmswebadmin.service.sos.controller;

import com.tpcmswebadmin.infrastructure.client.response.ResponseDto;
import com.tpcmswebadmin.service.sos.domain.SosCallDto;
import com.tpcmswebadmin.service.sos.service.SosCallsClientService;
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
public class SosCallControllerAPI {

    private final SosCallsClientService sosCallsClientService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("sosCall")
    public ResponseDto<SosCallDto> getPoliceVehicles(HttpServletRequest httpServletRequest) {
        return sosCallsClientService.getResponseDto(httpServletRequest);
    }
}
