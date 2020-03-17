package com.tpcmswebadmin.service.criminals.service;

import com.ssas.tpcms.engine.vo.request.CriminalProfileRequestVO;
import com.ssas.tpcms.engine.vo.response.TPEngineResponse;
import com.tpcmswebadmin.infrastructure.client.TPCMSClient;
import com.tpcmswebadmin.infrastructure.domain.LoginUserDo;
import com.tpcmswebadmin.infrastructure.service.ClientCreateServiceAPI;
import com.tpcmswebadmin.service.credentials.CredentialsService;
import com.tpcmswebadmin.service.credentials.domain.TpCmsWebAdminAppCredentials;
import com.tpcmswebadmin.service.missionpermits.exception.MissionPermitsException;
import com.tpcmswebadmin.webpages.criminals.model.CriminalCaseCreateModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;

@Slf4j
@Service
@RequiredArgsConstructor
public class CriminalCaseCreateService implements ClientCreateServiceAPI<CriminalCaseCreateModel, CriminalProfileRequestVO> {

    private final TPCMSClient tpcmsClient;

    private final CredentialsService credentialsService;

    @Override
    public TPEngineResponse create(CriminalCaseCreateModel model, LoginUserDo loginUserDo) {
        CriminalProfileRequestVO criminalProfileRequestVO = new CriminalProfileRequestVO();
        setFields(model, criminalProfileRequestVO);
        setCredentials(criminalProfileRequestVO, loginUserDo);

        try {
            return tpcmsClient.tpcmsWebAdminClient().getTPCMSCoreServices().createCriminalsProfile(criminalProfileRequestVO);
        } catch (RemoteException | ServiceException e) {
            throw new MissionPermitsException("Something wrong on creating Police Officer request. " + criminalProfileRequestVO.getMobileAppUserName());
        }
    }

    @Override
    public void setFields(CriminalCaseCreateModel model, CriminalProfileRequestVO requestVO) {
        requestVO.setAdditionalRemarks(null); //todo no additional remarks
        requestVO.setAdditional_Parameter1(null);
        requestVO.setAdditional_Parameter2(null);
        requestVO.setAdditional_Parameter3(null);
        requestVO.setAdditional_Parameter4(null);
        requestVO.setAdminOfficerStatement(model.getStatement());
        requestVO.setArrestedDate(null); //todo no idea
        requestVO.setArrestedOfficerId(null); //todo no idea
        requestVO.setBloogroup(model.getBloodGroup());
        requestVO.setCaseBriefDesc(model.getCaseBrief());
        requestVO.setCaseId(model.getCaseId());
        requestVO.setCityOfLiving(model.getPersonalCity());
        requestVO.setContactAddress(model.getContactAddress());
        requestVO.setContactEmailAddress(null); //todo no info
        requestVO.setCrimeClassification(model.getCrimeClassification());
        requestVO.setCrimeLocation(model.getCrimeLocation());
        requestVO.setCrimeName(model.getCrimeTitle());
        requestVO.setCrimeScene(model.getCrimeScene());
        requestVO.setCrimianlStatusCode(model.getStatus());
        requestVO.setCriminalWitnessList(null);
        requestVO.setCriminalsCode(null); //todo no info
        requestVO.setCriminalsId(null); //todo no info
        requestVO.setDateOfBirth(model.getDateOfBirth());
        requestVO.setDrivingLicenseNumber(model.getDrivingLicenceNumber());
        requestVO.setExpiryDate(null); //todo no info
        requestVO.setFirstName_Ar(model.getFirstName());
        requestVO.setFirstName_En(model.getFirstName());
        requestVO.setFlagedDate(model.getFlaggedDate());
        requestVO.setFriendsContactInformation(model.getFriendsAddress());
        requestVO.setGender(model.getGender());
        requestVO.setInterpolWantedCase("N"); //todo no field on screen
        requestVO.setLastName_Ar(model.getLastName());
        requestVO.setLastName_En(model.getLastName());
        requestVO.setMiddleName_Ar(model.getMiddleName());
        requestVO.setMiddleName_En(model.getMiddleName());
        requestVO.setMobileNumber(model.getMobileNumber());
        requestVO.setMobileNumberCountryCode(model.getCountryCode());
        requestVO.setNationalIdNumber(model.getNationalId());
        requestVO.setNationalityISOCode("LY"); //todo ask if its LY
        requestVO.setNotesDesc(null); //todo nothing on table
        requestVO.setOtherAttachments(null);
        requestVO.setOtherNotes(null);
        requestVO.setOtherPersonalInformation(model.getVisualIdentificationMark());
        requestVO.setParentsContactInformation(model.getParentsAddress());
        requestVO.setPassportNumber(model.getPassportNumber());
        requestVO.setPersoanlIdNumber(model.getNationalId());
        requestVO.setPreviousCriminalProfileId_1(null);
        requestVO.setPreviousCriminalProfileId_2(null);
        requestVO.setPreviousCriminalProfileId_3(null);
        requestVO.setProfilePhoto1(null);
        requestVO.setProfilePhoto10(null);
        requestVO.setProfilePhoto2(null);
        requestVO.setProfilePhoto3(null);
        requestVO.setProfilePhoto4(null);
        requestVO.setProfilePhoto5(null);
        requestVO.setProfilePhoto6(null);
        requestVO.setProfilePhoto7(null);
        requestVO.setProfilePhoto8(null);
        requestVO.setProfilePhoto9(null);
        requestVO.setReferenceCrimeReportId_1(null);
        requestVO.setReferenceCrimeReportId_2(null);
        requestVO.setReferenceCrimeReportId_3(null);
        requestVO.setRelativesContactInformation(model.getRelativesAddress());
        requestVO.setReportedDate(model.getFlaggedDate());
        requestVO.setTypeOfCrime(model.getCrimeType());
        requestVO.setVisualIdentificationMark(model.getVisualIdentificationMark());
        requestVO.setWantedBy(model.getWantedBy());
        requestVO.setWantedType(model.getWantedType());
    }

    @Override
    public void setCredentials(CriminalProfileRequestVO request, LoginUserDo loginUserDo) {
        TpCmsWebAdminAppCredentials credentials = credentialsService.getCredentialsOfWebAdmin();

        request.setLoginOfficersCode(loginUserDo.getLoginOfficersCode());
        request.setMobileAppUserName(credentials.getMobileAppUserName());
        request.setMobileAppPassword(credentials.getMobileAppPassword());
        request.setMobileAppSmartSecurityKey(credentials.getMobileAppSmartSecurityKey());
        request.setMobileAppDeviceId(loginUserDo.getMobileAppDeviceId());
    }
}
