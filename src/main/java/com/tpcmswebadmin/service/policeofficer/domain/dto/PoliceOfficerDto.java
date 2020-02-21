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
public class PoliceOfficerDto implements Serializable {

    private static final long serialVersionUID = -8299257537137496834L;

    private String officerCode;

    private String officerName;

    private String address;

    private String city;

    private String accessRole;

    private String lastLogin;

    private String status;

    private String actions;

}
