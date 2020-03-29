package com.tpcmswebadmin.webpages.prosecutionoffice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ManageCrimeFileModel {

    private String caseId;

    private String caseDate;

    private String officerName;

    private String crimeLocation;

    private String status;

    private List<String> suspects = new ArrayList<>();

    private String officerStatement;

    private List<String> images = new ArrayList<>();

    private String crimeType;

    private String crimeTitle;

    private String crimeClassification;

    private String witnessStatementFirstName;

    private String witnessStatementLastName;

    private String witnessStatement;

    private String reviewStatus;

    private String criminalsCode;

}
