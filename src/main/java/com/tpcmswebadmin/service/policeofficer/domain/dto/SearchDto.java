package com.tpcmswebadmin.service.policeofficer.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchDto {

    private String firstName;

    private String lastName;

    private String criminalsCode;

    private String drivingLicenceNumber;

    private String passportNumber;

    private String nationalId;

}
