package com.tpcmswebadmin.service.policevehicles.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PoliceVehicleDto implements Serializable {

    private static final long serialVersionUID = -1267620138715050415L;

    private String vehicleId;

    private String type;

    private String plateNumber;

    private String city;

    private String state;

    private String lastLogin;

    private String status;

    private String action;

}
