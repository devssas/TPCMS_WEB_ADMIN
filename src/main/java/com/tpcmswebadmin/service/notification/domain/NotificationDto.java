package com.tpcmswebadmin.service.notification.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDto implements Serializable {

    private static final long serialVersionUID = -4050116841101518838L;

    private String notificationCode;

    private String crimeName;

    private String notificationDate;

    private String natureOfAnnouncement;

    private String actions;
}
