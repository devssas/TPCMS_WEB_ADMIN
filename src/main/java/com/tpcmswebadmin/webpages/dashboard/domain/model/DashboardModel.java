package com.tpcmswebadmin.webpages.dashboard.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashboardModel {

    private String transactionId;

    private String officerCode;

    private String reportingUnit;

    private String missionPermitCount;

    private String caseCount;

    private String notificationCount;

    private String sosCount;

}
