package com.tpcmswebadmin.service.notification.controller;

import com.tpcmswebadmin.infrastructure.client.response.ResponseDto;
import com.tpcmswebadmin.service.notification.domain.NotificationDto;
import com.tpcmswebadmin.service.notification.service.NotificationClientService;
import com.tpcmswebadmin.service.policevehicles.domain.PoliceVehicleDto;
import com.tpcmswebadmin.service.policevehicles.service.PoliceVehicleClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("api")
public class NotificationControllerAPI {

    private final NotificationClientService notificationClientService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("notification")
    public ResponseDto<NotificationDto> getNotifications(HttpServletRequest httpServletRequest) {
        return notificationClientService.getResponseDto(httpServletRequest);
    }
}
