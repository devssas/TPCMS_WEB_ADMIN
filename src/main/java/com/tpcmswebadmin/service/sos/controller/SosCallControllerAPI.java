package com.tpcmswebadmin.service.sos.controller;

import com.tpcmswebadmin.infrastructure.client.response.ResponseAPIDto;
import com.tpcmswebadmin.infrastructure.domain.LoginUserDo;
import com.tpcmswebadmin.service.calender.domain.CalendarDto;
import com.tpcmswebadmin.service.sos.domain.SosCallDetailDto;
import com.tpcmswebadmin.service.sos.domain.SosCallDetailMapDto;
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
    @GetMapping("sosCall/details/map")
    public SosCallDetailMapDto getSosCallDetailsMap(HttpServletRequest httpServletRequest) {
        LoginUserDo loginUserDo = LoginUserDo.makeLoginUser(httpServletRequest);

        return sosClientService.getSosDetailMap(null , loginUserDo);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("sosCall/details")
    public ResponseAPIDto<SosCallDetailDto> getSosCallDetails(@RequestParam("sosId") String sosId, HttpServletRequest httpServletRequest) {
        LoginUserDo loginUserDo = LoginUserDo.makeLoginUser(httpServletRequest);

        return sosClientService.getSosDetailById(sosId , loginUserDo);
    }
}
