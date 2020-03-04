package com.tpcmswebadmin.webpages.policeofficer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PoliceOfficerCreateModel {

    private String nationalId;

    private String firstName;

    private String middleName;

    private String lastName;

    private String dateOfBirth;

    private String gender;

    private String passportNumber;

    private String countryCode;

    private String mobileNumber;

    private String email;

    private boolean isPermittedToCarryWeapon;

    private String weaponType;

    private String serialNumber;

    private String status;

    private String contactAddress;

    private String city;

    private String userCode;

    private String passCode;

    private String reportingOfficer;

    private String officerGrade;

    private String officerRank;

    private String reportingUnit;

    private String accessRole;

    private String officerId;

    private String employmentStartDate;

    private String expiryDate;

    private String nextOfKin;

    private String emergencyContactPerson;

    private String relationshipWithContactPerson;

    private String emergencyContactNumber1;

    private String emergencyContactNumber2;

    private String bloodGroup;

    private String visualIdentificationMark;

}
