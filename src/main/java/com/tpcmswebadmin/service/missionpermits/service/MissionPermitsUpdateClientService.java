package com.tpcmswebadmin.service.missionpermits.service;

import com.ssas.tpcms.engine.vo.request.SpecialMissionRequestVO;
import com.ssas.tpcms.engine.vo.response.TPEngineResponse;
import com.tpcmswebadmin.infrastructure.client.TPCMSClient;
import com.tpcmswebadmin.infrastructure.domain.LoginUserDo;
import com.tpcmswebadmin.service.credentials.CredentialsService;
import com.tpcmswebadmin.service.credentials.domain.TpCmsWebAdminAppCredentials;
import com.tpcmswebadmin.service.missionpermits.exception.MissionPermitsException;
import com.tpcmswebadmin.webpages.missionpermits.model.MissionPermitCardCreateModel;
import com.tpcmswebadmin.webpages.missionpermits.model.MissionPermitCardUpdateModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;

@Slf4j
@Service
@RequiredArgsConstructor
public class MissionPermitsUpdateClientService {

    private final TPCMSClient tpcmsClient;

    private final CredentialsService credentialsService;

    public TPEngineResponse update(MissionPermitCardUpdateModel missionPermitCardUpdateModel, LoginUserDo loginUserDo) {
        SpecialMissionRequestVO specialMissionRequestVO = new SpecialMissionRequestVO();
        setFields(missionPermitCardUpdateModel, specialMissionRequestVO);
        setCredentials(specialMissionRequestVO, loginUserDo);

        log.info("Special Mission to be updated. {}", missionPermitCardUpdateModel.getOfficerId());
        try {
            return tpcmsClient.tpcmsWebAdminClient().getTPCMSCoreServices().updateSpecialMission(specialMissionRequestVO);
        } catch (RemoteException | ServiceException e) {
            throw new MissionPermitsException("Something wrong on updating Special Mission request. " + e.getMessage());
        }
    }

    public void setFields(MissionPermitCardUpdateModel model, SpecialMissionRequestVO requestVO) {
        requestVO.setSpMissionId(model.getMissionId());
        requestVO.setSpecialMissionQRCode(model.getMissionQrCode());
        requestVO.setOfficerCode(model.getOfficerId());
        requestVO.setOfficersProfileId(model.getOfficerId());
        requestVO.setPermissionToCarryWeapon(model.isPermittedToCarryWeapon() ? "Y" : "N");
        requestVO.setAllowedWeaponType(model.getWeaponType());
        requestVO.setMissionType(model.getMissionType());
        requestVO.setMissionDescription(model.getMissionDescription());
        requestVO.setActivationDate(model.getActivationDate());
        requestVO.setExpiryDate(model.getExpiryDate());
        requestVO.setAdditionalRemarks(model.getAdditionalRemarks());
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
