package com.tpcmswebadmin.service.missionpermits.service.mapper;

import com.ssas.tpcms.engine.vo.response.GeneralAnnouncementResponseVO;
import com.ssas.tpcms.engine.vo.response.SpecialMissionResponseVO;
import com.tpcmswebadmin.service.missionpermits.domain.MissionPermitsDto;
import com.tpcmswebadmin.service.notification.domain.NotificationDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MissionPermitsMapper {

    public static List<MissionPermitsDto> makeMissionPermitsDtoList(SpecialMissionResponseVO[] specialMissionResponseList) {
        return Arrays.stream(specialMissionResponseList)
                .map(MissionPermitsMapper::makeMissionPermitsDto)
                .collect(Collectors.toList());
    }

    public static MissionPermitsDto makeMissionPermitsDto(SpecialMissionResponseVO specialMissionResponseVO) {
        return MissionPermitsDto.builder()
                .permitId(specialMissionResponseVO.getSpmissionId())
                .username(specialMissionResponseVO.getFirstName_Ar() + " " + specialMissionResponseVO.getLastName_Ar())
                .mobileNumber(specialMissionResponseVO.getSpecialMissionQRCode())
                .city(null)
                .state(null)
                .expiryDate(specialMissionResponseVO.getExpiryDate())
                .status(specialMissionResponseVO.getStatusCode())
                .build();
    }

}
