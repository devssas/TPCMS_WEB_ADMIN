package com.tpcmswebadmin.service.notification.service;

import com.ssas.tpcms.engine.vo.request.GeneralAnnouncementRequestVO;
import com.ssas.tpcms.engine.vo.request.SpecialMissionRequestVO;
import com.ssas.tpcms.engine.vo.response.TPEngineResponse;
import com.tpcmswebadmin.infrastructure.client.TPCMSClient;
import com.tpcmswebadmin.infrastructure.domain.LoginUserDo;
import com.tpcmswebadmin.infrastructure.service.ClientCreateServiceAPI;
import com.tpcmswebadmin.infrastructure.service.ClientServiceAPI;
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
public class NotificationClientCreateService implements ClientCreateServiceAPI<NotificationCreateModel, GeneralAnnouncementRequestVO> {

    private final TPCMSClient tpcmsClient;

    private final CredentialsService credentialsService;

    @Override
    public TPEngineResponse create(NotificationCreateModel model, LoginUserDo loginUserDo) {
        GeneralAnnouncementRequestVO generalAnnouncementRequestVO = new GeneralAnnouncementRequestVO();
        setFields(model, generalAnnouncementRequestVO);
        setCredentials(generalAnnouncementRequestVO, loginUserDo);

        try {
            return tpcmsClient.tpcmsWebAdminClient().getTPCMSCoreServices().createAnnouncements(generalAnnouncementRequestVO);
        } catch (RemoteException | ServiceException e) {
            throw new MissionPermitsException("Something wrong on creating notification request. " + generalAnnouncementRequestVO.getMobileAppUserName());
        }
    }

    @Override
    public void setFields(NotificationCreateModel model, GeneralAnnouncementRequestVO requestVO) {
        requestVO.setAnnouncementDesc(model.getStatement());
        requestVO.setNatureOfAnnouncement(model.getNatureOfAnnouncement());
    }

    @Override
    public void setCredentials(GeneralAnnouncementRequestVO requestVO, LoginUserDo loginUserDo) {
        TpCmsWebAdminAppCredentials credentials = credentialsService.getCredentialsOfWebAdmin();

        requestVO.setMobileAppUserName(credentials.getMobileAppUserName());
        requestVO.setMobileAppPassword(credentials.getMobileAppPassword());
        requestVO.setMobileAppSmartSecurityKey(credentials.getMobileAppSmartSecurityKey());
        requestVO.setMobileAppDeviceId(loginUserDo.getMobileAppDeviceId());
    }
}
