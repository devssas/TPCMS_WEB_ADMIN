package com.tpcmswebadmin.service.policeofficer.service;

import com.ssas.tpcms.engine.vo.request.OfficersProfileRequestVO;
import com.ssas.tpcms.engine.vo.response.TPEngineResponse;
import com.tpcmswebadmin.infrastructure.client.TPCMSClient;
import com.tpcmswebadmin.infrastructure.domain.LoginUserDo;
import com.tpcmswebadmin.service.credentials.CredentialsService;
import com.tpcmswebadmin.service.credentials.domain.TpCmsWebAdminAppCredentials;
import com.tpcmswebadmin.service.missionpermits.exception.MissionPermitsException;
import com.tpcmswebadmin.service.policeofficer.domain.dto.PoliceOfficerCardDto;
import com.tpcmswebadmin.webpages.policeofficer.model.PoliceOfficerUpdateModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;

@Slf4j
@Service
@RequiredArgsConstructor
public class PoliceOfficerUpdateClientService  {

    private final TPCMSClient tpcmsClient;

    private final CredentialsService credentialsService;

    private final PoliceOfficerClientService policeOfficerClientService;

    public TPEngineResponse update(PoliceOfficerUpdateModel model, LoginUserDo loginUserDo) {
        OfficersProfileRequestVO officersProfileRequestVO = new OfficersProfileRequestVO();
        PoliceOfficerCardDto policeOfficerCardDto = policeOfficerClientService.getPoliceOfficerByOfficerId(model.getId(), loginUserDo);

        setFields(model, officersProfileRequestVO, policeOfficerCardDto);
        setCredentials(officersProfileRequestVO, loginUserDo);

        log.info("Officer to be updated. {}", model.getId());
        try {
            return tpcmsClient.tpcmsWebAdminClient().getTPCMSCoreServices().updateOfficersProfile(officersProfileRequestVO);
        } catch (RemoteException | ServiceException e) {
            throw new MissionPermitsException("Something wrong on creating Police Officer request. " + e.getMessage());
        }
    }

    public void setFields(PoliceOfficerUpdateModel model, OfficersProfileRequestVO request, PoliceOfficerCardDto policeOfficerCardDto) {
        request.setNationalIdNumber(model.getNationalId());
        request.setFirstName_Ar(model.getFirstName());
        request.setFirstName_En(model.getFirstName());
        request.setLastName_Ar(model.getLastName());
        request.setLastName_En(model.getLastName());
        request.setMiddleName_Ar(model.getMiddleName());
        request.setMiddleName_En(model.getMiddleName());
        request.setDateOfBirth(model.getDateOfBirth());
        request.setGender(model.isGender() ? "M" : "F");
        request.setPassportNumber(model.getPassportNumber());
        request.setMobileNumberCountryCode(model.getCountryCode());
        request.setMobileNumber(model.getMobileNumber());
        request.setContactEmailAddress(model.getEmail());
        request.setPermissionToCarryWeapon(model.isPermittedToCarryWeapon() ? "Y" : "N");
        request.setAllowedWeaponType(model.getWeaponType());
        request.setWeaponSerialNumber(model.getSerialNumber());
        request.setStatusCode(model.getStatus());
        request.setContactAddress(model.getContactAddress());
        request.setLivingCity(model.getCity());
        request.setAdminUserName(null); //todo
        request.setUserCode(model.getUserCode());
        request.setPassCode(model.getPassCode());
        request.setReportingOfficerId(model.getReportingOfficer());
        request.setOfficersGrade(model.getOfficerGrade());
        request.setOfficersRank(model.getOfficerRank());
        request.setReportingUnit(model.getReportingUnit());
        request.setAccessRoleCode(model.getAccessRole());
        request.setOfficersIdNumber(model.getId());
        request.setOfficerProfileId(policeOfficerCardDto.getOfficerProfileId());
        request.setEmploymentStartDate(model.getEmploymentStartDate());
        request.setExpiryDate(model.getExpiryDate());
        request.setNextOfKin(model.getNextOfKin());
        request.setEmergencyContactPerson(model.getEmergencyContactPerson());
        request.setRelationshipWithContactPerson(model.getRelationshipWithContactPerson());
        request.setEmergencyContactNumber1(model.getEmergencyContactNumber1());
        request.setEmergencyContactNumber2(model.getEmergencyContactNumber2());
        request.setBloogroup(model.getBloodGroup());
        request.setVisualIdentificationMark(model.getVisualIdentificationMark());
        request.setReportingOfficerId(model.getReportingOfficer());
    }

    public void setCredentials(OfficersProfileRequestVO request, LoginUserDo loginUserDo) {
        TpCmsWebAdminAppCredentials credentials = credentialsService.getCredentialsOfWebAdmin();

        request.setLoginOfficerCode(loginUserDo.getLoginOfficersCode());
        request.setMobileAppUserName(credentials.getMobileAppUserName());
        request.setMobileAppPassword(credentials.getMobileAppPassword());
        request.setMobileAppSmartSecurityKey(credentials.getMobileAppSmartSecurityKey());
        request.setMobileAppDeviceId(loginUserDo.getMobileAppDeviceId());
        request.setLoginOfficerUnitNumber(loginUserDo.getLoginOfficerUnitNumber());
    }
}
