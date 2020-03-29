package com.tpcmswebadmin.service.superadmin.controller;

import com.tpcmswebadmin.infrastructure.client.response.ResponseAPIDto;
import com.tpcmswebadmin.service.superadmin.domain.SupervisorDto;
import com.tpcmswebadmin.service.superadmin.service.SupervisorClientService;
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
public class SupervisorControllerAPI {

    private final SupervisorClientService supervisorClientService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("supervisor")
    public ResponseAPIDto<SupervisorDto> getSupervisors(HttpServletRequest httpServletRequest) {
        return supervisorClientService.getResponseDto(httpServletRequest);
    }
}
