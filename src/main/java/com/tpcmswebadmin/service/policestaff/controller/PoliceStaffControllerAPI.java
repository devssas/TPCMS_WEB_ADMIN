package com.tpcmswebadmin.service.policestaff.controller;

import com.tpcmswebadmin.infrastructure.client.response.ResponseDto;
import com.tpcmswebadmin.service.policestaff.domain.dto.PoliceStaffDto;
import com.tpcmswebadmin.service.policestaff.service.PoliceStaffClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("api")
public class PoliceStaffControllerAPI {

    private final PoliceStaffClientService policeStaffServiceAPI;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("policeStaff")
    public ResponseDto<PoliceStaffDto> getPoliceOfficers(HttpServletRequest httpServletRequest) {
        return policeStaffServiceAPI.getResponseDto(httpServletRequest);
    }

}
