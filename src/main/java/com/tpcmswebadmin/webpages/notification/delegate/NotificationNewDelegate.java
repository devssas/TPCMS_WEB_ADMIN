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

    public ResponseMVCDto createNotification(NotificationCreateModel notificationCreateModel, HttpServletRequest httpServletRequest) {
        LoginUserDo loginUserDo = makeLoginUser(httpServletRequest);
        TPEngineResponse response;

        response = notificationClientCreateService.create(notificationCreateModel, loginUserDo);

        return returnResponseMVCDto(response);
    }

    private ResponseMVCDto returnResponseMVCDto(TPEngineResponse response) {
        if (response.getResponseCodeVO().getResponseCode().startsWith("OPS")) {
            return ResponseMVCDto.builder()
                    .message(null)
                    .result(true)
                    .build();
        } else {
            return ResponseMVCDto.builder()
                    .message(response.getResponseCodeVO().getResponseValue())
                    .result(false)
                    .build();
        }
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
