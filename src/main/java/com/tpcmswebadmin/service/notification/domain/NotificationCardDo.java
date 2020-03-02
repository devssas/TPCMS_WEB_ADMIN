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
public class NotificationCardDo implements Serializable {

    private static final long serialVersionUID = -2897915513813017349L;

    private String announcementCode;

    private String announcementDesc;

    private String announcementId;

    private String attachment1;

    private String crimeNameAr;

    private String crimeTypeCode;

    private String crimeTypeLogo1;

    private String effectiveDate;

    private String natureOfAnnouncement;

}
