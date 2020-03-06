package com.tpcmswebadmin.service.policeofficer.controller;

import com.tpcmswebadmin.infrastructure.client.response.ResponseDto;
import com.tpcmswebadmin.service.policeofficer.domain.dto.ReportingOfficerDto;
import com.tpcmswebadmin.service.policeofficer.service.ReportingOfficerClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("api")
public class ReportingOfficerControllerAPI {

    private final ReportingOfficerClientService reportingOfficerClientService;

    @GetMapping("/reporting-officer/{name}")
    public ResponseDto<ReportingOfficerDto> getByName(@PathVariable("name") String name, HttpServletRequest httpServletRequest) {
        return reportingOfficerClientService.getResponseDto(name, httpServletRequest);
    }


}
