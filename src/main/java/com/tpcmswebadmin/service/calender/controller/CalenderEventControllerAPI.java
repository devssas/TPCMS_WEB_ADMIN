package com.tpcmswebadmin.service.calender.controller;

import com.tpcmswebadmin.infrastructure.client.response.ResponseDto;
import com.tpcmswebadmin.service.calender.domain.CalenderDto;
import com.tpcmswebadmin.service.calender.service.CalenderEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("api")
public class CalenderEventControllerAPI {

    private final CalenderEventService calenderEventService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("calendarEvent")
    public ResponseDto<CalenderDto> getPoliceOfficers(@RequestParam("calendarDate") String date, HttpServletRequest httpServletRequest) {
        return calenderEventService.getResponseDto(date, httpServletRequest);
    }

}
