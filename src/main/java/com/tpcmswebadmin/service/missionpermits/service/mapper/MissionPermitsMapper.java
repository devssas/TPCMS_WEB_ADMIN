package com.tpcmswebadmin.service.missionpermits.service.mapper;

import com.ssas.tpcms.engine.vo.response.SpecialMissionResponseVO;
import com.tpcmswebadmin.infrastructure.domain.constant.TpCmsConstants;
import com.tpcmswebadmin.infrastructure.utils.DateUtility;
import com.tpcmswebadmin.service.missionpermits.domain.MissionPermitsDto;
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
                .missionType(specialMissionResponseVO.getMissionType())
                .missionQrCode(specialMissionResponseVO.getSpecialMissionQRCode())
                .expiryDate(DateUtility.convertToFormat(specialMissionResponseVO.getExpiryDate(), TpCmsConstants.SCREEN_DATE_FORMAT))
                .status(specialMissionResponseVO.getStatusCode())
                .actions(prepareActionsColumn(specialMissionResponseVO.getSpmissionId(), specialMissionResponseVO.getSpecialMissionQRCode(), specialMissionResponseVO.getOfficersProfileId()))
                .build();
    }

    public static String prepareActionsColumn(String missionId, String missionQrCode, String officerId) {
        String missionIdVariable = "{missionId}";

        String actionView = "<a href='javascript:;' data-fancybox-card data-type='ajax' class='button button-v4 sml-icon-btn color-1' data-src='/tpcmsWebAdmin/card/mission?missionId={missionId}&missionQrCode={missionQrCode}'><i class='icon-view'></i></a>";
        String actionUpdate = "<a href='/tpcmsWebAdmin/missionPermitsUpdate?missionId={missionId}&missionQrCode={missionQrCode}' class='button button-v4 sml-icon-btn color-1'><i class='icon-edit'></i></a>";
        String actionDelete = "<a href='/tpcmsWebAdmin/missionPermitsDelete?missionId={missionId}&missionQrCode={missionQrCode}&officerId={officerId}' class='button button-v4 sml-icon-btn color-2'><i class='icon-cancel'></i></a>";

        return actionView.replace(missionIdVariable, missionId).replace("{missionQrCode}", missionQrCode) +
                actionUpdate.replace(missionIdVariable, missionId).replace("{missionQrCode}", missionQrCode) +
                actionDelete.replace(missionIdVariable, missionId).replace("{missionQrCode}", missionQrCode).replace("{officerId}", officerId);
    }

}
