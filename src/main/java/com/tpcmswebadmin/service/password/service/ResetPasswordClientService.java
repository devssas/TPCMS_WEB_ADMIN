package com.tpcmswebadmin.service.password.service;

import com.ssas.tpcms.engine.vo.request.ResetPasswordRequestVO;
import com.ssas.tpcms.engine.vo.request.SpecialMissionRequestVO;
import com.ssas.tpcms.engine.vo.response.TPEngineResponse;
import com.tpcmswebadmin.infrastructure.client.TPCMSClient;
import com.tpcmswebadmin.infrastructure.domain.LoginUserDo;
import com.tpcmswebadmin.service.credentials.CredentialsService;
import com.tpcmswebadmin.service.credentials.domain.TpCmsWebAdminAppCredentials;
import com.tpcmswebadmin.service.missionpermits.exception.MissionPermitsException;
import com.tpcmswebadmin.webpages.missionpermits.model.MissionPermitCardUpdateModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResetPasswordClientService {

    private final TPCMSClient tpcmsClient;

    private final CredentialsService credentialsService;

    public TPEngineResponse resetPassword(String officerId, String officerProfileCode, LoginUserDo loginUserDo) {
        ResetPasswordRequestVO resetPasswordRequestVO = new ResetPasswordRequestVO();
        setCredentials(resetPasswordRequestVO, loginUserDo);
        resetPasswordRequestVO.setOfficerProfilceId(officerId);
        resetPasswordRequestVO.setOfficerProfileCode(officerProfileCode);

        log.info("Reset Password to be sent. {}", officerId);
        try {
            return tpcmsClient.tpcmsWebAdminClient().getTPCMSCoreServices().resetOfficerProfilePassword(resetPasswordRequestVO);
        } catch (RemoteException | ServiceException e) {
            throw new MissionPermitsException("Something wrong on Reset Password request. " + e.getMessage());
        }
    }

    public void setCredentials(ResetPasswordRequestVO requestVO, LoginUserDo loginUserDo) {
        TpCmsWebAdminAppCredentials credentials = credentialsService.getCredentialsOfWebAdmin();

        requestVO.setLoginOfficerUnitNumber(loginUserDo.getLoginOfficerUnitNumber());
        requestVO.setLoginOfficersCode(loginUserDo.getLoginOfficersCode());
        requestVO.setMobileAppUserName(credentials.getMobileAppUserName());
        requestVO.setMobileAppPassword(credentials.getMobileAppPassword());
        requestVO.setMobileAppSmartSecurityKey(credentials.getMobileAppSmartSecurityKey());
        requestVO.setMobileAppDeviceId(loginUserDo.getMobileAppDeviceId());
    }

}
