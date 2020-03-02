package com.tpcmswebadmin.webpages.card.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationCardModel {

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
