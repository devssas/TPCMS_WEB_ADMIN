package com.tpcmswebadmin.webpages.notification.delegate;

import com.ssas.tpcms.engine.vo.response.TPEngineResponse;
import com.tpcmswebadmin.infrastructure.domain.LoginUserDo;
import com.tpcmswebadmin.infrastructure.domain.constant.TpCmsConstants;
import com.tpcmswebadmin.infrastructure.domain.dto.ResponseMVCDto;
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

    public ResponseMVCDto createNotification(NotificationCreateModel notificationCreateModel, HttpServletRequest httpServletRequest) {
        LoginUserDo loginUserDo = LoginUserDo.makeLoginUser(httpServletRequest);
        TPEngineResponse response;

        if(notificationCreateModel.getNotificationType().equals("Notification"))
            response = notificationClientCreateService.create(notificationCreateModel, loginUserDo);
        else
            response = sosClientCreateService.create(notificationCreateModel, loginUserDo);

        return ResponseMVCDto.prepareResponse(response);
    }

    public List<String> getNotificationTypes() {
        return Arrays.asList("Notification", "Sos");
    }

}
