package com.tpcmswebadmin.service.sos.service.mapper;

import com.ssas.tpcms.engine.vo.response.SOSHistoryResponseVO;
import com.ssas.tpcms.engine.vo.response.SOSResponseVO;
import com.tpcmswebadmin.infrastructure.utils.DateUtility;
import com.tpcmswebadmin.infrastructure.utils.StringUtility;
import com.tpcmswebadmin.service.sos.domain.SosCallDetailDto;
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
                .emergencyLocation("Lat: " + sosResponseVO.getLatitudeDetails().substring(0, 9) + " | Lng: " + sosResponseVO.getLongitudeDetails().substring(0, 9))
                .phone(sosResponseVO.getContactMobileNumber() + "-" + sosResponseVO.getContactCountryCode())
                .status(sosResponseVO.getStatusCode())
                .actions(prepareActionsColumn(sosResponseVO.getSosRequestId()))
                .build();
    }

    public static String prepareActionsColumn(String id) {
        String actionView = "<a href='/tpcmsWebAdmin/sosDetails?sosId={sosId}' class='button button-v4 sml-icon-btn color-1'><i class='icon-view'></i></a>";

        return actionView.replace("{sosId}", id);
    }

    public static List<SosCallDetailDto> makeSosCallDetailsDtoList(SOSHistoryResponseVO[] sosHistoryResponseVOS) {
        return Arrays.stream(sosHistoryResponseVOS)
                .map(SosCallsMapper::makeSosCallDetailsDto)
                .collect(Collectors.toList());
    }

    public static SosCallDetailDto makeSosCallDetailsDto(SOSHistoryResponseVO sosResponseVO) {
        return SosCallDetailDto.builder()
                .staffName(StringUtility.makeFullName(sosResponseVO.getRemarksOfficerFirstName_Ar(), sosResponseVO.getRemarksOfficerLastName_Ar()))
                .remarkDate(DateUtility.convertToFormat(sosResponseVO.getRemarksDate(), "dd/MM/YYYY"))
                .remark(sosResponseVO.getAdditionalRemarks())
                .build();
    }
}
