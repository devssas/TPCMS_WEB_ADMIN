package com.tpcmswebadmin.service.notification.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationCreateDto {

    private String notificationType;

    private String statement;

    private String natureOfAnnouncement;

    private String image;
}
