package com.tpcmswebadmin.service.policeofficer.controller;

import com.tpcmswebadmin.infrastructure.client.response.ResponseDto;
import com.tpcmswebadmin.service.policeofficer.domain.dto.PoliceOfficerDto;
import com.tpcmswebadmin.service.policeofficer.service.PoliceOfficerClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("api")
public class PoliceOfficerControllerAPI {

    private final PoliceOfficerClientService policeOfficerClientService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("policeStaff")
    public ResponseDto<PoliceOfficerDto> getPoliceOfficers(HttpServletRequest httpServletRequest) {
        return policeOfficerClientService.getResponseDto(httpServletRequest);
    }

}
