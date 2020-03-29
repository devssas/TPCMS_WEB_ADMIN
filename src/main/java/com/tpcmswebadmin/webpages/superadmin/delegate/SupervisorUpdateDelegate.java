package com.tpcmswebadmin.webpages.superadmin.delegate;

import com.ssas.tpcms.engine.vo.response.TPEngineResponse;
import com.tpcmswebadmin.infrastructure.domain.LoginUserDo;
import com.tpcmswebadmin.infrastructure.domain.dto.ResponseMVCDto;
import com.tpcmswebadmin.service.policeofficer.domain.dto.PoliceOfficerCardDto;
import com.tpcmswebadmin.service.superadmin.service.SupervisorClientService;
import com.tpcmswebadmin.service.superadmin.service.SupervisorCreateClientService;
import com.tpcmswebadmin.service.superadmin.service.SupervisorUpdateClientService;
import com.tpcmswebadmin.webpages.reference.delegate.ReferenceDelegate;
import com.tpcmswebadmin.webpages.superadmin.model.SupervisorNewModel;
import com.tpcmswebadmin.webpages.superadmin.model.SupervisorUpdateModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Component
@RequiredArgsConstructor
public class SupervisorUpdateDelegate {

    private final SupervisorClientService supervisorClientService;

    private final SupervisorUpdateClientService supervisorUpdateClientService;

    private final ReferenceDelegate referenceDelegate;

    public SupervisorUpdateModel getSupervisorById(String supervisorId, HttpServletRequest httpServletRequest) {
        LoginUserDo loginUserDo = LoginUserDo.makeLoginUser(httpServletRequest);
        TPEngineResponse response = supervisorClientService.getSupervisorBySupervisorIdRaw(supervisorId, loginUserDo);

        return SupervisorUpdateModel.builder()
                .id(response.getOfficersProfileList()[0].getOfficerProfileId())
                .nationalId(response.getOfficersProfileList()[0].getNationalIdNumber())
                .firstName(response.getOfficersProfileList()[0].getOfficer_FirstName_Ar())
                .middleName(response.getOfficersProfileList()[0].getMiddleName_Ar())
                .lastName(response.getOfficersProfileList()[0].getOfficer_LastName_Ar())
                .dateOfBirth(response.getOfficersProfileList()[0].getDateOfBirth())
                .gender(response.getOfficersProfileList()[0].getGender().equals("M"))
                .passportNumber(response.getOfficersProfileList()[0].getPassportNumber())
                .countryCode(response.getOfficersProfileList()[0].getMobileNumberCountryCode())
                .mobileNumber(response.getOfficersProfileList()[0].getMobileNumber())
                .email(response.getOfficersProfileList()[0].getContactEmailAddress())
                .userName(response.getOfficersProfileList()[0].getAdminUserName())
                .userCode(null)
                .passCode(null)
                .reportingOfficer(response.getOfficersProfileList()[0].getReportingOfficerProfileId())
                .permissionToWebAccess(false)
                .officerId(response.getOfficersProfileList()[0].getOfficersIdNumber())
                .location(response.getOfficersProfileList()[0].getContactAddress())
                .city(response.getOfficersProfileList()[0].getLivingCity())
                .commandCenterId(response.getOfficersProfileList()[0].getCommandCenterId())
                .commandCenter(referenceDelegate.getCommandCenterId(response.getOfficersProfileList()[0].getCommandCenterId()).getCommandCenterCode())
                .locationIconColor(null)
                .supervisorType(response.getOfficersProfileList()[0].getAccessRoleCode())
                .supervisorUnit(response.getOfficersProfileList()[0].getReportingUnit())
                .supervisorOfficerGrade(response.getOfficersProfileList()[0].getOfficersGrade())
                .supervisorOfficerRank(response.getOfficersProfileList()[0].getOfficersRank())
                .nextOfKin(response.getOfficersProfileList()[0].getNextOfKin())
                .emergencyContactPerson(response.getOfficersProfileList()[0].getEmergencyContactPerson())
                .relationshipWithContactPerson(response.getOfficersProfileList()[0].getRelationshipWithContactPerson())
                .emergencyContactCountryCode(response.getOfficersProfileList()[0].getMobileNumberCountryCode())
                .emergencyContactNumber(response.getOfficersProfileList()[0].getEmergencyContactNumber1())
                .bloodGroup(response.getOfficersProfileList()[0].getBloogroup())
                .build();
    }

    public ResponseMVCDto updateSupervisor(SupervisorUpdateModel supervisorUpdateModel, HttpServletRequest httpServletRequest) {
        LoginUserDo loginUserDo = LoginUserDo.makeLoginUser(httpServletRequest);
        TPEngineResponse response = supervisorUpdateClientService.update(supervisorUpdateModel, loginUserDo);

        return ResponseMVCDto.prepareResponse(response);
    }
}
