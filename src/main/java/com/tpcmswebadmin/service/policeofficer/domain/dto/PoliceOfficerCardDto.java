package com.tpcmswebadmin.service.policeofficer.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PoliceOfficerCardDto implements Serializable {

    private static final long serialVersionUID = 1017384859489205961L;

    private String commandCenter;

    private String officerCode;

    private String officerName;

    private String expiryDate;

    private String unit;

    private String rank;

    private String weaponType;

    private String weaponSrl;

    private String isPermittedCarryWeapon;

    private String bloodGroup;

    private String image;

    private String email;

    @JsonIgnore
    private String additionalRemarks;

    @JsonIgnore
    private String accessRole;

    @JsonIgnore
    private String contactAddress;

    @JsonIgnore
    private String dateOfBirth;

    @JsonIgnore
    private String drivingLicenseNumber;

    @JsonIgnore
    private String emergencyContactPerson;

    @JsonIgnore
    private String emergencyContactCountryCode1;

    @JsonIgnore
    private String emergencyContactNumber1;

    @JsonIgnore
    private String emergencyContactCountryCode2;

    @JsonIgnore
    private String emergencyContactNumber2;

    @JsonIgnore
    private String employmentStartDate;

    @JsonIgnore
    private String relationshipWithContactPerson;

    @JsonIgnore
    private String firstNameAr;

    @JsonIgnore
    private String firstNameEn;

    @JsonIgnore
    private String gender;

    @JsonIgnore
    private String lastNameAr;

    @JsonIgnore
    private String lastNameEn;

    @JsonIgnore
    private String middleNameAr;

    @JsonIgnore
    private String middleNameEn;

    @JsonIgnore
    private String livingCity;

    @JsonIgnore
    private String missionDescription;

    @JsonIgnore
    private String missionType;

    @JsonIgnore
    private String countryCode;

    @JsonIgnore
    private String mobileNumber;

    @JsonIgnore
    private String nationalId;

    @JsonIgnore
    private String nextOfKin;

    @JsonIgnore
    private String officerProfileId;

    @JsonIgnore
    private String officersIdNumber;

    @JsonIgnore
    private String officerGrade;

    @JsonIgnore
    private String otherNotes;

    @JsonIgnore
    private String passportNumber;

    @JsonIgnore
    private String reportingOfficer;

    @JsonIgnore
    private String statusCode;

    @JsonIgnore
    private String visualIdentificationMark;

}
