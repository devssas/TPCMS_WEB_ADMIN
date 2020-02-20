package com.tpcmswebadmin.service.missionpermits.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MissionCardDto implements Serializable {

    private static final long serialVersionUID = -6958496546910764289L;

    private String officerName;

    private String commandCenter;

    private String rank;

    private String unit;

    private String officerId;

    private String expiryDate;

    private String isPermittedCarryWeapon;

    private String weaponType;

    private String missionType;

    private String missionDescription;

    private String image;

}
