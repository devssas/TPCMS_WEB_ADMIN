package com.tpcmswebadmin.webpages.policeofficer.delegate;

import com.tpcmswebadmin.infrastructure.client.response.ResponseAPIDto;
import com.tpcmswebadmin.service.policeofficer.domain.dto.PoliceOfficerDto;
import com.tpcmswebadmin.service.policeofficer.service.PoliceOfficerClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PoliceOfficerViewDelegate {

    private final PoliceOfficerClientService policeOfficerClientService;

    public List<String> getPoliceOfficerStatuses(HttpServletRequest httpServletRequest) {
        ResponseAPIDto<PoliceOfficerDto> responseAPIDto = policeOfficerClientService.getResponseDto(httpServletRequest);

        return responseAPIDto.getData().getTbody().stream().map(PoliceOfficerDto::getStatus).distinct().collect(
                Collectors.toList());
    }

}
