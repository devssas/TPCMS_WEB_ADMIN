package com.tpcmswebadmin.webpages.notification.delegate;

import com.tpcmswebadmin.infrastructure.domain.LoginUserDo;
import com.tpcmswebadmin.infrastructure.domain.constant.TpCmsConstants;
import com.tpcmswebadmin.service.notification.domain.enums.NotificationType;
import com.tpcmswebadmin.service.notification.service.NotificationClientCreateService;
import com.tpcmswebadmin.service.sos.service.SosClientCreateService;
import com.tpcmswebadmin.webpages.notification.model.NotificationCreateModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class NotificationNewDelegate {

    private final NotificationClientCreateService notificationClientCreateService;

    private final SosClientCreateService sosClientCreateService;

    public void createNotification(NotificationCreateModel notificationCreateModel, HttpServletRequest httpServletRequest) {
        LoginUserDo loginUserDo = makeLoginUser(httpServletRequest);

        if(NotificationType.NOTIFICATION.name().equals(notificationCreateModel.getNotificationType()))
            notificationClientCreateService.create(notificationCreateModel, loginUserDo);
        else
            sosClientCreateService.create(notificationCreateModel, loginUserDo);
    }

    public List<String> getNotificationTypes() {
        return Arrays.asList("Notification", "Sos");
    }

    private LoginUserDo makeLoginUser(HttpServletRequest httpServletRequest) {
        return LoginUserDo.builder()
                .loginOfficersCode((String) httpServletRequest.getSession().getAttribute(TpCmsConstants.OFFICER_CODE))
                .loginOfficerUnitNumber((String) httpServletRequest.getSession().getAttribute(TpCmsConstants.REPORT_UNIT))
                .mobileAppDeviceId((String) httpServletRequest.getSession().getAttribute(TpCmsConstants.MOBILE_APP_DEVICE_ID))
                .build();
    }
}
