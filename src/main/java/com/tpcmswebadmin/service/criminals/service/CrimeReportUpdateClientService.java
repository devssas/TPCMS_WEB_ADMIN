package com.tpcmswebadmin.service.criminals.service;

import com.ssas.tpcms.engine.vo.request.CrimeReportRequestVO;
import com.ssas.tpcms.engine.vo.response.TPEngineResponse;
import com.tpcmswebadmin.infrastructure.client.TPCMSClient;
import com.tpcmswebadmin.infrastructure.domain.LoginUserDo;
import com.tpcmswebadmin.service.credentials.CredentialsService;
import com.tpcmswebadmin.service.credentials.domain.TpCmsWebAdminAppCredentials;
import com.tpcmswebadmin.service.missionpermits.exception.MissionPermitsException;
import com.tpcmswebadmin.webpages.criminals.model.CrimeReportUpdateModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;

@Slf4j
@Service
@RequiredArgsConstructor
public class CrimeReportUpdateClientService {

    private final TPCMSClient tpcmsClient;

    private final CredentialsService credentialsService;

    private final CrimeReportsClientService crimeReportsClientService;

    public TPEngineResponse update(CrimeReportUpdateModel crimeReportUpdateModel, LoginUserDo loginUserDo) {
        TPEngineResponse response = crimeReportsClientService.getCrimeReportByCrimeReportId(crimeReportUpdateModel.getId(), loginUserDo);

        CrimeReportRequestVO crimeReportRequestVO = new CrimeReportRequestVO();
        setCredentials(crimeReportRequestVO, loginUserDo);
        setFields(crimeReportRequestVO, crimeReportUpdateModel, response);

        log.info("Updating crime report request. {}", crimeReportUpdateModel.getId());
        try {
            return tpcmsClient.tpcmsWebAdminClient().getTPCMSCoreServices().updateCrimeReport(crimeReportRequestVO);
        } catch (RemoteException | ServiceException e) {
            throw new MissionPermitsException("Something wrong on updating crime report request. " + e.getMessage());
        }
    }

    private void setFields(CrimeReportRequestVO crimeReportRequestVO, CrimeReportUpdateModel crimeReportUpdateModel, TPEngineResponse response) {
        crimeReportRequestVO.setAdditionalRemarks(response.getCrimeReportList()[0].getAdditionalRemarks());
        crimeReportRequestVO.setBloogroup(response.getCrimeReportList()[0].getBloodGroup());
        crimeReportRequestVO.setCaseBriefDesc(response.getCrimeReportList()[0].getCaseBriefDesc());
        crimeReportRequestVO.setCaseId(response.getCrimeReportList()[0].getCaseId());
        crimeReportRequestVO.setCasePhoto1(response.getCrimeReportList()[0].getCasePhoto1());
        crimeReportRequestVO.setCasePhoto2(response.getCrimeReportList()[0].getCasePhoto2());
        crimeReportRequestVO.setCasePhoto3(response.getCrimeReportList()[0].getCasePhoto3());
        crimeReportRequestVO.setCasePhoto4(response.getCrimeReportList()[0].getCasePhoto4());
        crimeReportRequestVO.setCasePhoto5(response.getCrimeReportList()[0].getCasePhoto5());
        crimeReportRequestVO.setCasePhoto6(response.getCrimeReportList()[0].getCasePhoto6());
        crimeReportRequestVO.setCasePhoto7(response.getCrimeReportList()[0].getCasePhoto7());
        crimeReportRequestVO.setCasePhoto8(response.getCrimeReportList()[0].getCasePhoto8());
        crimeReportRequestVO.setCasePhoto9(response.getCrimeReportList()[0].getCasePhoto9());
        crimeReportRequestVO.setCasePhoto10(response.getCrimeReportList()[0].getCasePhoto10());
        crimeReportRequestVO.setCityName(response.getCrimeReportList()[0].getCityName());
        crimeReportRequestVO.setContactAddress(response.getCrimeReportList()[0].getContactAddress());
        crimeReportRequestVO.setContactEmailAddress(response.getCrimeReportList()[0].getContactEmailAddress());
        crimeReportRequestVO.setCrimeLocation(response.getCrimeReportList()[0].getCrimeLocation());
        crimeReportRequestVO.setCrimeName(response.getCrimeReportList()[0].getCrimeName());
        crimeReportRequestVO.setCrimeReportsId(response.getCrimeReportList()[0].getCrimeReportsId());
        crimeReportRequestVO.setCrimeScene(response.getCrimeReportList()[0].getCrimeScene());
        crimeReportRequestVO.setCrimianlStatusCode(crimeReportUpdateModel.getReviewStatus());
        crimeReportRequestVO.setTypeOfCrime(crimeReportUpdateModel.getCrimeType());
        crimeReportRequestVO.setDrivingLicenseNumber(response.getCrimeReportList()[0].getDrivingLicenseNumber());
        crimeReportRequestVO.setNationalIdNumber(response.getCrimeReportList()[0].getNationalIdNumber());
        crimeReportRequestVO.setOtherAttachments(response.getCrimeReportList()[0].getOtherAttachments());
        crimeReportRequestVO.setOtherNotes(response.getCrimeReportList()[0].getOtherNotes());
        crimeReportRequestVO.setPassportNumber(response.getCrimeReportList()[0].getPassportNumber());
        crimeReportRequestVO.setPersoanlIdNumber(response.getCrimeReportList()[0].getPersoanlIdNumber());
        crimeReportRequestVO.setReportedDate(response.getCrimeReportList()[0].getReportedDate());
        crimeReportRequestVO.setVisualIdentificationMark(response.getCrimeReportList()[0].getVisualIdentificationMark());
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
