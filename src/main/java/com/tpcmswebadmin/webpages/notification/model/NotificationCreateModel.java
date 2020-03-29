package com.tpcmswebadmin.webpages.notification.model;

import com.tpcmswebadmin.service.notification.domain.enums.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationCreateModel {

    @NotNull(message = "{error.notification.notificationType.notNull}")
    private String notificationType;

    private String reportingUnitId;

    private String commandCenter;

    @NotNull(message = "{error.notification.statement.notNull}")
    private String statement;

    @NotNull(message = "{error.notification.natureOfAnnouncement.notNull}")
    private String natureOfAnnouncement;

    private String image;

}
