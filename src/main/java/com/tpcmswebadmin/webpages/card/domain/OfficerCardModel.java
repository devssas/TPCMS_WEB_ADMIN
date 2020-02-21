package com.tpcmswebadmin.webpages.card.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfficerCardModel {

    private String commandCenter;

    private String officerId;

    private String officerName;

    private String expiryDate;

    private String unit;

    private String rank;

    private String weaponType;

    private String weaponSrl;

    private String isCarryWeapon;

    private String bloodGroup;

    private String image;

}
