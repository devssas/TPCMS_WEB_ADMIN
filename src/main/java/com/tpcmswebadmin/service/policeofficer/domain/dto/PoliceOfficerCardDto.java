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
public class PoliceOfficerCardDto implements Serializable {

    private static final long serialVersionUID = 1017384859489205961L;

    private String commandCenter;

    private String officerCode;

    private String officerName;

    private String expiryDate;

    private String unit;

    private String rank;

    private String weaponType;

    private String weaponSrl;

    private String isPermittedCarryWeapon;

    private String bloodGroup;

    private String image;

}
