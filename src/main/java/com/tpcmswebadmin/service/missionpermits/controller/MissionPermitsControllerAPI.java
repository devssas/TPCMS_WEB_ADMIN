package com.tpcmswebadmin.service.missionpermits.controller;

import com.tpcmswebadmin.infrastructure.client.response.ResponseDto;
import com.tpcmswebadmin.service.missionpermits.domain.MissionPermitsDto;
import com.tpcmswebadmin.service.missionpermits.service.MissionPermitsClientService;
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
public class MissionPermitsControllerAPI {

    private final MissionPermitsClientService missionPermitsClientService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("missionPermits")
    public ResponseDto<MissionPermitsDto> getSpecialMissions(HttpServletRequest httpServletRequest) {
        return missionPermitsClientService.getResponseDto(httpServletRequest);
    }
}
