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

    @NotEmpty(message = "{error.criminalCase.dateOfBirth.notNull}")
    private String dateOfBirth;

    private String gender;

    private String crimeClassification;

    private String drivingLicenceNumber;

    @NotEmpty(message = "{error.criminalCase.passportNumber.notNull}")
    private String passportNumber;

    private String bloodGroup;

    private String visualIdentificationMark;

    @NotEmpty(message = "{error.criminalCase.contactAddress.notNull}")
    private String countryCode;

    @NotEmpty(message = "{error.criminalCase.contactAddress.notNull}")
    private String mobileNumber;

    private String email;

    @NotEmpty(message = "{error.criminalCase.personalIdCard.notNull}")
    private String personalIdCard;

    @NotEmpty(message = "{error.criminalCase.contactAddress.notNull}")
    private String flaggedDate;

    private String wantedBy;

    @NotEmpty(message = "{error.criminalCase.caseId.notNull}")
    private String caseId;

    @NotEmpty(message = "{error.criminalCase.caseBrief.notNull}")
    private String caseBrief;

    private String status;

    private String wantedType;

    @NotEmpty(message = "{error.criminalCase.contactAddress.notNull}")
    private String contactAddress;

    private String parentsAddress;

    @NotEmpty(message = "{error.criminalCase.friendsAddress.notNull}")
    private String friendsAddress;

    private String relativesAddress;

    @NotEmpty(message = "{error.criminalCase.personalCity.notNull}")
    private String personalCity;

    private String crimeType;

    @NotEmpty(message = "{error.criminalCase.crimeTitle.notNull}")
    private String crimeTitle;

    @NotEmpty(message = "{error.criminalCase.crimeScene.notNull}")
    private String crimeScene;

    @NotEmpty(message = "{error.criminalCase.crimeLocation.notNull}")
    private String crimeLocation;

    private String crimeImage;

    private String previousCase;

    private String witnessName;

    private String witnessSurname;

    private String statement;

    private String casePhoto;

}
