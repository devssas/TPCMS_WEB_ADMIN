package com.tpcmswebadmin.service.reference.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OfficerGradeDto {

    private String additionalRemarks;

    private String officerGradeCode;

    private String officerGradeDescAr;

    private String officerGradeDescEn;

    private String officerGradeId;

    private String officerGradeNameAr;

    private String officerGradeNameEn;

}
