package com.tpcmswebadmin.service.policeofficer.controller;

import com.tpcmswebadmin.infrastructure.client.response.ResponseDto;
import com.tpcmswebadmin.service.policeofficer.domain.dto.ReportingOfficerDto;
import com.tpcmswebadmin.service.policeofficer.service.ReportingOfficerClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("api")
public class ReportingOfficerControllerAPI {

    private final ReportingOfficerClientService reportingOfficerClientService;

    @PostMapping("reporting-officer/{name}")
    public ResponseDto<ReportingOfficerDto> getByName(@PathVariable("name") String name, HttpServletRequest httpServletRequest) {
        return reportingOfficerClientService.getResponseDto(name, httpServletRequest);
    }


}
