package com.tpcmswebadmin.service.notification.service.mapper;

import com.ssas.tpcms.engine.vo.response.GeneralAnnouncementResponseVO;
import com.ssas.tpcms.engine.vo.response.VehicleDetailsResponseVO;
import com.tpcmswebadmin.service.notification.domain.NotificationDto;
import com.tpcmswebadmin.service.policevehicles.domain.PoliceVehicleDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NotificationMapper {

    public static List<NotificationDto> makeNotificationDtoList(GeneralAnnouncementResponseVO[] generalAnnouncementResponseArray) {
        return Arrays.stream(generalAnnouncementResponseArray)
                .map(NotificationMapper::makePoliceVehicleDto)
                .collect(Collectors.toList());
    }

    public static NotificationDto makePoliceVehicleDto(GeneralAnnouncementResponseVO generalAnnouncementResponseVO) {
        return NotificationDto.builder()
                .permitId(generalAnnouncementResponseVO.getAnnouncementId())
                .username(generalAnnouncementResponseVO.getAnnouncementCode())
                .title(generalAnnouncementResponseVO.getCrimeName_Ar())
                .city(null)
                .state(null)
                .notificationDate(generalAnnouncementResponseVO.getEffectiveDate())
                .priority(generalAnnouncementResponseVO.getNatureOfAnnouncement())
                .build();
    }

}
