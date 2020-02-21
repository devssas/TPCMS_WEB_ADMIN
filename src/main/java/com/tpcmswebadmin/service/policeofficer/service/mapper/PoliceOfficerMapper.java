package com.tpcmswebadmin.service.policeofficer.service.mapper;

import com.ssas.tpcms.engine.vo.response.OfficersProfileResponseVO;
import com.tpcmswebadmin.service.policeofficer.domain.dto.PoliceOfficerDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PoliceOfficerMapper {

    public static List<PoliceOfficerDto> makePoliceStaffDtoList(OfficersProfileResponseVO[] officersProfileList) {
        return Arrays.stream(officersProfileList)
                .map(PoliceOfficerMapper::makePoliceStaffDto)
                .collect(Collectors.toList());
    }

    public static PoliceOfficerDto makePoliceStaffDto(OfficersProfileResponseVO officersProfileResponseVO) {
        return PoliceOfficerDto.builder()
                .officerCode(officersProfileResponseVO.getOfficerCode())
                .officerName(officersProfileResponseVO.getOfficer_FirstName_Ar() + " " + officersProfileResponseVO.getOfficer_LastName_Ar())
                .address(officersProfileResponseVO.getContactAddress())
                .city(officersProfileResponseVO.getLivingCity())
                .accessRole(officersProfileResponseVO.getAccessRoleCode())
                .status(officersProfileResponseVO.getStatusCode())
                .lastLogin(null)
                .actions(prepareActionsColumn(officersProfileResponseVO.getOfficerProfileId()))
                .build();
    }

    public static String prepareActionsColumn(String id) {
        String actionView = "<a href='javascript:;' data-fancybox-card data-type='ajax' class='button button-v4 sml-icon-btn color-1' data-src='/tpcmsWebAdmin/card/officer?officerId={officerId}'><i class='icon-view'></i></a>";
        String actionUpdate = "<a href='/tpcmsWebAdmin/updateOfficer?officerId={officerId}' class='button button-v4 sml-icon-btn color-1'><i class='icon-edit'></i></a>";
        String actionDelete = "<a href='/tpcmsWebAdmin/deleteOfficer?officerId={officerId}' class='button button-v4 sml-icon-btn color-2'><i class='icon-cancel'></i></a>";

        return actionView.replace("{officerId}", id) + actionUpdate.replace("{officerId}", id) + actionDelete.replace("{officerId}", id);
    }
}
