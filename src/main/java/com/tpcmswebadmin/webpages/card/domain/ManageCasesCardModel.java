package com.tpcmswebadmin.webpages.card.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManageCasesCardModel {

    private String image;

    private String crimeType;

    private String criminalName;

    private String caseId;

    private String date;

    private String status;

    private String requestUnit;

    private String caseBrief;

}
