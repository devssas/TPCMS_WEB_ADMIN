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

    private String vehicleQrCode;

    private String unit;

    private String plateNumber;

    private String chaseNumber;

    private String activationDate;

    private String expiryDate;

    private String weaponType;

    private String weaponSrl;

    private String weaponType2;

    private String weaponSrl2;

    private String weaponType3;

    private String weaponSrl3;

    private String isCarryWeapon;

    private String isNightPatrol;

    private String isCarryCivilians;

    private String isCarryPrisoners;

    private String isDriverOutsideCity;

    private String image;

    private String driverOfficerId1;

    private String driverOfficerId2;

    private String additionalRemarks;

    private String statusCode;

    private String vehicleDetailsId;
}
