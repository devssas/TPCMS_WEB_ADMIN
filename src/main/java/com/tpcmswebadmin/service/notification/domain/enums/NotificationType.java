package com.tpcmswebadmin.service.notification.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
public enum NotificationType {

    SOS("Sos"),
    NOTIFICATION("Notification");

    @Getter
    private final String title;

}
