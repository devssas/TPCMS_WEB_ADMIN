package com.tpcmswebadmin.webpages.missionpermits.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MissionPermitCardUpdateModel {

    private String missionId;

    private String missionQrCode;

    @NotEmpty(message = "{error.missionPermit.officerId.notNull}")
    private String officerId;

    @NotEmpty(message = "{error.missionPermit.officerName.notNull}")
    private String officerName;

    @NotEmpty(message = "{error.missionPermit.commandCenter.notNull}")
    private String commandCenter;

    private boolean permittedToCarryWeapon;

    private String weaponType;

    private String missionType;

    private String missionDescription;

    private String activationDate;

    private String expiryDate;

    private String additionalRemarks;
}
