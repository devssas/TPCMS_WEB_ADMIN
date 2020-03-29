package com.tpcmswebadmin.webpages.policevehicles.delegate;

import com.ssas.tpcms.engine.vo.response.TPEngineResponse;
import com.tpcmswebadmin.infrastructure.domain.LoginUserDo;
import com.tpcmswebadmin.infrastructure.domain.constant.TpCmsConstants;
import com.tpcmswebadmin.infrastructure.domain.dto.ResponseMVCDto;
import com.tpcmswebadmin.service.policevehicles.domain.dto.PoliceVehicleCardDto;
import com.tpcmswebadmin.service.policevehicles.service.PoliceVehicleClientService;
import com.tpcmswebadmin.service.policevehicles.service.PoliceVehicleUpdateClientService;
import com.tpcmswebadmin.webpages.policevehicles.model.PoliceVehicleUpdateModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Component
@RequiredArgsConstructor
public class PoliceVehicleUpdateDelegate {

    private final PoliceVehicleUpdateClientService policeVehicleUpdateClientService;

    private final PoliceVehicleClientService policeVehicleClientService;

    public PoliceVehicleUpdateModel getVehicle(String vehicleId, HttpServletRequest httpServletRequest) {
        LoginUserDo loginUserDo = LoginUserDo.makeLoginUser(httpServletRequest);
        PoliceVehicleCardDto policeVehicleCardDto = policeVehicleClientService.getPoliceVehiclesByVehicleId(vehicleId, loginUserDo);

        return PoliceVehicleUpdateModel.builder()
                .id(policeVehicleCardDto.getVehicleId())
                .vehicleName(policeVehicleCardDto.getName())
                .plateNumber(policeVehicleCardDto.getPlateNumber())
                .chaseNumber(policeVehicleCardDto.getChaseNumber())
                .vehicleType(null) //todo
                .commandCenter(policeVehicleCardDto.getCommandCenter())
                .unit(policeVehicleCardDto.getUnit())
                .activationDate(policeVehicleCardDto.getActivationDate())
                .expiryDate(policeVehicleCardDto.getExpiryDate())
                .isPermittedToCarryWeapon(policeVehicleCardDto.getIsCarryWeapon().equals("Y"))
                .allowedWeaponType1(policeVehicleCardDto.getWeaponType())
                .weapon1SerialNumber(policeVehicleCardDto.getWeaponSrl())
                .allowedWeaponType2(policeVehicleCardDto.getWeaponType2())
                .weapon2SerialNumber(policeVehicleCardDto.getWeaponSrl2())
                .allowedWeaponType3(policeVehicleCardDto.getWeaponType3())
                .weapon3SerialNumber(policeVehicleCardDto.getWeaponSrl3())
                .image(policeVehicleCardDto.getImage())
                .isPermittedToCarryCivilians(policeVehicleCardDto.getIsCarryCivilians().equals("Y"))
                .isPermittedToCarryPrisoners(policeVehicleCardDto.getIsCarryCivilians().equals("Y"))
                .isPermittedToNightPatrol(policeVehicleCardDto.getIsCarryCivilians().equals("Y"))
                .isPermittedToDriveOutsideCity(policeVehicleCardDto.getIsCarryCivilians().equals("Y"))
                .driverOfficerId1(policeVehicleCardDto.getDriverOfficerId1())
                .driverOfficerId2(policeVehicleCardDto.getDriverOfficerId1())
                .additionalRemarks(policeVehicleCardDto.getAdditionalRemarks())
                .build();
    }

    public ResponseMVCDto updateVehicle(PoliceVehicleUpdateModel policeVehicleUpdateModel, HttpServletRequest httpServletRequest) {
        LoginUserDo loginUserDo = LoginUserDo.makeLoginUser(httpServletRequest);
        TPEngineResponse response = policeVehicleUpdateClientService.update(policeVehicleUpdateModel, loginUserDo);

        return ResponseMVCDto.prepareResponse(response);
    }

}
