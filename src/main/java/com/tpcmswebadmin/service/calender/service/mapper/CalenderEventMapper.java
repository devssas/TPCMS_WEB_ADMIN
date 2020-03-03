package com.tpcmswebadmin.service.calender.service.mapper;

import com.ssas.tpcms.engine.vo.response.AdminAppointmentResponseVO;
import com.tpcmswebadmin.service.calender.domain.CalenderDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CalenderEventMapper {

    public static CalenderDto makeCalenderEventDto(AdminAppointmentResponseVO appointmentDetailsList){
        return CalenderDto.builder()
                .toWhom(appointmentDetailsList.getAppointmentWhom())
                .day(appointmentDetailsList.getAppointmentDay())
                .time(appointmentDetailsList.getAppointmentTime())
                .title(appointmentDetailsList.getSubjectTitle())
                .actions(prepareActionsColumn(appointmentDetailsList.getAppointmentId()))
                .build();
    }

    public static List<CalenderDto> makeCalenderEventList(AdminAppointmentResponseVO[] appointmentDetailsList){
        return Arrays.stream(appointmentDetailsList)
                .map(CalenderEventMapper::makeCalenderEventDto)
                .collect(Collectors.toList());
    }

    public static String prepareActionsColumn(String id) {
        String actionView = "<a href='/tpcmsWebAdmin/viewCriminal?criminalId={criminalId}' data-fancybox-card data-type='ajax' class='button button-v4 sml-icon-btn color-1' data-src='assets/ajax/card/officer-card.html'><i class='icon-view'></i></a>";

        return actionView.replace("{criminalId}", id);
    }
}
