package com.tpcmswebadmin.webpages.sos.delegate;

import com.tpcmswebadmin.infrastructure.client.response.ResponseAPIDto;
import com.tpcmswebadmin.infrastructure.domain.LoginUserDo;
import com.tpcmswebadmin.infrastructure.domain.constant.TpCmsConstants;
import com.tpcmswebadmin.service.notification.domain.enums.NotificationType;
import com.tpcmswebadmin.service.notification.service.NotificationClientService;
import com.tpcmswebadmin.service.policevehicles.domain.dto.PoliceVehicleDto;
import com.tpcmswebadmin.service.sos.domain.SosCallDto;
import com.tpcmswebadmin.service.sos.service.SosClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class SosViewDelegate {

    private final NotificationClientService notificationClientService;

    private final SosClientService sosClientService;

    public void updateSOSNotifications(HttpServletRequest httpServletRequest) {
        LoginUserDo loginUserDo = LoginUserDo.makeLoginUser(httpServletRequest);

        notificationClientService.updateNotifications(loginUserDo, NotificationType.SOS);
    }

    public List<String> getSosStatuses(HttpServletRequest httpServletRequest) {
        ResponseAPIDto<SosCallDto> responseAPIDto = sosClientService.getResponseDto(httpServletRequest);

        return responseAPIDto.getData().getTbody().stream().map(SosCallDto::getStatus).distinct().collect(
                Collectors.toList());
    }
}
