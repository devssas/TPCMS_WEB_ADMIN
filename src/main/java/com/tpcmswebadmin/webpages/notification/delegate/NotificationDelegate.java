package com.tpcmswebadmin.webpages.notification.delegate;

import com.tpcmswebadmin.infrastructure.client.response.ResponseAPIDto;
import com.tpcmswebadmin.infrastructure.domain.LoginUserDo;
import com.tpcmswebadmin.infrastructure.domain.constant.TpCmsConstants;
import com.tpcmswebadmin.service.notification.domain.NotificationDto;
import com.tpcmswebadmin.service.notification.domain.enums.NotificationType;
import com.tpcmswebadmin.service.notification.service.NotificationClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationDelegate {

    private final NotificationClientService notificationClientService;

    public void updateNotifications(HttpServletRequest httpServletRequest) {
        notificationClientService.updateNotifications(LoginUserDo.makeLoginUser(httpServletRequest), NotificationType.NOTIFICATION);
    }

    public List<String> getNotificationStatuses(HttpServletRequest httpServletRequest) {
        ResponseAPIDto<NotificationDto> responseAPIDto = notificationClientService.getResponseDto(httpServletRequest);

        return responseAPIDto.getData().getTbody().stream().map(NotificationDto::getNatureOfAnnouncement).distinct().collect(Collectors.toList());
    }

}
