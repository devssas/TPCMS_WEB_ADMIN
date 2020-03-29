package com.tpcmswebadmin.webpages.superadmin.delegate;

import com.tpcmswebadmin.infrastructure.client.response.ResponseAPIDto;
import com.tpcmswebadmin.service.superadmin.domain.SupervisorDto;
import com.tpcmswebadmin.service.superadmin.service.SupervisorClientService;
import com.tpcmswebadmin.webpages.superadmin.model.ScreenInfoModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class SupervisorViewDelegate {

    private final SupervisorClientService supervisorClientService;

    public ScreenInfoModel getScreenInfo(HttpServletRequest httpServletRequest) {
        ResponseAPIDto<SupervisorDto> response = supervisorClientService.getResponseDto(httpServletRequest);

        return ScreenInfoModel.builder()
                .statuses(getUserTypes(response))
                .userTypes(getStatuses(response))
                .build();
    }

    public List<String> getUserTypes(ResponseAPIDto<SupervisorDto> response) {
        return response.getData().getTbody().stream()
                .map(SupervisorDto::getAccessRole)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<String> getStatuses(ResponseAPIDto<SupervisorDto> response) {
        return response.getData().getTbody().stream()
                .map(SupervisorDto::getStatus)
                .distinct()
                .collect(Collectors.toList());
    }
}
