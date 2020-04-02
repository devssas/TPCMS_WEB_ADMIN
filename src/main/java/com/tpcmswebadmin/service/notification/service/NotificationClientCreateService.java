package com.tpcmswebadmin.service.notification.service;

import com.ssas.tpcms.engine.vo.request.GeneralAnnouncementRequestVO;
import com.ssas.tpcms.engine.vo.request.OfficersProfileRequestVO;
import com.ssas.tpcms.engine.vo.request.SpecialMissionRequestVO;
import com.ssas.tpcms.engine.vo.request.VehicleDetailsRequestVO;
import com.ssas.tpcms.engine.vo.response.TPEngineResponse;
import com.tpcmswebadmin.infrastructure.client.TPCMSClient;
import com.tpcmswebadmin.infrastructure.domain.LoginUserDo;
import com.tpcmswebadmin.infrastructure.service.ClientCreateServiceAPI;
import com.tpcmswebadmin.infrastructure.service.ClientServiceAPI;
import com.tpcmswebadmin.service.credentials.CredentialsService;
import com.tpcmswebadmin.service.credentials.domain.TpCmsWebAdminAppCredentials;
import com.tpcmswebadmin.service.images.domain.ImageDto;
import com.tpcmswebadmin.service.images.service.ImageService;
import com.tpcmswebadmin.service.missionpermits.exception.MissionPermitsException;
import com.tpcmswebadmin.webpages.notification.model.NotificationCreateModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;

import javax.xml.rpc.ServiceException;
import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationClientCreateService implements ClientCreateServiceAPI<NotificationCreateModel, GeneralAnnouncementRequestVO> {

    private final TPCMSClient tpcmsClient;

    private final CredentialsService credentialsService;

    private final ImageService imageService;

    @Override
    public TPEngineResponse create(NotificationCreateModel model, LoginUserDo loginUserDo) {
        GeneralAnnouncementRequestVO generalAnnouncementRequestVO = new GeneralAnnouncementRequestVO();
        setFields(model, generalAnnouncementRequestVO);
        setCredentials(generalAnnouncementRequestVO, loginUserDo);

        String pageName = model.getCurrentPageName();
        List<ImageDto> images = getImages(loginUserDo, pageName);
        setImages(generalAnnouncementRequestVO, images);

        try {
            TPEngineResponse response = tpcmsClient.tpcmsWebAdminClient().getTPCMSCoreServices().createAnnouncements(generalAnnouncementRequestVO);
            deleteImages(images, pageName, loginUserDo);

            return response;
        } catch (RemoteException | ServiceException e) {
            throw new MissionPermitsException("Something wrong on creating notification request. " + e.getMessage());
        }
    }

    private void deleteImages(List<ImageDto> images, String pageName, LoginUserDo loginUserDo) {
        if(!images.isEmpty()) {
            if(images.size() == 1) {
                imageService.delete(images.get(0).getFileName(), pageName, loginUserDo);
            }
            if(images.size() == 2) {
                imageService.delete(images.get(0).getFileName(), pageName, loginUserDo);
                imageService.delete(images.get(1).getFileName(), pageName, loginUserDo);
            }
            if(images.size() == 3) {
                imageService.delete(images.get(0).getFileName(), pageName, loginUserDo);
                imageService.delete(images.get(1).getFileName(), pageName, loginUserDo);
                imageService.delete(images.get(2).getFileName(), pageName, loginUserDo);
            }
            if(images.size() == 4) {
                imageService.delete(images.get(0).getFileName(), pageName, loginUserDo);
                imageService.delete(images.get(1).getFileName(), pageName, loginUserDo);
                imageService.delete(images.get(2).getFileName(), pageName, loginUserDo);
                imageService.delete(images.get(3).getFileName(), pageName, loginUserDo);
            }
            if(images.size() == 5) {
                imageService.delete(images.get(0).getFileName(), pageName, loginUserDo);
                imageService.delete(images.get(1).getFileName(), pageName, loginUserDo);
                imageService.delete(images.get(2).getFileName(), pageName, loginUserDo);
                imageService.delete(images.get(3).getFileName(), pageName, loginUserDo);
                imageService.delete(images.get(4).getFileName(), pageName, loginUserDo);
            }
        }
    }

    private List<ImageDto> getImages(LoginUserDo loginUserDo, String pageName) {
        String key = imageService.makeKey(loginUserDo);
        return imageService.getByKeyAndPage(key, pageName);
    }

    private void setImages(GeneralAnnouncementRequestVO generalAnnouncementRequestVO, List<ImageDto> images) {
        if(!images.isEmpty()) {
            if(images.size() == 1) {
                generalAnnouncementRequestVO.setAttachment1(convertFileContentToBlob(images.get(0).getFileBase64()));
            }
            if(images.size() == 2) {
                generalAnnouncementRequestVO.setAttachment1(convertFileContentToBlob(images.get(0).getFileBase64()));
                generalAnnouncementRequestVO.setAttachment2(convertFileContentToBlob(images.get(1).getFileBase64()));
            }
            if(images.size() == 3) {
                generalAnnouncementRequestVO.setAttachment1(convertFileContentToBlob(images.get(0).getFileBase64()));
                generalAnnouncementRequestVO.setAttachment2(convertFileContentToBlob(images.get(1).getFileBase64()));
                generalAnnouncementRequestVO.setAttachment3(convertFileContentToBlob(images.get(2).getFileBase64()));
            }
            if(images.size() == 4) {
                generalAnnouncementRequestVO.setAttachment1(convertFileContentToBlob(images.get(0).getFileBase64()));
                generalAnnouncementRequestVO.setAttachment2(convertFileContentToBlob(images.get(1).getFileBase64()));
                generalAnnouncementRequestVO.setAttachment3(convertFileContentToBlob(images.get(2).getFileBase64()));
                generalAnnouncementRequestVO.setAttachment4(convertFileContentToBlob(images.get(3).getFileBase64()));
            }
            if(images.size() == 5) {
                generalAnnouncementRequestVO.setAttachment1(convertFileContentToBlob(images.get(0).getFileBase64()));
                generalAnnouncementRequestVO.setAttachment2(convertFileContentToBlob(images.get(1).getFileBase64()));
                generalAnnouncementRequestVO.setAttachment3(convertFileContentToBlob(images.get(2).getFileBase64()));
                generalAnnouncementRequestVO.setAttachment4(convertFileContentToBlob(images.get(3).getFileBase64()));
                generalAnnouncementRequestVO.setAttachment5(convertFileContentToBlob(images.get(4).getFileBase64()));
            }
        }
    }

    public static byte[] convertFileContentToBlob(String filePath) {
        try {
            return Base64.decodeBase64(filePath.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new MissionPermitsException("Something wrong on creating Police Officer request. " + e.getMessage());
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

        requestVO.setLoginOfficersCode(loginUserDo.getLoginOfficersCode());
        requestVO.setMobileAppUserName(credentials.getMobileAppUserName());
        requestVO.setMobileAppPassword(credentials.getMobileAppPassword());
        requestVO.setMobileAppSmartSecurityKey(credentials.getMobileAppSmartSecurityKey());
        requestVO.setMobileAppDeviceId(loginUserDo.getMobileAppDeviceId());
    }
}
