package com.tpcmswebadmin.service.dashboard;

import com.ssas.tpcms.engine.vo.request.AdminDashBoardRequestVO;
import com.ssas.tpcms.engine.vo.request.ProsecutionDashBoardRequestVO;
import com.ssas.tpcms.engine.vo.request.PushNotificationsRequestVO;
import com.ssas.tpcms.engine.vo.response.TPEngineResponse;
import com.tpcmswebadmin.infrastructure.client.TPCMSClient;
import com.tpcmswebadmin.infrastructure.domain.constant.TpCmsConstants;
import com.tpcmswebadmin.service.credentials.CredentialsService;
import com.tpcmswebadmin.service.credentials.domain.TpCmsWebAdminAppCredentials;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;

@Slf4j
@Service
public class DashboardProsecutorService {

    private final TPCMSClient tpcmsClient;

    private final CredentialsService credentialsService;

    public DashboardProsecutorService(TPCMSClient tpcmsClient, CredentialsService credentialsService) {
        this.tpcmsClient = tpcmsClient;
        this.credentialsService = credentialsService;
    }

    public TPEngineResponse getProsecutorDashboard(HttpServletRequest httpServletRequest) {
        ProsecutionDashBoardRequestVO prosecutionDashBoardRequestVO = new ProsecutionDashBoardRequestVO();
        prosecutionDashBoardRequestVO.setLoginOfficersCode(((String) httpServletRequest.getSession().getAttribute(TpCmsConstants.OFFICER_CODE)));
        prosecutionDashBoardRequestVO.setReportingUnit(((String) httpServletRequest.getSession().getAttribute(TpCmsConstants.REPORT_UNIT)));
        prosecutionDashBoardRequestVO.setMobileAppDeviceId(((String) httpServletRequest.getSession().getAttribute(TpCmsConstants.MOBILE_APP_DEVICE_ID)));

        setCredentials(prosecutionDashBoardRequestVO);

        try {
            return tpcmsClient.tpcmsWebAdminClient().getTPCMSCoreServices().prosecutionOfficerDashboardRequest(prosecutionDashBoardRequestVO);
        } catch (RemoteException | ServiceException e) {
            log.warn("Something wrong on fetching prosecutor dashboard. {}", prosecutionDashBoardRequestVO.getMobileAppUserName());
        }

        return null;
    }

    public TPEngineResponse getProsecutorDashboardNotifications(HttpServletRequest httpServletRequest) {
        PushNotificationsRequestVO pushNotificationsRequestVO = new PushNotificationsRequestVO();
        pushNotificationsRequestVO.setLoginOfficersCode(((String) httpServletRequest.getSession().getAttribute(TpCmsConstants.OFFICER_CODE)));
        pushNotificationsRequestVO.setOfficerCode(((String) httpServletRequest.getSession().getAttribute(TpCmsConstants.OFFICER_CODE)));
        pushNotificationsRequestVO.setLoginOffierReportingUnit(((String) httpServletRequest.getSession().getAttribute(TpCmsConstants.REPORT_UNIT)));
        pushNotificationsRequestVO.setMobileAppDeviceId(((String) httpServletRequest.getSession().getAttribute(TpCmsConstants.MOBILE_APP_DEVICE_ID)));

        setCredentials(pushNotificationsRequestVO);

        try {
            return tpcmsClient.tpcmsWebAdminClient().getTPCMSCoreServices().getPushNotifications(pushNotificationsRequestVO);
        } catch (RemoteException | ServiceException e) {
            log.warn("Something wrong on fetching dashboard notifications request. {}", pushNotificationsRequestVO.getMobileAppUserName());
        }

        return null;
    }

    private void setCredentials(ProsecutionDashBoardRequestVO prosecutionDashBoardRequestVO) {
        TpCmsWebAdminAppCredentials credentials = credentialsService.getCredentialsOfWebAdmin();

        prosecutionDashBoardRequestVO.setMobileAppUserName(credentials.getMobileAppUserName());
        prosecutionDashBoardRequestVO.setMobileAppPassword(credentials.getMobileAppPassword());
        prosecutionDashBoardRequestVO.setMobileAppSmartSecurityKey(credentials.getMobileAppSmartSecurityKey());
    }

    private void setCredentials(PushNotificationsRequestVO pushNotificationsRequestVO) {
        TpCmsWebAdminAppCredentials credentials = credentialsService.getCredentialsOfWebAdmin();

        pushNotificationsRequestVO.setMobileAppUserName(credentials.getMobileAppUserName());
        pushNotificationsRequestVO.setMobileAppPassword(credentials.getMobileAppPassword());
        pushNotificationsRequestVO.setMobileAppSmartSecurityKey(credentials.getMobileAppSmartSecurityKey());
    }

}
