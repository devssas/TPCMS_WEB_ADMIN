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
public class PoliceVehicleCardDto implements Serializable {

    private static final long serialVersionUID = 973370746492099180L;

    private String name;

    private String commandCenter;

    private String vehicleId;

    private String unit;

    private String plateNumber;

    private String expiryDate;

    private String weaponType;

    private String weaponSrl;

    private String isCarryWeapon;

    private String isNightPatrol;

    private String isCarryCivilians;

    private String isCarryPrisoners;

    private String isDriverOutsideCity;

    private String image;

}
