package com.tpcmswebadmin.service.policestaff.service.mapper;

import com.ssas.tpcms.engine.vo.response.OfficersProfileResponseVO;
import com.tpcmswebadmin.service.policestaff.domain.dto.PoliceStaffDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PoliceStaffMapper {

    public static List<PoliceStaffDto> makePoliceStaffDtoList(OfficersProfileResponseVO[] officersProfileList) {
        return Arrays.stream(officersProfileList)
                .map(PoliceStaffMapper::makePoliceStaffDto)
                .collect(Collectors.toList());
    }

    public static PoliceStaffDto makePoliceStaffDto(OfficersProfileResponseVO officersProfileResponseVO) {
        return PoliceStaffDto.builder()
                .officerId(officersProfileResponseVO.getOfficerProfileId())
                .officerName(officersProfileResponseVO.getOfficer_FirstName_Ar() + " " + officersProfileResponseVO.getOfficer_LastName_Ar())
                .address(officersProfileResponseVO.getContactAddress())
                .city(officersProfileResponseVO.getLivingCity())
                .state(null)
                .status(officersProfileResponseVO.getStatusCode())
                .lastLogin(null)
                .build();
    }

}
