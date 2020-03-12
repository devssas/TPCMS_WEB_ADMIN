package com.tpcmswebadmin.service.sos.controller;

import com.tpcmswebadmin.infrastructure.client.response.ResponseAPIDto;
import com.tpcmswebadmin.service.sos.domain.SosCallDetailDto;
import com.tpcmswebadmin.service.sos.domain.SosCallDto;
import com.tpcmswebadmin.service.sos.service.SosClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("api")
public class SosCallControllerAPI {

    private final SosClientService sosClientService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("sosCall")
    public ResponseAPIDto<SosCallDto> getSosCalls(HttpServletRequest httpServletRequest) {
        return sosClientService.getResponseDto(httpServletRequest);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("sosCall/details")
    public SosCallDetailDto getSosCallDetails(HttpServletRequest httpServletRequest) {
        return sosClientService.getSosDetailById(httpServletRequest);
    }
}
