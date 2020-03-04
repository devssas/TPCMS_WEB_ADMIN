package com.tpcmswebadmin.webpages.policevehicles.delegate;

import com.tpcmswebadmin.infrastructure.client.response.ResponseDto;
import com.tpcmswebadmin.service.policevehicles.domain.dto.PoliceVehicleDto;
import com.tpcmswebadmin.service.policevehicles.service.PoliceVehicleClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PoliceVehicleViewDelegate {

    private final PoliceVehicleClientService policeVehicleClientService;

    public List<String> getPoliceVehicleStatuses(HttpServletRequest httpServletRequest) {
        ResponseDto<PoliceVehicleDto> responseDto = policeVehicleClientService.getResponseDto(httpServletRequest);

        return responseDto.getData().getTbody().stream().map(PoliceVehicleDto::getStatus).distinct().collect(
                Collectors.toList());
    }
}
