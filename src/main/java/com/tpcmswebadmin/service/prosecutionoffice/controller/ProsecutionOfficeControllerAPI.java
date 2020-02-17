package com.tpcmswebadmin.service.prosecutionoffice.controller;

import com.tpcmswebadmin.infrastructure.client.response.ResponseDto;
import com.tpcmswebadmin.service.prosecutionoffice.domain.ProsecutionCasesDto;
import com.tpcmswebadmin.service.prosecutionoffice.service.ProsecutionCasesHistoryClientService;
import com.tpcmswebadmin.service.prosecutionoffice.service.ProsecutionManageCasesClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/prosecution")
public class ProsecutionOfficeControllerAPI {

    private final ProsecutionCasesHistoryClientService prosecutionCasesHistoryClientService;

    private final ProsecutionManageCasesClientService prosecutionManageCasesClientService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("casesHistory")
    public ResponseDto<ProsecutionCasesDto> getCasesHistory(HttpServletRequest httpServletRequest) {
        return prosecutionCasesHistoryClientService.getResponseDto(httpServletRequest);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("cases")
    public ResponseDto<ProsecutionCasesDto> getManageCases(HttpServletRequest httpServletRequest) {
        return prosecutionManageCasesClientService.getResponseDto(httpServletRequest);
    }
}
