package com.tpcmswebadmin.webpages.notification.model;

import com.tpcmswebadmin.service.notification.domain.enums.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationCreateModel {

    private NotificationType notificationType;

    private String reportingUnitId;

    private String commandCenter;

    private String statement;

    private String natureOfAnnouncement;

}
