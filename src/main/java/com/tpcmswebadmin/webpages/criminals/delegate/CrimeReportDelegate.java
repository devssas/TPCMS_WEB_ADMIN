package com.tpcmswebadmin.webpages.criminals.delegate;

import com.tpcmswebadmin.infrastructure.client.response.ResponseDto;
import com.tpcmswebadmin.service.criminals.domain.dto.CasesDto;
import com.tpcmswebadmin.service.criminals.domain.dto.CrimeReportDto;
import com.tpcmswebadmin.service.criminals.service.CrimeReportsClientService;
import com.tpcmswebadmin.service.criminals.service.CriminalProfileClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CrimeReportDelegate {

    private final CrimeReportsClientService crimeReportsClientService;

    private final CriminalProfileClientService criminalProfileClientService;

    public List<String> getManageCaseStatuses(HttpServletRequest httpServletRequest) {
        ResponseDto<CrimeReportDto> responseDto = crimeReportsClientService.getResponseDto(httpServletRequest);

        return responseDto.getData().getTbody().stream()
                .map(CrimeReportDto::getStatus)
                .distinct().collect(Collectors.toList());
    }

    public List<String> getCriminalCaseStatuses(HttpServletRequest httpServletRequest) {
        ResponseDto<CasesDto> responseDto = criminalProfileClientService.getResponseDto(httpServletRequest);

        return responseDto.getData().getTbody().stream()
                .map(CasesDto::getStatus)
                .distinct().collect(Collectors.toList());
    }

    public void getCrimeReportByReportId(String reportId, HttpServletRequest httpServletRequest) {
    }
}
