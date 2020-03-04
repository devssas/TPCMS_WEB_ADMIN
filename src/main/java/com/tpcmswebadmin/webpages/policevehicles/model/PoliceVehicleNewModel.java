package com.tpcmswebadmin.webpages.policevehicles.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PoliceVehicleNewModel {

    private String vehicleId;

    private String vehicleName;

    private String plateNumber;

    private String chaseNumber;

    private String vehicleType;

    private String commandCenter;

    private String unit;

    private String activationDate;

    private String expiryDate;

    private String isPermittedToCarryWeapon;

    private String allowedWeaponType1;

    private String weapon1SerialNumber;

    private String allowedWeaponType2;

    private String weapon2SerialNumber;

    private String allowedWeaponType3;

    private String weapon3SerialNumber;

    private String image;

    private String isPermittedToCarryCivilians;

    private String isPermittedToCarryPrisoners;

    private String isPermittedToNightPatrol;

    private String isPermittedToDriveOutsideCity;

    private String driverOfficerId1;

    private String driverOfficerId2;

    private String additionalRemarks;

}
