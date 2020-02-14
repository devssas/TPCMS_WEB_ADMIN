package com.tpcmswebadmin.service.criminals.service.mapper;

import com.ssas.tpcms.engine.vo.response.CrimeReportResponseVO;
import com.ssas.tpcms.engine.vo.response.OfficersProfileResponseVO;
import com.tpcmswebadmin.service.criminals.domain.CrimeReportDto;
import com.tpcmswebadmin.service.policestaff.domain.dto.PoliceStaffDto;
import com.tpcmswebadmin.service.policestaff.service.mapper.PoliceStaffMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CrimeReportsMapper {

    public static List<CrimeReportDto> makeCrimeReportDtoList(CrimeReportResponseVO[] crimeReportResponseVOS) {
        return Arrays.stream(crimeReportResponseVOS)
                .map(CrimeReportsMapper::makeCrimeReportDto)
                .collect(Collectors.toList());
    }

    public static CrimeReportDto makeCrimeReportDto(CrimeReportResponseVO crimeReportResponseVOS) {
        return CrimeReportDto.builder()
                .nationalId(crimeReportResponseVOS.getNationalIdNumber())
                .criminalName(crimeReportResponseVOS.getReportingFirstName_Ar() + " " + crimeReportResponseVOS.getReportingLastName_Ar())
                .address(crimeReportResponseVOS.getContactAddress())
                .city(crimeReportResponseVOS.getCityName())
                .state(null)
                .wantedBy(null)
                .status(crimeReportResponseVOS.getCrimianlStatusCode())
                .build();
    }


}
