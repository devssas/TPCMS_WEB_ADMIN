package com.tpcmswebadmin.service.prosecutionoffice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProsecutionCasesDto implements Serializable {

    private static final long serialVersionUID = 5703060192503827407L;

    private String caseId;

    private String caseDate;

    private String userId;

    private String location;

    private String crimeType;

    private String status;

    private String actions;
}
