package com.tpcmswebadmin.webpages.card.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleCardModel {

    private String name;

    private String commandCenter;

    private String vehicleId;

    private String unit;

    private String plateNumber;

    private String expiryDate;

    private String weaponType;

    private String weaponSrl;

    private String hasPermissionToCarryWeapon;

    private String hasPermissionToNightPatrol;

    private String hasPermissionToCarryCivilians;

    private String hasPermissionToCarryPrisoners;

    private String hasPermissionToDriverOutsideCity;

    private String image;

}
