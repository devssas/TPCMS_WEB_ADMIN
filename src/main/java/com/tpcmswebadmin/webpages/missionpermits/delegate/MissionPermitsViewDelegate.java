package com.tpcmswebadmin.webpages.missionpermits.delegate;

import com.tpcmswebadmin.infrastructure.client.response.ResponseAPIDto;
import com.tpcmswebadmin.service.missionpermits.domain.MissionPermitsDto;
import com.tpcmswebadmin.service.missionpermits.service.MissionPermitsClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class MissionPermitsViewDelegate {

    private final MissionPermitsClientService missionPermitsClientService;

    public List<String> getMissionPermitStatuses(HttpServletRequest httpServletRequest) {
        ResponseAPIDto<MissionPermitsDto> responseAPIDto = missionPermitsClientService.getResponseDto(httpServletRequest);

        return responseAPIDto.getData().getTbody().stream().map(MissionPermitsDto::getStatus).distinct().collect(
                Collectors.toList());
    }
}
