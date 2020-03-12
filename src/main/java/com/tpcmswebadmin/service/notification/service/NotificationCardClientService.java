package com.tpcmswebadmin.service.notification.service;

import com.ssas.tpcms.engine.vo.request.ViewNotificationsRequestVO;
import com.ssas.tpcms.engine.vo.response.TPEngineResponse;
import com.tpcmswebadmin.infrastructure.client.TPCMSClient;
import com.tpcmswebadmin.infrastructure.domain.constant.TpCmsConstants;
import com.tpcmswebadmin.infrastructure.utils.DateUtility;
import com.tpcmswebadmin.infrastructure.utils.ImageUtility;
import com.tpcmswebadmin.service.credentials.CredentialsService;
import com.tpcmswebadmin.service.credentials.domain.TpCmsWebAdminAppCredentials;
import com.tpcmswebadmin.service.notification.domain.NotificationCardDo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationCardClientService   {

    private final TPCMSClient tpcmsClient;

    private final CredentialsService credentialsService;

    public NotificationCardDo getNotificationCardById(String notificationId, HttpServletRequest request) {
        ViewNotificationsRequestVO viewNotificationsRequestVO = new ViewNotificationsRequestVO();
        viewNotificationsRequestVO.setLoginOfficersCode((String) request.getSession().getAttribute(TpCmsConstants.OFFICER_CODE));
        viewNotificationsRequestVO.setMobileAppDeviceId((String) request.getSession().getAttribute(TpCmsConstants.MOBILE_APP_DEVICE_ID));
        viewNotificationsRequestVO.setAnnouncementId(notificationId);

        setCredentials(viewNotificationsRequestVO);

        try {
            return prepareNotificationCardDo(tpcmsClient.tpcmsWebAdminClient().getTPCMSCoreServices().getNotifications(viewNotificationsRequestVO));
        } catch (RemoteException | ServiceException e) {
            log.warn("Something wrong on get notification by id request. " + notificationId);
        }
        return null;
    }

    private NotificationCardDo prepareNotificationCardDo(TPEngineResponse response) {
        return NotificationCardDo.builder()
                .announcementCode(response.getGeneralAnnouncementList()[0].getAnnouncementCode())
                .announcementDesc(response.getGeneralAnnouncementList()[0].getAnnouncementDesc())
                .announcementId(response.getGeneralAnnouncementList()[0].getAnnouncementId())
                .attachment1(ImageUtility.convertToBase64image(response.getGeneralAnnouncementList()[0].getAttachment1()))
                .crimeNameAr(response.getGeneralAnnouncementList()[0].getCrimeName_Ar())
                .crimeTypeCode(response.getGeneralAnnouncementList()[0].getCrimeTypeCode())
                .crimeTypeLogo1(ImageUtility.convertToBase64image(response.getGeneralAnnouncementList()[0].getCrimeTypeLogo1()))
                .effectiveDate(DateUtility.convertToFormat(response.getGeneralAnnouncementList()[0].getEffectiveDate(), TpCmsConstants.SCREEN_DATE_FORMAT))
                .natureOfAnnouncement(response.getGeneralAnnouncementList()[0].getNatureOfAnnouncement())
                .build();
    }

    public void setCredentials(ViewNotificationsRequestVO requestVO) {
        TpCmsWebAdminAppCredentials credentials = credentialsService.getCredentialsOfWebAdmin();

        requestVO.setMobileAppUserName(credentials.getMobileAppUserName());
        requestVO.setMobileAppPassword(credentials.getMobileAppPassword());
        requestVO.setMobileAppSmartSecurityKey(credentials.getMobileAppSmartSecurityKey());
    }

}
