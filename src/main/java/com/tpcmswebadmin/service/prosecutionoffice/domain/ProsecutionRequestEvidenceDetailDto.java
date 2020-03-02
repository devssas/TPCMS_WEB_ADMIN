package com.tpcmswebadmin.service.prosecutionoffice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProsecutionRequestEvidenceDetailDto implements Serializable {

    private static final long serialVersionUID = -6451390513285381283L;

    private String caseId;

    private String caseDate;

    private String officerId;

    private String crimeLocation;

    private String status;

    private List<String> suspects;

    private String officerStatement;

    private List<String> images = new ArrayList<>();

    private String crimeType;

    private String crimeTitle;

    private String crimeClassification;

    private String witnessFirstName;

    private String witnessLastName;

    private String witnessStatement;

}
