package com.tpcmswebadmin.service.policestaff.controller;

import com.tpcmswebadmin.infrastructure.client.response.ResponseDto;
import com.tpcmswebadmin.service.policestaff.domain.dto.PoliceStaffDto;
import com.tpcmswebadmin.service.policestaff.service.PoliceStaffClientCallServiceAPI;
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
public class PoliceStaffControllerAPI {

    private final PoliceStaffClientCallServiceAPI policeStaffServiceAPI;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("policeStaff")
    public ResponseDto<PoliceStaffDto> getPoliceOfficers(HttpServletRequest httpServletRequest) {
        return policeStaffServiceAPI.getResponseDto(httpServletRequest);
    }

}
