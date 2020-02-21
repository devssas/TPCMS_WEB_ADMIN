package com.tpcmswebadmin.webpages.card.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupervisorCardModel {

    private String officerName;

    private String commandCenter;

    private String rank;

    private String unit;

    private String officerId;

    private String expiryDate;

    private String image;

}
