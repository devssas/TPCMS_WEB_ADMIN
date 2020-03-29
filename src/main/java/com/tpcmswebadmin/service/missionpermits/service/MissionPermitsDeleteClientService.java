package com.tpcmswebadmin.service.missionpermits.service;

import com.ssas.tpcms.engine.vo.request.SpecialMissionRequestVO;
import com.ssas.tpcms.engine.vo.response.TPEngineResponse;
import com.tpcmswebadmin.infrastructure.client.TPCMSClient;
import com.tpcmswebadmin.infrastructure.domain.LoginUserDo;
import com.tpcmswebadmin.service.credentials.CredentialsService;
import com.tpcmswebadmin.service.credentials.domain.TpCmsWebAdminAppCredentials;
import com.tpcmswebadmin.service.missionpermits.domain.MissionCardDto;
import com.tpcmswebadmin.service.missionpermits.exception.MissionPermitsException;
import com.tpcmswebadmin.webpages.missionpermits.model.MissionPermitCardUpdateModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;

@Slf4j
@Service
@RequiredArgsConstructor
public class MissionPermitsDeleteClientService {

    private final TPCMSClient tpcmsClient;

    private final CredentialsService credentialsService;

    private final MissionPermitsClientService missionPermitsClientService;

    public TPEngineResponse delete(String missionId, String missionQrCode, String officerId, LoginUserDo loginUserDo) {
        SpecialMissionRequestVO specialMissionRequestVO = new SpecialMissionRequestVO();
        setCredentials(specialMissionRequestVO, loginUserDo);

        MissionCardDto missionCardDto = missionPermitsClientService.getSpecialMissionsByMissionId(missionId, missionQrCode, loginUserDo);
        specialMissionRequestVO.setSpMissionId(missionId);
        specialMissionRequestVO.setOfficersProfileId(officerId);
        specialMissionRequestVO.setSpecialMissionQRCode(missionQrCode);
        specialMissionRequestVO.setIsDeleteSpecialMission("Y");
        specialMissionRequestVO.setPermissionToCarryWeapon(missionCardDto.getIsPermittedCarryWeapon());
        specialMissionRequestVO.setMissionType(missionCardDto.getMissionType());
        specialMissionRequestVO.setMissionDescription(missionCardDto.getMissionDescription());

        log.info("Special Mission to be deleted. {}", missionId);
        try {
            return tpcmsClient.tpcmsWebAdminClient().getTPCMSCoreServices().updateSpecialMission(specialMissionRequestVO);
        } catch (RemoteException | ServiceException e) {
            throw new MissionPermitsException("Something wrong on updating Special Mission request. " + e.getMessage());
        }
    }

    public void setCredentials(SpecialMissionRequestVO requestVO, LoginUserDo loginUserDo) {
        TpCmsWebAdminAppCredentials credentials = credentialsService.getCredentialsOfWebAdmin();

        requestVO.setUnitNumber(loginUserDo.getLoginOfficerUnitNumber());
        requestVO.setLoginOfficersCode(loginUserDo.getLoginOfficersCode());
        requestVO.setMobileAppUserName(credentials.getMobileAppUserName());
        requestVO.setMobileAppPassword(credentials.getMobileAppPassword());
        requestVO.setMobileAppSmartSecurityKey(credentials.getMobileAppSmartSecurityKey());
        requestVO.setMobileAppDeviceId(loginUserDo.getMobileAppDeviceId());
    }
}
