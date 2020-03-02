package com.tpcmswebadmin.service.notification.service.mapper;

import com.ssas.tpcms.engine.vo.response.GeneralAnnouncementResponseVO;
import com.tpcmswebadmin.service.notification.domain.NotificationDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NotificationMapper {

    public static List<NotificationDto> makeNotificationDtoList(GeneralAnnouncementResponseVO[] generalAnnouncementResponseArray) {
        return Arrays.stream(generalAnnouncementResponseArray)
                .map(NotificationMapper::makeNotificationDto)
                .collect(Collectors.toList());
    }

    public static NotificationDto makeNotificationDto(GeneralAnnouncementResponseVO generalAnnouncementResponseVO) {
        return NotificationDto.builder()
                .notificationCode(generalAnnouncementResponseVO.getAnnouncementCode())
                .crimeName(generalAnnouncementResponseVO.getCrimeName_Ar())
                .notificationDate(generalAnnouncementResponseVO.getEffectiveDate())
                .natureOfAnnouncement(generalAnnouncementResponseVO.getNatureOfAnnouncement())
                .actions(prepareActionsColumn(generalAnnouncementResponseVO.getAnnouncementId()))
                .build();
    }

    public static String prepareActionsColumn(String id) {
        String notificationId = "{notificationId}";

        String actionView = "<a href='/tpcmsWebAdmin/viewNotification?notificationId={notificationId}' class='button button-v4 sml-icon-btn color-1'><i class='icon-view'></i></a>";
        String actionUpdate = "<a href='/tpcmsWebAdmin/updateNotification?notificationId={notificationId}' class='button button-v4 sml-icon-btn color-1'><i class='icon-edit'></i></a>";
        String actionDelete = "<a href='/tpcmsWebAdmin/updateNotification?notificationId={notificationId}' class='button button-v4 sml-icon-btn color-2'><i class='icon-cancel'></i></a>";

        return actionView.replace(notificationId, id) + actionUpdate.replace(notificationId, id) + actionDelete.replace(notificationId, id);
    }

}
