package com.tpcmswebadmin.webpages.criminals.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CriminalCaseCreateModel {

    private String image;

    @NotEmpty(message = "{error.criminalCase.nationalId.notNull}")
    private String nationalId;

    @NotEmpty(message = "{error.criminalCase.firstName.notNull}")
    private String firstName;

    private String middleName;

    @NotEmpty(message = "{error.criminalCase.lastName.notNull}")
    private String lastName;

    private String dateOfBirth;

    private String gender;

    private String crimeClassification;

    private String drivingLicenceNumber;

    private String passportNumber;

    private String bloodGroup;

    private String visualIdentificationMark;

    private String countryCode;

    private String mobileNumber;

    private String email;

    private String personalIdCard;

    private String flaggedDate;

    private String wantedBy;

    private String caseId;

    private String caseBrief;

    private String status;

    private String wantedType;

    @NotEmpty(message = "{error.criminalCase.contactAddress.notNull}")
    private String contactAddress;

    private String parentsAddress;

    private String friendsAddress;

    private String relativesAddress;

    private String personalCity;

    private String crimeType;

    private String crimeTitle;

    private String crimeScene;

    private String crimeLocation;

    private String crimeImage;

    private String previousCase;

    private String witnessName;

    private String witnessSurname;

    private String statement;

    private String casePhoto;

}
