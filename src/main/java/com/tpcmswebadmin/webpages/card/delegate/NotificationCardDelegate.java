package com.tpcmswebadmin.webpages.card.delegate;

import com.tpcmswebadmin.service.notification.domain.NotificationCardDo;
import com.tpcmswebadmin.service.notification.service.NotificationCardClientService;
import com.tpcmswebadmin.webpages.card.domain.NotificationCardModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@RequiredArgsConstructor
public class NotificationCardDelegate {

    private final NotificationCardClientService notificationCardClientService;

    public NotificationCardModel getNotificationCard(String notificationCardId, HttpServletRequest httpServletRequest) {
        NotificationCardDo notificationCardDo = notificationCardClientService.getNotificationCardById(notificationCardId, httpServletRequest);

        return NotificationCardModel.builder()
                .announcementCode(notificationCardDo.getAnnouncementCode())
                .announcementDesc(notificationCardDo.getAnnouncementDesc())
                .announcementId(notificationCardDo.getAnnouncementId())
                .attachment1(notificationCardDo.getAttachment1())
                .crimeNameAr(notificationCardDo.getCrimeNameAr())
                .crimeTypeCode(notificationCardDo.getCrimeTypeCode())
                .crimeTypeLogo1(notificationCardDo.getCrimeTypeLogo1())
                .effectiveDate(notificationCardDo.getEffectiveDate())
                .natureOfAnnouncement(notificationCardDo.getNatureOfAnnouncement())
                .build();
    }
}
