package com.tpcmswebadmin.webpages.card.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CrimeReportCardModel {

    private String crimeTitle;

    private String reportId;

    private String status;

    private String crimeScene;

    private String suspects;

    private String reportedDate;

    private List<String> images;

    private String caseBrief;

}
