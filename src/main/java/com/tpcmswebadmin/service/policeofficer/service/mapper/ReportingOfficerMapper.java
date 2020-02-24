package com.tpcmswebadmin.service.policeofficer.service.mapper;

import com.ssas.tpcms.engine.vo.response.OfficersProfileResponseVO;
import com.tpcmswebadmin.infrastructure.utils.StringUtility;
import com.tpcmswebadmin.service.policeofficer.domain.dto.ReportingOfficerDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReportingOfficerMapper {

    public static List<ReportingOfficerDto> makeReportingOfficerDtoList(OfficersProfileResponseVO[] officersProfileList) {
        return Arrays.stream(officersProfileList)
                .map(ReportingOfficerMapper::makeReportingOfficerDto)
                .collect(Collectors.toList());
    }

    public static ReportingOfficerDto makeReportingOfficerDto(OfficersProfileResponseVO reportingOfficer) {
        return ReportingOfficerDto.builder()
                .officerId(reportingOfficer.getOfficerProfileId())
                .officerName(StringUtility.makeFullName(reportingOfficer.getOfficer_FirstName_Ar(), reportingOfficer.getOfficer_LastName_Ar()))
                .commandCenter(reportingOfficer.getCommandCenterId())
                .build();
    }

}
