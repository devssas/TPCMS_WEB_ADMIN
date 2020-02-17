package com.tpcmswebadmin.service.prosecutionoffice.controller;

import com.tpcmswebadmin.infrastructure.client.response.ResponseDto;
import com.tpcmswebadmin.service.criminals.domain.CasesDto;
import com.tpcmswebadmin.service.criminals.service.CriminalProfileClientService;
import com.tpcmswebadmin.service.prosecutionoffice.service.ProsecutionCriminalsProfileClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/prosecution")
public class ProsecutionOfficeControllerAPI {

    private final CriminalProfileClientService criminalProfileClientService;

    private final ProsecutionCriminalsProfileClientService prosecutionCriminalsProfileClientService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("casesHistory")
    public ResponseDto<CasesDto> getCasesHistory(HttpServletRequest httpServletRequest) {
        return prosecutionCriminalsProfileClientService.getResponseDto(httpServletRequest);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("cases")
    public ResponseDto<CasesDto> getManageCases(HttpServletRequest httpServletRequest) {
        return criminalProfileClientService.getResponseDto(httpServletRequest);
    }
}
