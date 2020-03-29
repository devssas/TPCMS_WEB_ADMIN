package com.tpcmswebadmin.webpages.policeofficer.delegate;

import com.ssas.tpcms.engine.vo.response.TPEngineResponse;
import com.tpcmswebadmin.infrastructure.domain.LoginUserDo;
import com.tpcmswebadmin.infrastructure.domain.dto.ResponseMVCDto;
import com.tpcmswebadmin.service.policeofficer.domain.dto.PoliceOfficerCardDto;
import com.tpcmswebadmin.service.policeofficer.service.PoliceOfficerClientService;
import com.tpcmswebadmin.service.policeofficer.service.PoliceOfficerUpdateClientService;
import com.tpcmswebadmin.webpages.policeofficer.model.PoliceOfficerUpdateModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@RequiredArgsConstructor
public class PoliceOfficerUpdateDelegate {

    private final PoliceOfficerClientService policeOfficerClientService;

    private final PoliceOfficerUpdateClientService policeOfficerUpdateClientService;

    public PoliceOfficerUpdateModel getOfficerById(String officerId, HttpServletRequest httpServletRequest) {
        LoginUserDo loginUserDo = LoginUserDo.makeLoginUser(httpServletRequest);
        PoliceOfficerCardDto policeOfficerCardDto = policeOfficerClientService.getPoliceOfficerByOfficerId(officerId, loginUserDo);

        return PoliceOfficerUpdateModel.builder()
                .nationalId(policeOfficerCardDto.getNationalId())
                .firstName(policeOfficerCardDto.getFirstNameAr())
                .middleName(policeOfficerCardDto.getMiddleNameAr())
                .lastName(policeOfficerCardDto.getLastNameAr())
                .dateOfBirth(policeOfficerCardDto.getDateOfBirth())
                .gender(policeOfficerCardDto.getGender().equals("M"))
                .passportNumber(policeOfficerCardDto.getPassportNumber())
                .countryCode(policeOfficerCardDto.getCountryCode())
                .mobileNumber(policeOfficerCardDto.getMobileNumber())
                .email(policeOfficerCardDto.getEmail())//todo
                .isPermittedToCarryWeapon(policeOfficerCardDto.getIsPermittedCarryWeapon().equals("Y"))
                .weaponType(policeOfficerCardDto.getWeaponType())
                .serialNumber(policeOfficerCardDto.getWeaponSrl())
                .status(policeOfficerCardDto.getStatusCode())
                .contactAddress(policeOfficerCardDto.getContactAddress())
                .city(policeOfficerCardDto.getLivingCity())
                .userCode(null)
                .passCode(null)
                .reportingOfficer(policeOfficerCardDto.getReportingOfficer())
                .officerGrade(policeOfficerCardDto.getOfficerGrade())
                .officerRank(policeOfficerCardDto.getRank())
                .reportingUnit(policeOfficerCardDto.getUnit())
                .accessRole(policeOfficerCardDto.getAccessRole())
                .employmentStartDate(policeOfficerCardDto.getEmploymentStartDate())
                .expiryDate(policeOfficerCardDto.getExpiryDate())
                .nextOfKin(policeOfficerCardDto.getNextOfKin())
                .emergencyContactPerson(policeOfficerCardDto.getEmergencyContactPerson())
                .relationshipWithContactPerson(policeOfficerCardDto.getRelationshipWithContactPerson())
                .emergencyContactCountryCode1(policeOfficerCardDto.getEmergencyContactCountryCode1() == null ? "218" : policeOfficerCardDto.getEmergencyContactCountryCode1())
                .emergencyContactNumber1(policeOfficerCardDto.getEmergencyContactNumber1())
                .emergencyContactCountryCode2(policeOfficerCardDto.getEmergencyContactCountryCode2() == null ? "218" : policeOfficerCardDto.getEmergencyContactCountryCode2())
                .emergencyContactNumber2(policeOfficerCardDto.getEmergencyContactNumber1())
                .bloodGroup(policeOfficerCardDto.getBloodGroup())
                .visualIdentificationMark(policeOfficerCardDto.getVisualIdentificationMark())
                .id(policeOfficerCardDto.getOfficerProfileId())
                .officerCode(policeOfficerCardDto.getOfficerCode())
                .build();
    }

    public ResponseMVCDto updateOfficer(PoliceOfficerUpdateModel policeOfficerUpdateModel, HttpServletRequest httpServletRequest) {
        LoginUserDo loginUserDo = LoginUserDo.makeLoginUser(httpServletRequest);
        TPEngineResponse response = policeOfficerUpdateClientService.update(policeOfficerUpdateModel, loginUserDo);

        return ResponseMVCDto.prepareResponse(response);
    }


}
