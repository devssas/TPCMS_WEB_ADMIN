package com.tpcmswebadmin.webpages.notification.delegate;

import com.tpcmswebadmin.infrastructure.client.response.ResponseDto;
import com.tpcmswebadmin.infrastructure.domain.LoginUserDo;
import com.tpcmswebadmin.infrastructure.domain.constant.TpCmsConstants;
import com.tpcmswebadmin.service.notification.domain.NotificationDto;
import com.tpcmswebadmin.service.notification.domain.enums.NotificationType;
import com.tpcmswebadmin.service.notification.service.NotificationClientCreateService;
import com.tpcmswebadmin.service.notification.service.NotificationClientService;
import com.tpcmswebadmin.service.sos.service.SosClientCreateService;
import com.tpcmswebadmin.webpages.notification.model.NotificationCreateModel;
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

    private final NotificationClientCreateService notificationClientCreateService;

    private final SosClientCreateService sosClientCreateService;

    public void createNotification(NotificationCreateModel notificationCreateModel, HttpServletRequest httpServletRequest) {
        LoginUserDo loginUserDo = makeLoginUser(httpServletRequest);

        if(NotificationType.NOTIFICATION.equals(notificationCreateModel.getNotificationType()))
            notificationClientCreateService.create(notificationCreateModel, loginUserDo);
        else
            sosClientCreateService.create(notificationCreateModel, loginUserDo);
    }

    public void updateNotifications(HttpServletRequest httpServletRequest) {
        notificationClientService.updateNotifications(makeLoginUser(httpServletRequest), NotificationType.NOTIFICATION);
    }

    public List<String> getNotificationStatuses(HttpServletRequest httpServletRequest) {
        ResponseDto<NotificationDto> responseDto = notificationClientService.getResponseDto(httpServletRequest);

        return responseDto.getData().getTbody().stream().map(NotificationDto::getNatureOfAnnouncement).distinct().collect(Collectors.toList());
    }

    private LoginUserDo makeLoginUser(HttpServletRequest httpServletRequest) {
        return LoginUserDo.builder()
                .loginOfficersCode((String) httpServletRequest.getSession().getAttribute(TpCmsConstants.OFFICER_CODE))
                .loginOfficerUnitNumber((String) httpServletRequest.getSession().getAttribute(TpCmsConstants.REPORT_UNIT))
                .mobileAppDeviceId((String) httpServletRequest.getSession().getAttribute(TpCmsConstants.MOBILE_APP_DEVICE_ID))
                .build();
    }
}
