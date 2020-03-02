package com.tpcmswebadmin.webpages.dashboard.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashboardProsecutorModel {

    private String transactionId;

    private String officerCode;

    private String reportingUnit;

    private String totalEvidenceCount;

    private String notificationCount;

}
