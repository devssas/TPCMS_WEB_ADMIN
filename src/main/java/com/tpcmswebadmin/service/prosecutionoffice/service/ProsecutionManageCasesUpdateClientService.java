package com.tpcmswebadmin.service.prosecutionoffice.service;

import com.ssas.tpcms.engine.vo.request.CrimeReportRequestVO;
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

        CrimeReportRequestVO crimeReportRequestVO = new CrimeReportRequestVO();
        setCredentials(crimeReportRequestVO, loginUserDo);
        setFields(crimeReportRequestVO, manageCrimeFileModel, response);

        log.info("Updating crime report request. {}", manageCrimeFileModel.getCaseId());
        try {
            return tpcmsClient.tpcmsWebAdminClient().getTPCMSCoreServices().updateCrimeReport(crimeReportRequestVO);
        } catch (RemoteException | ServiceException e) {
            throw new MissionPermitsException("Something wrong on updating crime report request. " + e.getMessage());
        }
    }

    private void setFields(CrimeReportRequestVO crimeReportRequestVO, ManageCrimeFileModel crimeReportUpdateModel, TPEngineResponse response) {
        crimeReportRequestVO.setCrimianlStatusCode(crimeReportUpdateModel.getReviewStatus());

        crimeReportRequestVO.setAdditionalRemarks(response.getCriminalProfileList()[0].getAdditionalRemarks());
        crimeReportRequestVO.setBloogroup(response.getCriminalProfileList()[0].getBloogroup());
        crimeReportRequestVO.setCaseBriefDesc(response.getCriminalProfileList()[0].getCaseBriefDesc());
        crimeReportRequestVO.setCaseId(response.getCriminalProfileList()[0].getCaseId());
        crimeReportRequestVO.setCasePhoto1(response.getCriminalProfileList()[0].getProfilePhoto1());
        crimeReportRequestVO.setCasePhoto2(response.getCriminalProfileList()[0].getProfilePhoto2());
        crimeReportRequestVO.setCasePhoto3(response.getCriminalProfileList()[0].getProfilePhoto3());
        crimeReportRequestVO.setCasePhoto4(response.getCriminalProfileList()[0].getProfilePhoto4());
        crimeReportRequestVO.setCasePhoto5(response.getCriminalProfileList()[0].getProfilePhoto5());
        crimeReportRequestVO.setCasePhoto6(response.getCriminalProfileList()[0].getProfilePhoto6());
        crimeReportRequestVO.setCasePhoto7(response.getCriminalProfileList()[0].getProfilePhoto7());
        crimeReportRequestVO.setCasePhoto8(response.getCriminalProfileList()[0].getProfilePhoto8());
        crimeReportRequestVO.setCasePhoto9(response.getCriminalProfileList()[0].getProfilePhoto9());
        crimeReportRequestVO.setCasePhoto10(response.getCriminalProfileList()[0].getProfilePhoto10());
        crimeReportRequestVO.setCityName(response.getCriminalProfileList()[0].getCrimeLocation());
        crimeReportRequestVO.setContactAddress(response.getCriminalProfileList()[0].getContactAddress());
        crimeReportRequestVO.setContactEmailAddress(response.getCriminalProfileList()[0].getContactEmailAddress());
        crimeReportRequestVO.setCrimeLocation(response.getCriminalProfileList()[0].getCrimeLocation());
        crimeReportRequestVO.setCrimeName(response.getCriminalProfileList()[0].getCrimeName());
        crimeReportRequestVO.setCrimeReportsId(response.getCriminalProfileList()[0].getCaseId());
        crimeReportRequestVO.setCrimeScene(response.getCriminalProfileList()[0].getCrimeScene());
        crimeReportRequestVO.setDrivingLicenseNumber(response.getCriminalProfileList()[0].getDrivingLicenseNumber());
        crimeReportRequestVO.setNationalIdNumber(response.getCriminalProfileList()[0].getNationalIdNumber());
        crimeReportRequestVO.setOtherAttachments(response.getCriminalProfileList()[0].getOtherAttachments());
        crimeReportRequestVO.setOtherNotes(response.getCriminalProfileList()[0].getOtherNotes());
        crimeReportRequestVO.setPassportNumber(response.getCriminalProfileList()[0].getPassportNumber());
        crimeReportRequestVO.setPersoanlIdNumber(response.getCriminalProfileList()[0].getPersoanlIdNumber());
        crimeReportRequestVO.setReportedDate(response.getCriminalProfileList()[0].getReportedDate());
        crimeReportRequestVO.setVisualIdentificationMark(response.getCriminalProfileList()[0].getVisualIdentificationMark());
    }

    public void setCredentials(CrimeReportRequestVO crimeReportRequestVO, LoginUserDo loginUserDo) {
        TpCmsWebAdminAppCredentials credentials = credentialsService.getCredentialsOfWebAdmin();

        crimeReportRequestVO.setLoginOfficerReportingUnit(loginUserDo.getLoginOfficerUnitNumber());
        crimeReportRequestVO.setLoginOfficersCode(loginUserDo.getLoginOfficersCode());
        crimeReportRequestVO.setMobileAppUserName(credentials.getMobileAppUserName());
        crimeReportRequestVO.setMobileAppPassword(credentials.getMobileAppPassword());
        crimeReportRequestVO.setMobileAppSmartSecurityKey(credentials.getMobileAppSmartSecurityKey());
        crimeReportRequestVO.setMobileAppDeviceId(loginUserDo.getMobileAppDeviceId());
    }
}
