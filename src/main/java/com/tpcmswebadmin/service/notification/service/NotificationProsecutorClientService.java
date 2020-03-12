package com.tpcmswebadmin.service.notification.service;

import com.ssas.tpcms.engine.vo.request.ViewNotificationsRequestVO;
import com.ssas.tpcms.engine.vo.response.TPEngineResponse;
import com.tpcmswebadmin.infrastructure.client.TPCMSClient;
import com.tpcmswebadmin.infrastructure.client.response.DataDto;
import com.tpcmswebadmin.infrastructure.client.response.ResponseAPIDto;
import com.tpcmswebadmin.infrastructure.domain.LoginUserDo;
import com.tpcmswebadmin.infrastructure.domain.constant.TpCmsConstants;
import com.tpcmswebadmin.infrastructure.service.ClientServiceAPI;
import com.tpcmswebadmin.service.credentials.CredentialsService;
import com.tpcmswebadmin.service.credentials.domain.TpCmsWebAdminAppCredentials;
import com.tpcmswebadmin.service.notification.domain.NotificationDto;
import com.tpcmswebadmin.service.notification.service.mapper.NotificationProsecutorMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationProsecutorClientService implements ClientServiceAPI<NotificationDto, LoginUserDo, ViewNotificationsRequestVO>  {

    private final TPCMSClient tpcmsClient;

    private final CredentialsService credentialsService;

    @Override
    public ResponseAPIDto<NotificationDto> getResponseDto(HttpServletRequest request) {
        LoginUserDo loginUserDo = LoginUserDo.builder()
                .accessRole((String) request.getSession().getAttribute(TpCmsConstants.ACCESS_ROLE))
                .loginOfficersCode((String) request.getSession().getAttribute(TpCmsConstants.OFFICER_CODE))
                .loginOfficerUnitNumber((String) request.getSession().getAttribute(TpCmsConstants.REPORT_UNIT))
                .mobileAppDeviceId((String) request.getSession().getAttribute(TpCmsConstants.MOBILE_APP_DEVICE_ID))
                .build();

        TPEngineResponse response = makeClientCall(loginUserDo);

        if (response.getGeneralAnnouncementList() == null)
            return prepareResponseDto(Collections.emptyList(), true, response);
        else
            return prepareResponseDto(NotificationProsecutorMapper.makeNotificationDtoList(response.getGeneralAnnouncementList()), true, response);
    }

    @Override
    public ResponseAPIDto<NotificationDto> prepareResponseDto(List<NotificationDto> list, boolean status, TPEngineResponse response) {
        ResponseAPIDto<NotificationDto> responseAPIDto = new ResponseAPIDto<>();
        DataDto<NotificationDto> dataDto = new DataDto<>();

        dataDto.setTbody(list);
        dataDto.setThead(setTableColumnNames());

        responseAPIDto.setData(dataDto);
        responseAPIDto.setMessage("status");
        responseAPIDto.setStatus("true");

        return responseAPIDto;
    }

    @Override
    public TPEngineResponse makeClientCall(LoginUserDo loginUserDo) {
        ViewNotificationsRequestVO viewNotificationsRequestVO = new ViewNotificationsRequestVO();
        viewNotificationsRequestVO.setLoginOfficersCode(loginUserDo.getLoginOfficersCode());
        viewNotificationsRequestVO.setPageNumber(String.valueOf(loginUserDo.getPageNumber()));
        viewNotificationsRequestVO.setLimit(String.valueOf(loginUserDo.getLimit()));
        viewNotificationsRequestVO.setNotificationSeeAll("Y");
        viewNotificationsRequestVO.setAccessRoleCode(loginUserDo.getAccessRole());

        viewNotificationsRequestVO.setMobileAppDeviceId(loginUserDo.getMobileAppDeviceId());
        setCredentials(viewNotificationsRequestVO);

        try {
            log.info("Get notification list request will be sent to client. {}", viewNotificationsRequestVO.getMobileAppUserName());

            return tpcmsClient.tpcmsWebAdminClient().getTPCMSCoreServices().getNotifications(viewNotificationsRequestVO);
        } catch (RemoteException | ServiceException e) {
            log.warn("Something wrong on get notification list request. " + viewNotificationsRequestVO.getMobileAppUserName());
        }
        return null;
    }

    @Override
    public void setCredentials(ViewNotificationsRequestVO requestVO) {
        TpCmsWebAdminAppCredentials credentials = credentialsService.getCredentialsOfWebAdmin();

        requestVO.setMobileAppUserName(credentials.getMobileAppUserName());
        requestVO.setMobileAppPassword(credentials.getMobileAppPassword());
        requestVO.setMobileAppSmartSecurityKey(credentials.getMobileAppSmartSecurityKey());
    }

    @Override
    public List<String> setTableColumnNames() {
        List<String> list = new ArrayList<>();

        list.add("Notification Code");
        list.add("Crime Name");
        list.add("Notification Date");
        list.add("Nature of Announcement");
        list.add("Actions");

        return list;
    }
}
