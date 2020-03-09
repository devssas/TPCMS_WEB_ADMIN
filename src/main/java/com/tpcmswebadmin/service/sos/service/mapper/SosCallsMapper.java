package com.tpcmswebadmin.service.sos.service.mapper;

import com.ssas.tpcms.engine.vo.response.SOSResponseVO;
import com.tpcmswebadmin.infrastructure.utils.DateUtility;
import com.tpcmswebadmin.service.sos.domain.SosCallDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SosCallsMapper {

    public static List<SosCallDto> makeSosCallDtoList(SOSResponseVO[] sosResponseVOSList) {
        return Arrays.stream(sosResponseVOSList)
                .map(SosCallsMapper::makeSosCallDto)
                .collect(Collectors.toList());
    }

    public static SosCallDto makeSosCallDto(SOSResponseVO sosResponseVO) {
        return SosCallDto.builder()
                .requestNumber(sosResponseVO.getSosRequestId())
                .requestDate(DateUtility.convertToFormat(sosResponseVO.getRequestDate(), "dd/MM/YYYY"))
                .userId(sosResponseVO.getOfficerProfileId())
                .emergencyLocation("Lat: " + sosResponseVO.getLatitudeDetails()+ " || Lng: " + sosResponseVO.getLongitudeDetails())
                .phone("(" + sosResponseVO.getContactCountryCode()+ ")-" + sosResponseVO.getContactMobileNumber())
                .status(sosResponseVO.getStatusCode())
                .actions(prepareActionsColumn(sosResponseVO.getSosRequestId()))
                .build();
    }

    public static String prepareActionsColumn(String id) {
        String actionView = "<a href='/tpcmsWebAdmin/sosDetails?sosId={sosId}' class='button button-v4 sml-icon-btn color-1'><i class='icon-view'></i></a>";

        return actionView.replace("{sosId}", id);
    }
}
