package com.tpcmswebadmin.service.policevehicles.service.mapper;

import com.ssas.tpcms.engine.vo.response.VehicleDetailsResponseVO;
import com.tpcmswebadmin.service.policevehicles.domain.PoliceVehicleDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PoliceVehicleMapper {

    public static List<PoliceVehicleDto> makePoliceVehicleDtoList(VehicleDetailsResponseVO[] vehicleDetailsResponseList) {
        return Arrays.stream(vehicleDetailsResponseList)
                .map(PoliceVehicleMapper::makePoliceVehicleDto)
                .collect(Collectors.toList());
    }

    public static PoliceVehicleDto makePoliceVehicleDto(VehicleDetailsResponseVO vehicleDetailsResponse) {
        return PoliceVehicleDto.builder()
                .vehicleId(vehicleDetailsResponse.getVehicleId())
                .type(vehicleDetailsResponse.getAllowedWeaponType1())
                .plateNumber(vehicleDetailsResponse.getPlateNumber())
                .city(vehicleDetailsResponse.getCommandCenter())
                .state(null)
                .status(vehicleDetailsResponse.getStatusCode())
                .lastLogin(null)
                .action(prepareActionsColumn(vehicleDetailsResponse.getVehicleId()))
                .build();
    }

    public static String prepareActionsColumn(String id) {
        String actionView = "<a href='/tpcmsWebAdmin/viewVehicle?vehicleId={vehicleId}' data-fancybox-card data-type='ajax' class='button button-v4 sml-icon-btn color-1' data-src='assets/ajax/card/officer-card.html'><i class='icon-view'></i></a>";
        String actionUpdate = "<a href='/tpcmsWebAdmin/updateVehicle?vehicleId={vehicleId}' class='button button-v4 sml-icon-btn color-1'><i class='icon-edit'></i></a>";
        String actionDelete = "<a href='/tpcmsWebAdmin/deleteVehicle?vehicleId={vehicleId}'class='button button-v4 sml-icon-btn color-2'><i class='icon-cancel'></i></a>";

        return actionView.replace("{vehicleId}", id) + actionUpdate.replace("{vehicleId}", id) + actionDelete.replace("{vehicleId}", id);
    }

}
