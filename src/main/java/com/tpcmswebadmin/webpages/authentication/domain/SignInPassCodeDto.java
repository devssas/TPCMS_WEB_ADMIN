package com.tpcmswebadmin.webpages.authentication.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class SignInPassCodeDto {

    private boolean hasResult;

    private String officerCode;

    private String reportUnit;

    private String officerName;

    private String profilePicture;

    private String accessRole;

    public SignInPassCodeDto() {
        this.hasResult = false;
    }

    public SignInPassCodeDto(String officerCode, String reportUnit) {
        this.hasResult = false;
        this.officerCode = officerCode;
        this.reportUnit = reportUnit;
    }

    public SignInPassCodeDto(String officerCode, String reportUnit, String officerName, String profilePicture, String accessRole) {
        this.hasResult = false;
        this.officerCode = officerCode;
        this.reportUnit = reportUnit;
        this.officerName = officerName;
        this.profilePicture = profilePicture;
        this.accessRole = accessRole;
    }

}
