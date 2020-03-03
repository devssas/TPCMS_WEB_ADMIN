package com.tpcmswebadmin.webpages.calender.delegate;

import com.tpcmswebadmin.service.calender.domain.CalendarDto;
import com.tpcmswebadmin.service.calender.service.CalendarEventService;
import com.tpcmswebadmin.webpages.calender.domain.CalenderEventModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CalenderEventDelegate {

    private final CalendarEventService calendarEventService;

    public List<CalenderEventModel> getEventsOnDate(String date, HttpServletRequest httpServletRequest) {
      //  List<CalenderDto> responseDto = calenderEventService.getCalenderEventsOnDate(date, httpServletRequest);

        return null;
    }

    private static CalenderEventModel makeCalenderEventModel(CalendarDto calendarDto) {
        return CalenderEventModel.builder()
                .toWhom(calendarDto.getToWhom())
                .day(calendarDto.getDay())
                .time(calendarDto.getTime())
                .title(calendarDto.getTitle())
                .actions(calendarDto.getActions())
                .build();
    }
}
