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
public class CrimeReportUpdateModel {

    private String crimeLocation;

    private String crimeType;

    private String suspectName1;

    private String suspectName2;

    private String suspectName3;

    private String crimeName;

    private String crimeScene;

    private String nationalId;

    private String passportNumber;

    private String drivingLicenseNumber;

    private String mobileNumberCountryCode;

    private String mobileNumber;

    private String suspectKnownAddress;

    private String images;

    private String crimeBrief;

    private String idNumber;

    private String reportingOfficer;

    private String id;

    private String reviewStatus;

    private String initialStatus;

}
