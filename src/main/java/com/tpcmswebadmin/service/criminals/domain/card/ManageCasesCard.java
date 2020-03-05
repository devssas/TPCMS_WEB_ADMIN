package com.tpcmswebadmin.service.criminals.domain.card;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ManageCasesCard implements Serializable {

    private static final long serialVersionUID = 1161309937197648315L;

    private String images;

    private String crimeType;

    private String criminalName;

    private String caseId;

    private String date;

    private String status;

    private String requestUnit;

    private String caseBrief;
}
