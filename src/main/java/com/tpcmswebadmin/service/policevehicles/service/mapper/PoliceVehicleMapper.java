package com.tpcmswebadmin.service.policevehicles.service.mapper;

import com.ssas.tpcms.engine.vo.request.ViewVehicleDetailsRequestVO;
import com.ssas.tpcms.engine.vo.response.OfficersProfileResponseVO;
import com.ssas.tpcms.engine.vo.response.VehicleDetailsResponseVO;
import com.tpcmswebadmin.service.policestaff.domain.dto.PoliceStaffDto;
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
                .build();
    }

}
