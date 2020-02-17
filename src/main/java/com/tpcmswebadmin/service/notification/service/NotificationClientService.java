package com.tpcmswebadmin.service.notification.service;

import com.ssas.tpcms.engine.vo.request.ViewNotificationsRequestVO;
import com.ssas.tpcms.engine.vo.response.TPEngineResponse;
import com.tpcmswebadmin.infrastructure.client.TPCMSClient;
import com.tpcmswebadmin.infrastructure.client.response.DataDto;
import com.tpcmswebadmin.infrastructure.client.response.ResponseDto;
import com.tpcmswebadmin.infrastructure.domain.LoginUserDo;
import com.tpcmswebadmin.infrastructure.domain.constant.TpCmsConstants;
import com.tpcmswebadmin.infrastructure.service.ClientServiceAPI;
import com.tpcmswebadmin.service.credentials.CredentialsService;
import com.tpcmswebadmin.service.credentials.domain.TpCmsWebAdminAppCredentials;
import com.tpcmswebadmin.service.notification.domain.NotificationDto;
import com.tpcmswebadmin.service.notification.service.mapper.NotificationMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationClientService implements ClientServiceAPI<NotificationDto, LoginUserDo, ViewNotificationsRequestVO> {

    private final TPCMSClient tpcmsClient;

    private final CredentialsService credentialsService;

    @Override
    public ResponseDto<NotificationDto> getResponseDto(HttpServletRequest request) {
        LoginUserDo loginUserDo = LoginUserDo.builder()
                .loginOfficersCode((String) request.getSession().getAttribute(TpCmsConstants.OFFICER_CODE))
                .loginOfficerUnitNumber((String) request.getSession().getAttribute(TpCmsConstants.REPORT_UNIT))
                .build();

        TPEngineResponse response = makeClientCall(loginUserDo);

        return prepareResponseDto(NotificationMapper.makeNotificationDtoList(response.getGeneralAnnouncementList()));
    }

    @Override
    public ResponseDto<NotificationDto> prepareResponseDto(List<NotificationDto> list) {
        ResponseDto<NotificationDto> responseDto = new ResponseDto<>();
        DataDto<NotificationDto> dataDto = new DataDto<>();

        dataDto.setTbody(list);
        dataDto.setThead(setTableColumnNames());

        responseDto.setData(dataDto);
        responseDto.setMessage("status");
        responseDto.setStatus("true");

        return responseDto;
    }

    @Override
    public TPEngineResponse makeClientCall(LoginUserDo loginUserDo) {
        ViewNotificationsRequestVO viewNotificationsRequestVO = new ViewNotificationsRequestVO();
        viewNotificationsRequestVO.setLoginOfficersCode(loginUserDo.getLoginOfficersCode());
        viewNotificationsRequestVO.setPageNumber(String.valueOf(loginUserDo.getPageNumber()));
        viewNotificationsRequestVO.setLimit(String.valueOf(loginUserDo.getLimit()));
        viewNotificationsRequestVO.setNotificationSeeAll("Y");

        setCredentials(viewNotificationsRequestVO);

        try {
            log.info("Get vehicle list request will be sent to client. {}", viewNotificationsRequestVO.getMobileAppUserName());

            return tpcmsClient.tpcmsWebAdminClient().getTPCMSCoreServices().getNotifications(viewNotificationsRequestVO);
        } catch (RemoteException | ServiceException e) {
            log.warn("Something wrong on get vehicle list request. " + viewNotificationsRequestVO.getMobileAppUserName());
        }
        return null;
    }

    @Override
    public void setCredentials(ViewNotificationsRequestVO requestVO) {
        TpCmsWebAdminAppCredentials credentials = credentialsService.getCredentialsOfWebAdmin();

        requestVO.setMobileAppUserName(credentials.getMobileAppUserName());
        requestVO.setMobileAppDeviceId(TpCmsConstants.MOBILE_DEVICE_ID); //todo constant pass
        requestVO.setMobileAppPassword(credentials.getMobileAppPassword());
        requestVO.setMobileAppSmartSecurityKey(credentials.getMobileAppSmartSecurityKey());
    }

    @Override
    public String prepareActionsColumn(Integer id) {
        String actionView = "<a href='/tpcmsWebAdmin/viewNotification?notificationId={notificationId}' class='button-v1 btn-color-1'><i class='icon-eye'></i></a>";
        String actionUpdate = "<a href='/tpcmsWebAdmin/updateNotification?notificationId={notificationId}' class='button-v1 btn-color-1'><i class='icon-edit'></i></a>";

        return actionView.replace("{notificationId}", String.valueOf(id)) + actionUpdate.replace("{notificationId}", String.valueOf(id));
    }

    @Override
    public List<String> setTableColumnNames() {
        List<String> list = new ArrayList<>();

        list.add("Permit ID");
        list.add("username");
        list.add("Title");
        list.add("City");
        list.add("State");
        list.add("Notification Date");
        list.add("Priority");
        list.add("Actions");

        return list;
    }
}
