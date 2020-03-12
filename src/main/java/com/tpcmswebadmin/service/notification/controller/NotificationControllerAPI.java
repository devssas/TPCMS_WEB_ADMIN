package com.tpcmswebadmin.service.notification.controller;

import com.tpcmswebadmin.infrastructure.client.response.ResponseAPIDto;
import com.tpcmswebadmin.service.notification.domain.NotificationDto;
import com.tpcmswebadmin.service.notification.service.NotificationClientService;
import com.tpcmswebadmin.service.notification.service.NotificationProsecutorClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/notification")
public class NotificationControllerAPI {

    private final NotificationClientService notificationClientService;

    private final NotificationProsecutorClientService notificationProsecutorClientService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ResponseAPIDto<NotificationDto> getNotifications(HttpServletRequest httpServletRequest) {
        return notificationClientService.getResponseDto(httpServletRequest);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("prosecutor")
    public ResponseAPIDto<NotificationDto> getProsecutorNotifications(HttpServletRequest httpServletRequest) {
        return notificationProsecutorClientService.getResponseDto(httpServletRequest);
    }

}
