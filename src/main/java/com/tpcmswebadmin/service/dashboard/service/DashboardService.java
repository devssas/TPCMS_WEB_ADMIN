package com.tpcmswebadmin.service.dashboard.service;

import com.ssas.tpcms.engine.vo.request.AdminDashBoardRequestVO;
import com.ssas.tpcms.engine.vo.request.PushNotificationsRequestVO;
import com.ssas.tpcms.engine.vo.response.TPEngineResponse;
import com.tpcmswebadmin.infrastructure.client.TPCMSClient;
import com.tpcmswebadmin.infrastructure.domain.constant.TpCmsConstants;
import com.tpcmswebadmin.service.credentials.CredentialsService;
import com.tpcmswebadmin.service.credentials.domain.TpCmsWebAdminAppCredentials;
import com.tpcmswebadmin.service.dashboard.domain.DashboardDto;
import com.tpcmswebadmin.service.dashboard.domain.DefaultDto;
import com.tpcmswebadmin.service.dashboard.domain.DefaultWithNewDto;
import com.tpcmswebadmin.service.dashboard.domain.MapCenter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.xml.rpc.ServiceException;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.Collections;

@Slf4j
@Service
public class DashboardService {

    private final TPCMSClient tpcmsClient;

    private final CredentialsService credentialsService;

    public DashboardService(TPCMSClient tpcmsClient, CredentialsService credentialsService) {
        this.tpcmsClient = tpcmsClient;
        this.credentialsService = credentialsService;
    }

    public DashboardDto getAdminDashboardAPI(HttpServletRequest httpServletRequest) {
        TPEngineResponse response = getAdminDashboard(httpServletRequest);

        return DashboardDto.builder()
                .mapCenter(new MapCenter())
                .data(Collections.emptyList())
                .notifications(DefaultWithNewDto.builder()
                                       .defaultName("dejneme")
                                       .newName("xsxsxsxs")
                                       .build())
                .permits(DefaultDto.builder().defaultName("dejvf").build())
                .cases(DefaultDto.builder().defaultName("dejvf").build())
                .sos(DefaultDto.builder().defaultName("dejvf").build())
                .sosDetail(DefaultWithNewDto.builder()
                                   .defaultName("dejneme")
                                   .newName("xsxsxsxs")
                                   .build())
                .build();
    }

    public TPEngineResponse getAdminDashboard(HttpServletRequest httpServletRequest) {
        AdminDashBoardRequestVO adminDashBoardRequestVO = new AdminDashBoardRequestVO();
        adminDashBoardRequestVO.setLoginOfficersCode(((String) httpServletRequest.getSession().getAttribute(TpCmsConstants.OFFICER_CODE)));
        adminDashBoardRequestVO.setReportingUnit(((String) httpServletRequest.getSession().getAttribute(TpCmsConstants.REPORT_UNIT)));
        adminDashBoardRequestVO.setMobileAppDeviceId(((String) httpServletRequest.getSession().getAttribute(TpCmsConstants.MOBILE_APP_DEVICE_ID)));

        setCredentials(adminDashBoardRequestVO);

        try {
            return tpcmsClient.tpcmsWebAdminClient().getTPCMSCoreServices().adminDashboardRequest(
                    adminDashBoardRequestVO);
        } catch (RemoteException | ServiceException e) {
            log.warn("Something wrong on fetching dashboard. {}", adminDashBoardRequestVO.getMobileAppUserName());
        }

        return null;
    }

    public TPEngineResponse getAdminDashboardNotifications(HttpServletRequest httpServletRequest) {
        PushNotificationsRequestVO pushNotificationsRequestVO = new PushNotificationsRequestVO();
        pushNotificationsRequestVO.setLoginOfficersCode(
                ((String) httpServletRequest.getSession().getAttribute(TpCmsConstants.OFFICER_CODE)));
        pushNotificationsRequestVO.setOfficerCode(
                ((String) httpServletRequest.getSession().getAttribute(TpCmsConstants.OFFICER_CODE)));
        pushNotificationsRequestVO.setLoginOffierReportingUnit(
                ((String) httpServletRequest.getSession().getAttribute(TpCmsConstants.REPORT_UNIT)));
        pushNotificationsRequestVO.setMobileAppDeviceId(
                ((String) httpServletRequest.getSession().getAttribute(TpCmsConstants.MOBILE_APP_DEVICE_ID)));

        setCredentials(pushNotificationsRequestVO);

        try {
            return tpcmsClient.tpcmsWebAdminClient().getTPCMSCoreServices().getPushNotifications(
                    pushNotificationsRequestVO);
        } catch (RemoteException | ServiceException e) {
            log.warn("Something wrong on fetching dashboard notifications request. {}",
                     pushNotificationsRequestVO.getMobileAppUserName());
        }

        return null;
    }

    private void setCredentials(AdminDashBoardRequestVO adminDashBoardRequestVO) {
        TpCmsWebAdminAppCredentials credentials = credentialsService.getCredentialsOfWebAdmin();

        adminDashBoardRequestVO.setMobileAppUserName(credentials.getMobileAppUserName());
        adminDashBoardRequestVO.setMobileAppPassword(credentials.getMobileAppPassword());
        adminDashBoardRequestVO.setMobileAppSmartSecurityKey(credentials.getMobileAppSmartSecurityKey());
    }

    private void setCredentials(PushNotificationsRequestVO pushNotificationsRequestVO) {
        TpCmsWebAdminAppCredentials credentials = credentialsService.getCredentialsOfWebAdmin();

        pushNotificationsRequestVO.setMobileAppUserName(credentials.getMobileAppUserName());
        pushNotificationsRequestVO.setMobileAppPassword(credentials.getMobileAppPassword());
        pushNotificationsRequestVO.setMobileAppSmartSecurityKey(credentials.getMobileAppSmartSecurityKey());
    }


}
