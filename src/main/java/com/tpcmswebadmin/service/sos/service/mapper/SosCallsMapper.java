package com.tpcmswebadmin.service.sos.service.mapper;

import com.ssas.tpcms.engine.vo.response.SOSResponseVO;
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
                .requestDate(sosResponseVO.getRequestDate())
                .userId(sosResponseVO.getOfficerProfileId())
                .emergencyLocation(null)
                .phone(null)
                .remarks(sosResponseVO.getAdditionalRemarks())
                .status(sosResponseVO.getStatusCode())
                .actions(prepareActionsColumn(sosResponseVO.getSosRequestId()))
                .build();
    }

    public static String prepareActionsColumn(String id) {
        String actionView = "<a href='/tpcmsWebAdmin/viewSosCall?sosRequestId={sosRequestId}' class='button button-v4 sml-icon-btn color-1'><i class='icon-view'></i></a>";

        return actionView.replace("{sosRequestId}", id);
    }
}
