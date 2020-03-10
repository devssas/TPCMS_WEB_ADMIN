package com.tpcmswebadmin.service.reference.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NatureOfAnnouncementDto {

    private String natureOfAnnouncement;

    private String natureOfAnnouncementCode;

    private String natureOfAnnouncementDescription;

    private String natureOfAnnouncementId;

}
