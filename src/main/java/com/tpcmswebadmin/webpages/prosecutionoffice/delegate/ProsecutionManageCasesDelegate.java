package com.tpcmswebadmin.webpages.prosecutionoffice.delegate;

import com.tpcmswebadmin.infrastructure.client.response.ResponseAPIDto;
import com.tpcmswebadmin.service.prosecutionoffice.domain.ProsecutionCasesDto;
import com.tpcmswebadmin.service.prosecutionoffice.service.ProsecutionManageCasesClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProsecutionManageCasesDelegate {

    private final ProsecutionManageCasesClientService prosecutionManageCasesClientService;

    public List<String> getCriminalCaseStatuses(HttpServletRequest httpServletRequest) {
        ResponseAPIDto<ProsecutionCasesDto> responseAPIDto = prosecutionManageCasesClientService.getResponseDto(httpServletRequest);

        return responseAPIDto.getData().getTbody().stream()
                .map(ProsecutionCasesDto::getStatus)
                .distinct().collect(Collectors.toList());
    }
}
