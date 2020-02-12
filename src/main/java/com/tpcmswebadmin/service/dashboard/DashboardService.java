package com.tpcmswebadmin.service.dashboard;

import com.ssas.tpcms.engine.vo.request.AdminDashBoardRequestVO;
import com.ssas.tpcms.engine.vo.request.OfficersLoginRequestVO;
import com.ssas.tpcms.engine.vo.response.TPEngineResponse;
import com.tpcmswebadmin.infrastructure.client.TPCMSClient;
import com.tpcmswebadmin.service.credentials.CredentialsService;
import com.tpcmswebadmin.service.credentials.domain.TpCmsWebAdminAppCredentials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;

@Service
public class DashboardService {

    private static final Logger logger = LoggerFactory.getLogger(DashboardService.class);

    private final TPCMSClient tpcmsClient;

    private final CredentialsService credentialsService;

    public DashboardService(TPCMSClient tpcmsClient, CredentialsService credentialsService) {
        this.tpcmsClient = tpcmsClient;
        this.credentialsService = credentialsService;
    }

    public TPEngineResponse getAdminDashboard() {
        AdminDashBoardRequestVO adminDashBoardRequestVO = new AdminDashBoardRequestVO();
        setCredentials(adminDashBoardRequestVO);

        try {
            tpcmsClient.tpcmsWebAdminClient().getTPCMSCoreServices().adminDashboardRequest(adminDashBoardRequestVO);
        } catch (RemoteException | ServiceException e) {
            logger.warn("Something wrong on signIn username request. {}", adminDashBoardRequestVO.getMobileAppUserName());
        }

        return null;
    }

    private void setCredentials(AdminDashBoardRequestVO adminDashBoardRequestVO) {
        TpCmsWebAdminAppCredentials credentials = credentialsService.getCredentialsOfWebAdmin();

        adminDashBoardRequestVO.setMobileAppUserName(credentials.getMobileAppUserName());
        adminDashBoardRequestVO.setMobileAppDeviceId("e369f536f443a91"); //todo static
        adminDashBoardRequestVO.setMobileAppPassword(credentials.getMobileAppPassword());
        adminDashBoardRequestVO.setMobileAppSmartSecurityKey(credentials.getMobileAppSmartSecurityKey());
    }
}
