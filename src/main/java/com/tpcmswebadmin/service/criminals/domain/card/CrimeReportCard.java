package com.tpcmswebadmin.service.criminals.domain.card;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CrimeReportCard implements Serializable {

    private static final long serialVersionUID = 5741606045804495559L;

    private String crimeTitle;

    private String reportId;

    private String status;

    private String crimeScene;

    private String suspects;

    private String reportedDate;

    private List<String> images;

    private String caseBrief;

}
