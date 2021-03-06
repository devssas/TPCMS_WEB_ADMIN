package com.tpcmswebadmin.service.prosecutionoffice.controller;

import com.tpcmswebadmin.infrastructure.client.response.ResponseAPIDto;
import com.tpcmswebadmin.service.prosecutionoffice.domain.ProsecutionCasesDto;
import com.tpcmswebadmin.service.prosecutionoffice.service.ProsecutionCasesHistoryClientService;
import com.tpcmswebadmin.service.prosecutionoffice.service.ProsecutionCasesRequestEvidenceClientService;
import com.tpcmswebadmin.service.prosecutionoffice.service.ProsecutionCasesSubmitReviewClientService;
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

    private final ProsecutionCasesSubmitReviewClientService prosecutionCasesSubmitReviewClientService;

    private final ProsecutionCasesRequestEvidenceClientService prosecutionCasesRequestEvidenceClientService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("casesHistory")
    public ResponseAPIDto<ProsecutionCasesDto> getCasesHistory(HttpServletRequest httpServletRequest) {
        return prosecutionCasesHistoryClientService.getResponseDto(httpServletRequest);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("cases")
    public ResponseAPIDto<ProsecutionCasesDto> getManageCases(HttpServletRequest httpServletRequest) {
        return prosecutionManageCasesClientService.getResponseDto(httpServletRequest);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("cases/evidence")
    public ResponseAPIDto<ProsecutionCasesDto> getRequestForEvidence(HttpServletRequest httpServletRequest) {
        return prosecutionCasesRequestEvidenceClientService.getResponseDto(httpServletRequest);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("cases/review")
    public ResponseAPIDto<ProsecutionCasesDto> getSubmitForReview(HttpServletRequest httpServletRequest) {
        return prosecutionCasesSubmitReviewClientService.getResponseDto(httpServletRequest);
    }

}
