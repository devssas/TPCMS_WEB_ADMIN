package com.tpcmswebadmin.service.superadmin.service.mapper;

import com.ssas.tpcms.engine.vo.response.OfficersProfileResponseVO;
import com.tpcmswebadmin.service.superadmin.domain.SupervisorDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SupervisorMapper {

    public static List<SupervisorDto> makeSupervisorDtoList(OfficersProfileResponseVO[] officersProfileList) {
        return Arrays.stream(officersProfileList)
                .map(SupervisorMapper::makeSupervisorDto)
                .collect(Collectors.toList());
    }

    public static SupervisorDto makeSupervisorDto(OfficersProfileResponseVO officersProfileResponseVO) {
        return SupervisorDto.builder()
                .adminId(officersProfileResponseVO.getOfficerProfileId())
                .adminName(officersProfileResponseVO.getOfficer_FirstName_Ar() + " " + officersProfileResponseVO.getOfficer_LastName_Ar())
                .address(officersProfileResponseVO.getContactAddress())
                .city(officersProfileResponseVO.getLivingCity())
                .accessRole(officersProfileResponseVO.getAccessRoleCode())
                .lastLogin(null)
                .status(officersProfileResponseVO.getStatusCode())
                .actions(prepareActionsColumn(officersProfileResponseVO.getOfficerProfileId()))
                .build();
    }

    public static String prepareActionsColumn(String id) {
        String superVisorId = "{supervisorId}";

        String actionView = "<a href='javascript:;' data-fancybox-card data-type='ajax' class='button button-v4 sml-icon-btn color-1' data-src='/tpcmsWebAdmin/card/supervisor?supervisorId={supervisorId}'><i class='icon-view'></i></a>";
        String actionUpdate = "<a href='/tpcmsWebAdmin/updateSupervisor?supervisorId={supervisorId}' class='button button-v4 sml-icon-btn color-1'><i class='icon-edit'></i></a>";
        String actionDelete = "<a href='/tpcmsWebAdmin/supervisor?supervisorId={supervisorId}' class='button button-v4 sml-icon-btn color-2'><i class='icon-cancel'></i></a>";

        return actionView.replace(superVisorId, id) + actionUpdate.replace(superVisorId, id) + actionDelete.replace(superVisorId, id);
    }
}
