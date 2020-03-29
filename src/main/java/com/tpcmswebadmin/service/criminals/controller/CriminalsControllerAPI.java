package com.tpcmswebadmin.service.criminals.controller;

import com.tpcmswebadmin.infrastructure.client.response.ResponseAPIDto;
import com.tpcmswebadmin.service.criminals.domain.dto.CasesDto;
import com.tpcmswebadmin.service.criminals.domain.dto.CrimeReportDto;
import com.tpcmswebadmin.service.criminals.service.CrimeReportsClientService;
import com.tpcmswebadmin.service.criminals.service.CriminalProfileClientService;
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
public class CriminalsControllerAPI {

    private final CrimeReportsClientService crimeReportsClientService;

    private final CriminalProfileClientService criminalProfileClientService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("crimeReports")
    public ResponseAPIDto<CrimeReportDto> getCrimeReports(HttpServletRequest httpServletRequest) {
        return crimeReportsClientService.getResponseDto(httpServletRequest);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("criminalCases")
    public ResponseAPIDto<CasesDto> getManageCases(HttpServletRequest httpServletRequest) {
        return criminalProfileClientService.getResponseDto(httpServletRequest);
    }
}
