package com.tpcmswebadmin.service.policevehicles.service;

import com.ssas.tpcms.engine.vo.request.SpecialMissionRequestVO;
import com.ssas.tpcms.engine.vo.request.VehicleDetailsRequestVO;
import com.ssas.tpcms.engine.vo.response.TPEngineResponse;
import com.tpcmswebadmin.infrastructure.client.TPCMSClient;
import com.tpcmswebadmin.infrastructure.domain.LoginUserDo;
import com.tpcmswebadmin.service.credentials.CredentialsService;
import com.tpcmswebadmin.service.credentials.domain.TpCmsWebAdminAppCredentials;
import com.tpcmswebadmin.service.missionpermits.exception.MissionPermitsException;
import com.tpcmswebadmin.service.policevehicles.domain.dto.PoliceVehicleCardDto;
import com.tpcmswebadmin.webpages.policevehicles.model.PoliceVehicleNewModel;
import com.tpcmswebadmin.webpages.policevehicles.model.PoliceVehicleUpdateModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;

@Slf4j
@Service
@RequiredArgsConstructor
public class PoliceVehicleDeleteClientService {

    private final TPCMSClient tpcmsClient;

    private final CredentialsService credentialsService;

    private final PoliceVehicleClientService policeVehicleClientService;

    public TPEngineResponse delete(String vehicleId, LoginUserDo loginUserDo) {
        VehicleDetailsRequestVO vehicleDetailsRequestVO = new VehicleDetailsRequestVO();
        PoliceVehicleCardDto policeVehicleCardDto = policeVehicleClientService.getPoliceVehiclesByVehicleId(vehicleId, loginUserDo);

        setCredentials(vehicleDetailsRequestVO, loginUserDo);
        setFields(policeVehicleCardDto, vehicleDetailsRequestVO);
        vehicleDetailsRequestVO.setVehicleId(vehicleId);
        vehicleDetailsRequestVO.setIsDeleteVehicleDetails("Y");

        log.info("Vehicle to be deleted. {}", vehicleId);
        try {
            return tpcmsClient.tpcmsWebAdminClient().getTPCMSCoreServices().updateVehicleDetails(vehicleDetailsRequestVO);
        } catch (RemoteException | ServiceException e) {
            throw new MissionPermitsException("Something wrong on deleting vehicle request. " + e.getMessage());
        }
    }

    public void setFields(PoliceVehicleCardDto policeVehicleCardDto, VehicleDetailsRequestVO requestVO) {
        requestVO.setActivationDate(policeVehicleCardDto.getActivationDate());
        requestVO.setExpiryDate(policeVehicleCardDto.getExpiryDate());
        requestVO.setAllowedWeaponType1(policeVehicleCardDto.getWeaponType());
        requestVO.setChasisNumber(policeVehicleCardDto.getChaseNumber());
        requestVO.setCommandCenter(policeVehicleCardDto.getCommandCenter());
        requestVO.setPermissionForNightPatrol(policeVehicleCardDto.getIsNightPatrol());
        requestVO.setPermissionToCarryCivilians(policeVehicleCardDto.getIsCarryCivilians());
        requestVO.setPermissionToCarryPrisoners(policeVehicleCardDto.getIsCarryPrisoners());
        requestVO.setPermissionToCarryWeapon(policeVehicleCardDto.getIsCarryWeapon());
        requestVO.setPermissionToDriverOutsideCity(policeVehicleCardDto.getIsDriverOutsideCity());
        requestVO.setPlateNumber(policeVehicleCardDto.getPlateNumber());
        requestVO.setVehicleId(String.valueOf(policeVehicleCardDto.getVehicleId()));
        requestVO.setVehicleDetailsId(policeVehicleCardDto.getVehicleDetailsId());
        requestVO.setVehicleName(policeVehicleCardDto.getName());
        requestVO.setWeaponSerialNumber1(policeVehicleCardDto.getWeaponSrl());
    }

    public void setCredentials(VehicleDetailsRequestVO vehicleDetailsRequestVO, LoginUserDo loginUserDo) {
        TpCmsWebAdminAppCredentials credentials = credentialsService.getCredentialsOfWebAdmin();

        vehicleDetailsRequestVO.setUnitNumber(loginUserDo.getLoginOfficerUnitNumber());
        vehicleDetailsRequestVO.setLoginOfficersCode(loginUserDo.getLoginOfficersCode());
        vehicleDetailsRequestVO.setMobileAppUserName(credentials.getMobileAppUserName());
        vehicleDetailsRequestVO.setMobileAppPassword(credentials.getMobileAppPassword());
        vehicleDetailsRequestVO.setMobileAppSmartSecurityKey(credentials.getMobileAppSmartSecurityKey());
        vehicleDetailsRequestVO.setMobileAppDeviceId(loginUserDo.getMobileAppDeviceId());
    }
}
