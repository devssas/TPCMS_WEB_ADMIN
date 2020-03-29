package com.tpcmswebadmin.webpages.card.delegate;

import com.tpcmswebadmin.infrastructure.domain.LoginUserDo;
import com.tpcmswebadmin.infrastructure.domain.constant.TpCmsConstants;
import com.tpcmswebadmin.service.policevehicles.domain.dto.PoliceVehicleCardDto;
import com.tpcmswebadmin.service.policevehicles.service.PoliceVehicleClientService;
import com.tpcmswebadmin.webpages.card.domain.VehicleCardModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@RequiredArgsConstructor
public class VehicleCardDelegate {

    private final PoliceVehicleClientService policeVehicleClientService;

    public VehicleCardModel getVehicleDetailsByVehicleId(String officerId, HttpServletRequest httpServletRequest) {
        LoginUserDo loginUserDo = makeLoginUser(httpServletRequest);
        PoliceVehicleCardDto policeVehicleCardDto = policeVehicleClientService.getPoliceVehiclesByVehicleId(officerId, loginUserDo);

        return VehicleCardModel.builder()
                .name(policeVehicleCardDto.getName())
                .commandCenter(policeVehicleCardDto.getCommandCenter())
                .vehicleId(policeVehicleCardDto.getVehicleId())
                .unit(policeVehicleCardDto.getUnit())
                .plateNumber(policeVehicleCardDto.getPlateNumber())
                .expiryDate(policeVehicleCardDto.getExpiryDate())
                .weaponType(policeVehicleCardDto.getWeaponType())
                .weaponSrl(policeVehicleCardDto.getWeaponSrl())
                .hasPermissionToCarryWeapon(policeVehicleCardDto.getIsCarryWeapon())
                .hasPermissionToNightPatrol(policeVehicleCardDto.getIsNightPatrol())
                .hasPermissionToCarryCivilians(policeVehicleCardDto.getIsCarryCivilians())
                .hasPermissionToCarryPrisoners(policeVehicleCardDto.getIsCarryPrisoners())
                .hasPermissionToDriverOutsideCity(policeVehicleCardDto.getIsDriverOutsideCity())
                .image(policeVehicleCardDto.getImage())
                .build();
    }

    private LoginUserDo makeLoginUser(HttpServletRequest httpServletRequest) {
        return LoginUserDo.builder()
                .loginOfficersCode((String) httpServletRequest.getSession().getAttribute(TpCmsConstants.OFFICER_CODE))
                .loginOfficerUnitNumber((String) httpServletRequest.getSession().getAttribute(TpCmsConstants.REPORT_UNIT))
                .mobileAppDeviceId((String) httpServletRequest.getSession().getAttribute(TpCmsConstants.MOBILE_APP_DEVICE_ID))
                .build();
    }
}
