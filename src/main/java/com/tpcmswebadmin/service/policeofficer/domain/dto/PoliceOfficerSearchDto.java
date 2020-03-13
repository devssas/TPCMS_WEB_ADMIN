package com.tpcmswebadmin.service.policeofficer.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PoliceOfficerSearchDto implements Serializable {

    private static final long serialVersionUID = -763386545092488800L;

    private String officerId;

    private String officerName;

    private String commandCenter;

}
