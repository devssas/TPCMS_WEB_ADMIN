package com.tpcmswebadmin.webpages.policevehicles.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PoliceVehicleNewModel {

    private Integer vehicleId;

    @NotEmpty(message = "{error.policeVehicle.vehicleName.notNull}")
    private String vehicleName;

    @NotEmpty(message = "{error.policeVehicle.plateNumber.notNull}")
    private String plateNumber;

    @NotEmpty(message = "{error.policeVehicle.chaseNumber.notNull}")
    private String chaseNumber;

    private String vehicleType;

    @NotEmpty(message = "{error.policeVehicle.commandCenter.notNull}")
    private String commandCenter;

    @NotEmpty(message = "{error.policeVehicle.unit.notNull}")
    private String unit;

    private String activationDate;

    private String expiryDate;

    private boolean isPermittedToCarryWeapon;

    private String allowedWeaponType1;

    private String weapon1SerialNumber;

    private String allowedWeaponType2;

    private String weapon2SerialNumber;

    private String allowedWeaponType3;

    private String weapon3SerialNumber;

    private String image;

    private boolean isPermittedToCarryCivilians;

    private boolean isPermittedToCarryPrisoners;

    private boolean isPermittedToNightPatrol;

    private boolean isPermittedToDriveOutsideCity;

    private String driverOfficerId1;

    private String driverOfficerId2;

    private String additionalRemarks;

    private String currentPageName;

}
