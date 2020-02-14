package com.tpcmswebadmin.service.criminals.controller;

import com.tpcmswebadmin.infrastructure.client.response.ResponseDto;
import com.tpcmswebadmin.service.criminals.domain.CasesDto;
import com.tpcmswebadmin.service.criminals.domain.CrimeReportDto;
import com.tpcmswebadmin.service.criminals.service.CrimeReportsServiceAPI;
import com.tpcmswebadmin.service.criminals.service.CriminalProfileServiceAPI;
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

    private final CrimeReportsServiceAPI crimeReportsServiceAPI;

    private final CriminalProfileServiceAPI criminalProfileServiceAPI;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("crimeReports")
    public ResponseDto<CrimeReportDto> getPoliceOfficers(HttpServletRequest httpServletRequest) {
        return crimeReportsServiceAPI.getResponseDto(httpServletRequest);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("criminalCases")
    public ResponseDto<CasesDto> getManageCases(HttpServletRequest httpServletRequest) {
        return criminalProfileServiceAPI.getResponseDto(httpServletRequest);
    }
}
