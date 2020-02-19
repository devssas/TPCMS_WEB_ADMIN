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
                .actions(prepareActionsColumn(officersProfileResponseVO.getOfficerProfileId()))
                .build();
    }

    public static String prepareActionsColumn(String id) {
        String actionView = "<a href='/tpcmsWebAdmin/viewOfficer?officerId={officerId}' data-fancybox-card data-type='ajax' class='button button-v4 sml-icon-btn color-1' data-src='assets/ajax/card/officer-card.html'><i class='icon-view'></i></a>";
        String actionUpdate = "<a href='/tpcmsWebAdmin/updateOfficer?officerId={officerId}' class='button button-v4 sml-icon-btn color-1'><i class='icon-edit'></i></a>";
        String actionDelete = "<a href='/tpcmsWebAdmin/deleteOfficer?officerId={officerId}' class='button button-v4 sml-icon-btn color-2'><i class='icon-cancel'></i></a>";

        return actionView.replace("{officerId}", id) + actionUpdate.replace("{officerId}", id) + actionDelete.replace("{officerId}", id);
    }
}
