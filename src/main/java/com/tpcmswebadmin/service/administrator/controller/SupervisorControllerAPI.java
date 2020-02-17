package com.tpcmswebadmin.service.administrator.controller;

import com.tpcmswebadmin.infrastructure.client.response.ResponseDto;
import com.tpcmswebadmin.service.administrator.domain.SupervisorDto;
import com.tpcmswebadmin.service.administrator.service.SupervisorClientService;
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
    public ResponseDto<SupervisorDto> getSupervisors(HttpServletRequest httpServletRequest) {
        return supervisorClientService.getResponseDto(httpServletRequest);
    }
}
