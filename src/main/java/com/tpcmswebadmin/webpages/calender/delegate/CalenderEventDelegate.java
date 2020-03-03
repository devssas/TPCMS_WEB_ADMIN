package com.tpcmswebadmin.webpages.calender.delegate;

import com.tpcmswebadmin.service.calender.domain.CalenderDto;
import com.tpcmswebadmin.service.calender.service.CalenderEventService;
import com.tpcmswebadmin.webpages.calender.domain.CalenderEventModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CalenderEventDelegate {

    private final CalenderEventService calenderEventService;

    public List<CalenderEventModel> getEventsOnDate(String date, HttpServletRequest httpServletRequest) {
      //  List<CalenderDto> responseDto = calenderEventService.getCalenderEventsOnDate(date, httpServletRequest);

        return null;
    }

    private static CalenderEventModel makeCalenderEventModel(CalenderDto calenderDto) {
        return CalenderEventModel.builder()
                .toWhom(calenderDto.getToWhom())
                .day(calenderDto.getDay())
                .time(calenderDto.getTime())
                .title(calenderDto.getTitle())
                .actions(calenderDto.getActions())
                .build();
    }
}
