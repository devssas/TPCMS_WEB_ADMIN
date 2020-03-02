package com.tpcmswebadmin.service.notification.service.mapper;

import com.ssas.tpcms.engine.vo.response.GeneralAnnouncementResponseVO;
import com.tpcmswebadmin.service.notification.domain.NotificationDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NotificationProsecutorMapper {

    public static List<NotificationDto> makeNotificationDtoList(GeneralAnnouncementResponseVO[] generalAnnouncementResponseArray) {
        return Arrays.stream(generalAnnouncementResponseArray)
                .map(NotificationProsecutorMapper::makeNotificationDto)
                .collect(Collectors.toList());
    }

    public static NotificationDto makeNotificationDto(GeneralAnnouncementResponseVO generalAnnouncementResponseVO) {
        return NotificationDto.builder()
                .permitId(generalAnnouncementResponseVO.getAnnouncementId())
                .username(generalAnnouncementResponseVO.getAnnouncementCode())
                .title(generalAnnouncementResponseVO.getCrimeName_Ar())
                .city(null)
                .state(null)
                .notificationDate(generalAnnouncementResponseVO.getEffectiveDate())
                .priority(generalAnnouncementResponseVO.getNatureOfAnnouncement())
                .actions(prepareActionsColumn(generalAnnouncementResponseVO.getAnnouncementId()))
                .build();
    }

    public static String prepareActionsColumn(String id) {
        String notificationId = "{notificationId}";
        String actionView = "<a href='javascript:;' data-fancybox-card data-type='ajax' class='button button-v4 sml-icon-btn color-1' data-src='/tpcmsWebAdmin/card/notification?notificationId={notificationId}'><i class='icon-view'></i></a>";

        return actionView.replace(notificationId, id);
    }

}
