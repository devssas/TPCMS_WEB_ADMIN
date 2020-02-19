package com.tpcmswebadmin.webpages.dashboard.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashboardNotificationModel {

    private String notificationCount;

    private String sosCount;

}
