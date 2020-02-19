package com.tpcmswebadmin.service.administrator.service.mapper;

import com.ssas.tpcms.engine.vo.response.OfficersProfileResponseVO;
import com.tpcmswebadmin.service.administrator.domain.SupervisorDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AdministratorMapper {

    public static List<SupervisorDto> makeSupervisorDtoList(OfficersProfileResponseVO[] officersProfileList) {
        return Arrays.stream(officersProfileList)
                .map(AdministratorMapper::makeSupervisorDto)
                .collect(Collectors.toList());
    }

    public static SupervisorDto makeSupervisorDto(OfficersProfileResponseVO officersProfileResponseVO) {
        return SupervisorDto.builder()
                .adminId(officersProfileResponseVO.getOfficerProfileId())
                .adminName(officersProfileResponseVO.getOfficer_FirstName_Ar() + " " + officersProfileResponseVO.getOfficer_LastName_Ar())
                .address(officersProfileResponseVO.getContactAddress())
                .city(officersProfileResponseVO.getLivingCity())
                .state(null)
                .lastLogin(null)
                .status(officersProfileResponseVO.getStatusCode())
                .actions(prepareActionsColumn(officersProfileResponseVO.getOfficerProfileId()))
                .build();
    }

    public static String prepareActionsColumn(String id) {
        String actionView = "<a href='/tpcmsWebAdmin/viewSupervisor?supervisorId={supervisorId}' class='button button-v4 sml-icon-btn color-1'><i class='icon-view'></i></a>";
        String actionUpdate = "<a href='/tpcmsWebAdmin/updateSupervisor?supervisorId={supervisorId}' class='button button-v4 sml-icon-btn color-1'><i class='icon-edit'></i></a>";
        String actionDelete = "<a href='/tpcmsWebAdmin/deleteSupervisor?supervisorId={supervisorId}' class='button button-v4 sml-icon-btn color-2'><i class='icon-cancel'></i></a>";

        return actionView.replace("{supervisorId}", id) + actionUpdate.replace("{supervisorId}", id) + actionDelete.replace("{supervisorId}", id);
    }
}
