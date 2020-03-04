package com.tpcmswebadmin.service.sos.service;

import com.ssas.tpcms.engine.vo.request.GeneralAnnouncementRequestVO;
import com.ssas.tpcms.engine.vo.request.SOSRequestVO;
import com.ssas.tpcms.engine.vo.response.TPEngineResponse;
import com.tpcmswebadmin.infrastructure.client.TPCMSClient;
import com.tpcmswebadmin.infrastructure.domain.LoginUserDo;
import com.tpcmswebadmin.infrastructure.service.ClientCreateServiceAPI;
import com.tpcmswebadmin.service.credentials.CredentialsService;
import com.tpcmswebadmin.service.credentials.domain.TpCmsWebAdminAppCredentials;
import com.tpcmswebadmin.service.missionpermits.exception.MissionPermitsException;
import com.tpcmswebadmin.webpages.notification.model.NotificationCreateModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;

@Slf4j
@Service
@RequiredArgsConstructor
public class SosClientCreateService implements ClientCreateServiceAPI<NotificationCreateModel, SOSRequestVO> {

    private final TPCMSClient tpcmsClient;

    private final CredentialsService credentialsService;

    @Override
    public TPEngineResponse create(NotificationCreateModel model, LoginUserDo loginUserDo) {
        SOSRequestVO sosRequestVO = new SOSRequestVO();
        setFields(model, sosRequestVO);
        setCredentials(sosRequestVO, loginUserDo);

        try {
            return tpcmsClient.tpcmsWebAdminClient().getTPCMSCoreServices().createSOSRequest(sosRequestVO);
        } catch (RemoteException | ServiceException e) {
            throw new MissionPermitsException("Something wrong on creating notification request. " + sosRequestVO.getMobileAppUserName());
        }
    }

    @Override
    public void setFields(NotificationCreateModel model, SOSRequestVO requestVO) {
        requestVO.setStatusCode(model.getStatement());
    }

    @Override
    public void setCredentials(SOSRequestVO requestVO, LoginUserDo loginUserDo) {
        TpCmsWebAdminAppCredentials credentials = credentialsService.getCredentialsOfWebAdmin();

        requestVO.setMobileAppUserName(credentials.getMobileAppUserName());
        requestVO.setMobileAppPassword(credentials.getMobileAppPassword());
        requestVO.setMobileAppSmartSecurityKey(credentials.getMobileAppSmartSecurityKey());
        requestVO.setMobileAppDeviceId(loginUserDo.getMobileAppDeviceId());
    }
}
