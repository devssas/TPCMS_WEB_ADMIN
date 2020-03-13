package com.tpcmswebadmin.service.reference.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OfficerRankDto {

    private String additionalRemarks;

    private String officerRankCode;

    private String officerRankDescAr;

    private String officerRankDescEn;

    private String officerRankId;

    private String officerRankNameAr;

    private String officerRankNameEn;

}

