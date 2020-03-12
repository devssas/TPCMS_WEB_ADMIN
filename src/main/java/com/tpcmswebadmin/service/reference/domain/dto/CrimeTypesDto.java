package com.tpcmswebadmin.service.reference.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CrimeTypesDto {

    private String additionalRemarks;

    private String crimeDescAr;

    private String crimeNameAr;

    private String crimeDescEn;

    private String crimeNameEn;

    private String crimeTypeCode;

    private String crimeTypeId;

    private String crimeTypeLogo1;

    private String crimeTypeLogo2;
}

