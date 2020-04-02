package com.tpcmswebadmin.webpages.card.controller;

import com.tpcmswebadmin.webpages.card.delegate.NotificationCardDelegate;
import com.tpcmswebadmin.webpages.card.domain.NotificationCardModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class NotificationCardController {

    private final NotificationCardDelegate notificationCardDelegate;

    @GetMapping("/card/notification")
    public String getNotificationCard(@RequestParam("notificationId") String notificationId, HttpServletRequest httpServletRequest, Model model) {
        NotificationCardModel notificationCardModel = notificationCardDelegate.getNotificationCard(notificationId, httpServletRequest);

        model.addAttribute("announcementCode", notificationCardModel.getAnnouncementCode());
        model.addAttribute("announcementDesc", notificationCardModel.getAnnouncementDesc());
        model.addAttribute("announcementId", notificationCardModel.getAnnouncementId());
        model.addAttribute("notificationDate", notificationCardModel.getEffectiveDate());
        model.addAttribute("natureOfAnnouncement", notificationCardModel.getNatureOfAnnouncement());
        model.addAttribute("crimeTypeCode", notificationCardModel.getCrimeTypeCode());
        model.addAttribute("crimeTypeLogo1", notificationCardModel.getCrimeTypeLogo1());
        model.addAttribute("attachment", notificationCardModel.getAttachment1());

        return "card_notification";
    }
}
