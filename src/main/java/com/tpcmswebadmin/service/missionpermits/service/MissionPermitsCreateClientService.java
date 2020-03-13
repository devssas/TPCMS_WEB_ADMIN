package com.tpcmswebadmin.service.missionpermits.service;

import com.ssas.tpcms.engine.vo.request.SpecialMissionRequestVO;
import com.ssas.tpcms.engine.vo.response.TPEngineResponse;
import com.tpcmswebadmin.infrastructure.client.TPCMSClient;
import com.tpcmswebadmin.infrastructure.domain.LoginUserDo;
import com.tpcmswebadmin.infrastructure.service.ClientCreateServiceAPI;
import com.tpcmswebadmin.service.credentials.CredentialsService;
import com.tpcmswebadmin.service.credentials.domain.TpCmsWebAdminAppCredentials;
import com.tpcmswebadmin.service.missionpermits.exception.MissionPermitsException;
import com.tpcmswebadmin.webpages.missionpermits.model.MissionPermitCardCreateModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;

@Slf4j
@Service
@RequiredArgsConstructor
public class MissionPermitsCreateClientService implements ClientCreateServiceAPI<MissionPermitCardCreateModel, SpecialMissionRequestVO> {

    private final TPCMSClient tpcmsClient;

    private final CredentialsService credentialsService;

    @Override
    public TPEngineResponse create(MissionPermitCardCreateModel model, LoginUserDo loginUserDo) {
        SpecialMissionRequestVO specialMissionRequestVO = new SpecialMissionRequestVO();
        setFields(model, specialMissionRequestVO);
        setCredentials(specialMissionRequestVO, loginUserDo);

        try {
            return tpcmsClient.tpcmsWebAdminClient().getTPCMSCoreServices().createSpecialMission(specialMissionRequestVO);
        } catch (RemoteException | ServiceException e) {
            throw new MissionPermitsException("Something wrong on creating Special Mission request. " + specialMissionRequestVO.getMobileAppUserName());
        }
    }

    @Override
    public void setFields(MissionPermitCardCreateModel model, SpecialMissionRequestVO requestVO) {
        requestVO.setOfficersProfileId(model.getOfficerId());
        requestVO.setPermissionToCarryWeapon(model.isPermittedToCarryWeapon() ? "Y" : "N");
        requestVO.setAllowedWeaponType(model.getWeaponType());
        requestVO.setMissionType(model.getMissionType());
        requestVO.setMissionDescription(model.getMissionDescription());
        requestVO.setActivationDate(model.getActivationDate());
        requestVO.setExpiryDate(model.getExpiryDate());
        requestVO.setAdditionalRemarks(model.getAdditionalRemarks());
    }

    @Override
    public void setCredentials(SpecialMissionRequestVO requestVO, LoginUserDo loginUserDo) {
        TpCmsWebAdminAppCredentials credentials = credentialsService.getCredentialsOfWebAdmin();

        requestVO.setLoginOfficersCode(loginUserDo.getLoginOfficersCode());
        requestVO.setMobileAppUserName(credentials.getMobileAppUserName());
        requestVO.setMobileAppPassword(credentials.getMobileAppPassword());
        requestVO.setMobileAppSmartSecurityKey(credentials.getMobileAppSmartSecurityKey());
        requestVO.setMobileAppDeviceId(loginUserDo.getMobileAppDeviceId());
    }

}
