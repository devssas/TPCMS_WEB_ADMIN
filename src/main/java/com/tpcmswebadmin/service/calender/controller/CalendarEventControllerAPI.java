package com.tpcmswebadmin.service.calender.controller;

import com.tpcmswebadmin.infrastructure.client.response.ResponseDto;
import com.tpcmswebadmin.service.calender.domain.CalendarDto;
import com.tpcmswebadmin.service.calender.service.CalendarEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("api")
public class CalendarEventControllerAPI {

    private final CalendarEventService calendarEventService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("calendarEvent")
    public ResponseDto<CalendarDto> getPoliceOfficers(@RequestParam("calendarDate") String date, HttpServletRequest httpServletRequest) {
        return calendarEventService.getResponseDto(date, httpServletRequest);
    }

}
