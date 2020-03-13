package com.tpcmswebadmin.service.notification.controller;

import com.tpcmswebadmin.infrastructure.client.response.ResponseAPIDto;
import com.tpcmswebadmin.infrastructure.utils.ImageUtility;
import com.tpcmswebadmin.service.notification.domain.NotificationCreateDto;
import com.tpcmswebadmin.service.notification.domain.NotificationDto;
import com.tpcmswebadmin.service.notification.service.NotificationClientService;
import com.tpcmswebadmin.service.notification.service.NotificationProsecutorClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
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

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("image")
    public ResponseEntity<Void> createImage(@RequestParam(name = "imageBase64", required = false) String imageBase64,
                                            @RequestParam(name = "imageName", required = false) String imageName,
                                           // @RequestBody NotificationCreateDto notificationCreateDto,
                                            HttpServletRequest httpServletRequest) {

        log.info(imageBase64);
        log.info(imageName);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("image/delete")
    public ResponseEntity<Void> deleteMapping(@RequestParam(name = "imageName", required = false) String imageName,
                                            HttpServletRequest httpServletRequest) {

        log.info(imageName);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
