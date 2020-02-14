package com.tpcmswebadmin.service.policestaff.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PoliceStaffDto implements Serializable {

    private static final long serialVersionUID = -8299257537137496834L;

    private String officerId;

    private String officerName;

    private String address;

    private String city;

    private String state;

    private String lastLogin;

    private String status;

    private String actions;

}
