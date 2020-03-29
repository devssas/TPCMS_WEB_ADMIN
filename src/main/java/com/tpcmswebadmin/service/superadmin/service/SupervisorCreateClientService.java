package com.tpcmswebadmin.service.superadmin.service;

import com.ssas.tpcms.engine.vo.request.OfficersProfileRequestVO;
import com.ssas.tpcms.engine.vo.response.TPEngineResponse;
import com.tpcmswebadmin.infrastructure.client.TPCMSClient;
import com.tpcmswebadmin.infrastructure.domain.LoginUserDo;
import com.tpcmswebadmin.infrastructure.service.ClientCreateServiceAPI;
import com.tpcmswebadmin.service.credentials.CredentialsService;
import com.tpcmswebadmin.service.credentials.domain.TpCmsWebAdminAppCredentials;
import com.tpcmswebadmin.service.missionpermits.exception.MissionPermitsException;
import com.tpcmswebadmin.webpages.superadmin.model.SupervisorNewModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;

@Slf4j
@Service
@RequiredArgsConstructor
public class SupervisorCreateClientService implements ClientCreateServiceAPI<SupervisorNewModel, OfficersProfileRequestVO>  {

    private final TPCMSClient tpcmsClient;

    private final CredentialsService credentialsService;

    @Override
    public TPEngineResponse create(SupervisorNewModel model, LoginUserDo loginUserDo) {
        OfficersProfileRequestVO officersProfileRequestVO = new OfficersProfileRequestVO();
        setFields(model, officersProfileRequestVO);
        setCredentials(officersProfileRequestVO, loginUserDo);

        try {
            return tpcmsClient.tpcmsWebAdminClient().getTPCMSCoreServices().createOfficersProfile(officersProfileRequestVO);
        } catch (RemoteException | ServiceException e) {
            throw new MissionPermitsException("Something wrong on creating Police Officer request. " + e.getMessage());
        }
    }

    @Override
    public void setFields(SupervisorNewModel model, OfficersProfileRequestVO request) {
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
        request.setOfficersIdNumber(model.getOfficerId());
        request.setReportingOfficerId(model.getReportingOfficer());
        request.setContactAddress(model.getLocation());
        request.setLivingCity(model.getCity());
        request.setCommandCenterId(model.getCommandCenterId());
        request.setReportingUnit(model.getSupervisorUnit());
        request.setAccessRoleCode(model.getSupervisorType());
        request.setNextOfKin(model.getNextOfKin());
        request.setEmergencyContactPerson(model.getEmergencyContactPerson());
        request.setRelationshipWithContactPerson(model.getRelationshipWithContactPerson());
        request.setEmergencyContactNumber1(model.getEmergencyContactNumber());
        request.setBloogroup(model.getBloodGroup());
        request.setUserCode(model.getUserCode());
        request.setPassCode(model.getPassCode());
        request.setAdminUserName(model.getUserName());
        request.setOfficersRank(model.getSupervisorOfficerRank());
        request.setOfficersGrade(model.getSupervisorOfficerGrade());
    }

    @Override
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
