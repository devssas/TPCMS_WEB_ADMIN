package com.tpcmswebadmin.service.superadmin.service;

import com.ssas.tpcms.engine.vo.request.OfficersProfileRequestVO;
import com.ssas.tpcms.engine.vo.response.TPEngineResponse;
import com.tpcmswebadmin.infrastructure.client.TPCMSClient;
import com.tpcmswebadmin.infrastructure.domain.LoginUserDo;
import com.tpcmswebadmin.service.credentials.CredentialsService;
import com.tpcmswebadmin.service.credentials.domain.TpCmsWebAdminAppCredentials;
import com.tpcmswebadmin.service.missionpermits.exception.MissionPermitsException;
import com.tpcmswebadmin.webpages.superadmin.model.SupervisorUpdateModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;

@Slf4j
@Service
@RequiredArgsConstructor
public class SupervisorDeleteClientService {

    private final TPCMSClient tpcmsClient;

    private final CredentialsService credentialsService;

    private final SupervisorClientService supervisorClientService;

    public TPEngineResponse delete(String supervisorId, LoginUserDo loginUserDo) {
        TPEngineResponse response = supervisorClientService.getSupervisorBySupervisorIdRaw(supervisorId, loginUserDo);
        OfficersProfileRequestVO officersProfileRequestVO = new OfficersProfileRequestVO();
        setFields(response, officersProfileRequestVO);
        setCredentials(officersProfileRequestVO, loginUserDo);
        officersProfileRequestVO.setIsDeleteOfficerProfile("Y");

        log.info("Delete Supervisor request. {}", supervisorId);
        try {
            return tpcmsClient.tpcmsWebAdminClient().getTPCMSCoreServices().updateOfficersProfile(officersProfileRequestVO);
        } catch (RemoteException | ServiceException e) {
            throw new MissionPermitsException("Something wrong on deleting Supervisor request. " + e.getMessage());
        }
    }

    public void setFields(TPEngineResponse response, OfficersProfileRequestVO request) {
        request.setNationalIdNumber(response.getOfficersProfileList()[0].getNationalIdNumber());
        request.setFirstName_Ar(response.getOfficersProfileList()[0].getOfficer_FirstName_Ar());
        request.setFirstName_En(response.getOfficersProfileList()[0].getOfficer_FirstName_En());
        request.setLastName_Ar(response.getOfficersProfileList()[0].getOfficer_LastName_Ar());
        request.setLastName_En(response.getOfficersProfileList()[0].getOfficer_LastName_En());
        request.setMiddleName_Ar(response.getOfficersProfileList()[0].getMiddleName_Ar());
        request.setMiddleName_En(response.getOfficersProfileList()[0].getMiddleName_En());
        request.setDateOfBirth(response.getOfficersProfileList()[0].getDateOfBirth());
        request.setGender(response.getOfficersProfileList()[0].getGender());
        request.setPassportNumber(response.getOfficersProfileList()[0].getPassportNumber());
        request.setMobileNumberCountryCode(response.getOfficersProfileList()[0].getMobileNumberCountryCode());
        request.setMobileNumber(response.getOfficersProfileList()[0].getMobileNumber());
        request.setContactEmailAddress(response.getOfficersProfileList()[0].getContactEmailAddress());
        request.setOfficersIdNumber(response.getOfficersProfileList()[0].getOfficersIdNumber());
        request.setReportingOfficerId(response.getOfficersProfileList()[0].getReportingOfficerProfileId());
        request.setContactAddress(response.getOfficersProfileList()[0].getContactAddress());
        request.setLivingCity(response.getOfficersProfileList()[0].getLivingCity());
        request.setCommandCenterId(response.getOfficersProfileList()[0].getCommandCenterId());
        request.setReportingUnit(response.getOfficersProfileList()[0].getReportingUnit());
        request.setAccessRoleCode(response.getOfficersProfileList()[0].getAccessRoleCode());
        request.setNextOfKin(response.getOfficersProfileList()[0].getNextOfKin());
        request.setEmergencyContactPerson(response.getOfficersProfileList()[0].getEmergencyContactPerson());
        request.setRelationshipWithContactPerson(response.getOfficersProfileList()[0].getRelationshipWithContactPerson());
        request.setEmergencyContactNumber1(response.getOfficersProfileList()[0].getEmergencyContactNumber1());
        request.setBloogroup(response.getOfficersProfileList()[0].getBloogroup());
        request.setAdminUserName(response.getOfficersProfileList()[0].getAdminUserName());
        request.setOfficersRank(response.getOfficersProfileList()[0].getOfficersRank());
        request.setOfficersGrade(response.getOfficersProfileList()[0].getOfficersGrade());
        request.setOfficerProfileId(response.getOfficersProfileList()[0].getOfficerProfileId());
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
