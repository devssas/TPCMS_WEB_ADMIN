package com.tpcmswebadmin.webpages.authentication.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
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

}
