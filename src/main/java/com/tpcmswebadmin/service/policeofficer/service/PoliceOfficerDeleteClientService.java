package com.tpcmswebadmin.service.policeofficer.service;

import com.ssas.tpcms.engine.vo.request.OfficersProfileRequestVO;
import com.ssas.tpcms.engine.vo.response.TPEngineResponse;
import com.tpcmswebadmin.infrastructure.client.TPCMSClient;
import com.tpcmswebadmin.infrastructure.domain.LoginUserDo;
import com.tpcmswebadmin.service.credentials.CredentialsService;
import com.tpcmswebadmin.service.credentials.domain.TpCmsWebAdminAppCredentials;
import com.tpcmswebadmin.service.missionpermits.exception.MissionPermitsException;
import com.tpcmswebadmin.service.policeofficer.domain.dto.PoliceOfficerCardDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;

@Slf4j
@Service
@RequiredArgsConstructor
public class PoliceOfficerDeleteClientService {

    private final TPCMSClient tpcmsClient;

    private final CredentialsService credentialsService;

    private final PoliceOfficerClientService policeOfficerClientService;

    public TPEngineResponse delete(String officerId, LoginUserDo loginUserDo) {
        OfficersProfileRequestVO officersProfileRequestVO = new OfficersProfileRequestVO();
        PoliceOfficerCardDto policeOfficerCardDto = policeOfficerClientService.getPoliceOfficerByOfficerId(officerId, loginUserDo);

        setFields(policeOfficerCardDto, officersProfileRequestVO);
        setCredentials(officersProfileRequestVO, loginUserDo);
        officersProfileRequestVO.setIsDeleteOfficerProfile("Y");

        log.info("Officer to be deleted. {}", officerId);
        try {
            return tpcmsClient.tpcmsWebAdminClient().getTPCMSCoreServices().updateOfficersProfile(officersProfileRequestVO);
        } catch (RemoteException | ServiceException e) {
            throw new MissionPermitsException("Something wrong on creating Police Officer request. " + e.getMessage());
        }
    }

    public void setFields(PoliceOfficerCardDto policeOfficerCardDto, OfficersProfileRequestVO request) {
        request.setAdditionalRemarks(policeOfficerCardDto.getAdditionalRemarks());
        request.setAllowedWeaponType(policeOfficerCardDto.getWeaponType());
        request.setBloogroup(policeOfficerCardDto.getBloodGroup());
        request.setContactAddress(policeOfficerCardDto.getContactAddress());
        request.setDateOfBirth(policeOfficerCardDto.getDateOfBirth());
        request.setDrivingLicenseNumber(policeOfficerCardDto.getDrivingLicenseNumber());
        request.setEmergencyContactNumber1(policeOfficerCardDto.getEmergencyContactNumber1());
        request.setEmergencyContactNumber2(policeOfficerCardDto.getEmergencyContactNumber2());
        request.setEmploymentStartDate(policeOfficerCardDto.getEmploymentStartDate());
        request.setFirstName_Ar(policeOfficerCardDto.getFirstNameAr());
        request.setFirstName_En(policeOfficerCardDto.getFirstNameEn());
        request.setGender(policeOfficerCardDto.getGender());
        request.setLastName_Ar(policeOfficerCardDto.getLastNameAr());
        request.setLastName_En(policeOfficerCardDto.getLastNameEn());
        request.setLivingCity(policeOfficerCardDto.getLivingCity());
        request.setMissionDescription(policeOfficerCardDto.getMissionDescription());
        request.setMissionType(policeOfficerCardDto.getMissionType());
        request.setMobileNumberCountryCode(policeOfficerCardDto.getCountryCode());
        request.setMobileNumber(policeOfficerCardDto.getMobileNumber());
        request.setNationalIdNumber(policeOfficerCardDto.getNationalId());
        request.setNextOfKin(policeOfficerCardDto.getNextOfKin());
        request.setOfficerCode(policeOfficerCardDto.getOfficerCode());
        request.setOfficerProfileId(policeOfficerCardDto.getOfficerProfileId());
        request.setOfficersIdNumber(policeOfficerCardDto.getOfficersIdNumber());
        request.setOfficersRank(policeOfficerCardDto.getRank());
        request.setOtherAttachments(null);
        request.setOtherNotes(policeOfficerCardDto.getOtherNotes());
        request.setPassportNumber(policeOfficerCardDto.getPassportNumber());
        request.setWeaponSerialNumber(policeOfficerCardDto.getWeaponSrl());
        request.setPermissionToCarryWeapon(policeOfficerCardDto.getIsPermittedCarryWeapon());
        request.setReportingOfficerId(policeOfficerCardDto.getReportingOfficer());
        request.setReportingUnit(policeOfficerCardDto.getUnit());
        request.setStatusCode(policeOfficerCardDto.getStatusCode());
        request.setVisualIdentificationMark(policeOfficerCardDto.getVisualIdentificationMark());
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
