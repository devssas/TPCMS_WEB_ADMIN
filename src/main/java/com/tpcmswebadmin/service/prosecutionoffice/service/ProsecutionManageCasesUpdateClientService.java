package com.tpcmswebadmin.service.prosecutionoffice.service;

import com.ssas.tpcms.engine.vo.request.CrimeReportRequestVO;
import com.ssas.tpcms.engine.vo.request.UpdateCriminalsProfileStatusRequestVO;
import com.ssas.tpcms.engine.vo.response.TPEngineResponse;
import com.tpcmswebadmin.infrastructure.client.TPCMSClient;
import com.tpcmswebadmin.infrastructure.domain.LoginUserDo;
import com.tpcmswebadmin.service.credentials.CredentialsService;
import com.tpcmswebadmin.service.credentials.domain.TpCmsWebAdminAppCredentials;
import com.tpcmswebadmin.service.criminals.service.CrimeReportsClientService;
import com.tpcmswebadmin.service.missionpermits.exception.MissionPermitsException;
import com.tpcmswebadmin.webpages.criminals.model.CrimeReportUpdateModel;
import com.tpcmswebadmin.webpages.prosecutionoffice.model.ManageCrimeFileModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProsecutionManageCasesUpdateClientService {

    private final TPCMSClient tpcmsClient;

    private final CredentialsService credentialsService;

    private final ProsecutionManageCasesClientService prosecutionManageCasesClientService;

    public TPEngineResponse update(ManageCrimeFileModel manageCrimeFileModel, LoginUserDo loginUserDo) {
        TPEngineResponse response = prosecutionManageCasesClientService.getCaseDetails(manageCrimeFileModel.getCriminalsCode(), loginUserDo);

        UpdateCriminalsProfileStatusRequestVO updateCriminalsProfileStatusRequestVO = new UpdateCriminalsProfileStatusRequestVO();
        setCredentials(updateCriminalsProfileStatusRequestVO, loginUserDo);
        setFields(updateCriminalsProfileStatusRequestVO, manageCrimeFileModel, response);

        log.info("Updating crime report request. {}", manageCrimeFileModel.getCaseId());
        try {

            return tpcmsClient.tpcmsWebAdminClient().getTPCMSCoreServices().updateCriminalsStatusByProsecutor(updateCriminalsProfileStatusRequestVO);
        } catch (RemoteException | ServiceException e) {
            throw new MissionPermitsException("Something wrong on updating crime report request. " + e.getMessage());
        }
    }

    private void setFields(UpdateCriminalsProfileStatusRequestVO updateCriminalsProfileStatusRequestVO, ManageCrimeFileModel manageCrimeFileModel, TPEngineResponse response) {
        updateCriminalsProfileStatusRequestVO.setCriminalsCode(manageCrimeFileModel.getCriminalsCode());
        updateCriminalsProfileStatusRequestVO.setCriminalsId(response.getCriminalProfileList()[0].getCriminalsId());
        updateCriminalsProfileStatusRequestVO.setCriminalsStatusCode(manageCrimeFileModel.getReviewStatus());
    }

    public void setCredentials(UpdateCriminalsProfileStatusRequestVO updateCriminalsProfileStatusRequestVO, LoginUserDo loginUserDo) {
        TpCmsWebAdminAppCredentials credentials = credentialsService.getCredentialsOfWebAdmin();

        updateCriminalsProfileStatusRequestVO.setLoginOfficersCode(loginUserDo.getLoginOfficersCode());
        updateCriminalsProfileStatusRequestVO.setLoginOfficersUnitNumber(loginUserDo.getLoginOfficerUnitNumber());
        updateCriminalsProfileStatusRequestVO.setMobileAppUserName(credentials.getMobileAppUserName());
        updateCriminalsProfileStatusRequestVO.setMobileAppPassword(credentials.getMobileAppPassword());
        updateCriminalsProfileStatusRequestVO.setMobileAppSmartSecurityKey(credentials.getMobileAppSmartSecurityKey());
        updateCriminalsProfileStatusRequestVO.setMobileAppDeviceId(loginUserDo.getMobileAppDeviceId());
    }
}
