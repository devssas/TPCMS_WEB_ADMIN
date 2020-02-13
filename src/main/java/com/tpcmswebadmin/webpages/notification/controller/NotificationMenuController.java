package com.tpcmswebadmin.webpages.notification.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NotificationMenuController {

    @GetMapping("/notification")
    public String getNotifications() {

        return "notification_menu";
    }

}
