package com.tpcmswebadmin.webpages.superadmin.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SupervisorUpdateModel {

    @JsonIgnore
    private String id;

    private String nationalId;

    private String firstName;

    private String middleName;

    private String lastName;

    private String dateOfBirth;

    private boolean gender;

    private String passportNumber;

    private String countryCode;

    private String mobileNumber;

    private String email;

    private String userName;

    private String userCode;

    private String passCode;

    private String reportingOfficer;

    private boolean permissionToWebAccess;

    private String officerId;

    private String location;

    private String city;

    private String state;

    private String zip;

    private String commandCenterId;

    private String commandCenter;

    private String locationIconColor;

    private String supervisorType;

    private String supervisorUnit;

    private String supervisorOfficerGrade;

    private String supervisorOfficerRank;

    private String nextOfKin;

    private String emergencyContactPerson;

    private String relationshipWithContactPerson;

    private String emergencyContactCountryCode;

    private String emergencyContactNumber;

    private String bloodGroup;
}
