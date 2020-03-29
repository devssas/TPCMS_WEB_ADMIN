package com.tpcmswebadmin.service.policevehicles.service;

import com.ssas.tpcms.engine.vo.request.VehicleDetailsRequestVO;
import com.ssas.tpcms.engine.vo.response.TPEngineResponse;
import com.tpcmswebadmin.infrastructure.client.TPCMSClient;
import com.tpcmswebadmin.infrastructure.domain.LoginUserDo;
import com.tpcmswebadmin.service.credentials.CredentialsService;
import com.tpcmswebadmin.service.credentials.domain.TpCmsWebAdminAppCredentials;
import com.tpcmswebadmin.service.missionpermits.exception.MissionPermitsException;
import com.tpcmswebadmin.service.policevehicles.domain.dto.PoliceVehicleCardDto;
import com.tpcmswebadmin.webpages.policevehicles.model.PoliceVehicleUpdateModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;

@Slf4j
@Service
@RequiredArgsConstructor
public class PoliceVehicleUpdateClientService {

    private final TPCMSClient tpcmsClient;

    private final CredentialsService credentialsService;

    private final PoliceVehicleClientService policeVehicleClientService;

    public TPEngineResponse update(PoliceVehicleUpdateModel model, LoginUserDo loginUserDo) {
        PoliceVehicleCardDto policeVehicleCardDto = policeVehicleClientService.getPoliceVehiclesByVehicleId(model.getId(), loginUserDo);

        VehicleDetailsRequestVO vehicleDetailsRequestVO = new VehicleDetailsRequestVO();
        setCredentials(vehicleDetailsRequestVO, loginUserDo);
        setFields(model, vehicleDetailsRequestVO);
        vehicleDetailsRequestVO.setVehicleId(model.getId());
        vehicleDetailsRequestVO.setVehicleDetailsId(policeVehicleCardDto.getVehicleDetailsId());

        log.info("Special Mission to be updated. {}", model.getId());
        try {
            return tpcmsClient.tpcmsWebAdminClient().getTPCMSCoreServices().updateVehicleDetails(vehicleDetailsRequestVO);
        } catch (RemoteException | ServiceException e) {
            throw new MissionPermitsException("Something wrong on updating Special Mission request. " + e.getMessage());
        }
    }

    public void setFields(PoliceVehicleUpdateModel model, VehicleDetailsRequestVO requestVO) {
        requestVO.setActivationDate(model.getActivationDate());
        requestVO.setAdditionalRemarks(model.getAdditionalRemarks());
        requestVO.setAllowedWeaponType1(model.getAllowedWeaponType1());
        requestVO.setAllowedWeaponType2(model.getAllowedWeaponType2());
        requestVO.setAllowedWeaponType3(model.getAllowedWeaponType3());
        requestVO.setChasisNumber(model.getChaseNumber());
        requestVO.setCommandCenter(model.getCommandCenter());
        requestVO.setDriverOfficerId_1(model.getDriverOfficerId1());
        requestVO.setDriverOfficerId_2(model.getDriverOfficerId2());
        requestVO.setExpiryDate(model.getExpiryDate());
        requestVO.setIsDeleteVehicleDetails(null);
        requestVO.setMissionDescription(model.getActivationDate());
        requestVO.setMissionType(model.getVehicleType());
        requestVO.setOtherAttachments(null);
        requestVO.setOtherNotes(model.getActivationDate());
        requestVO.setPermissionForNightPatrol(model.getActivationDate());
        requestVO.setPermissionToCarryCivilians(model.getActivationDate());
        requestVO.setPermissionToCarryPrisoners(model.getActivationDate());
        requestVO.setPermissionToCarryWeapon(model.getAllowedWeaponType1());
        requestVO.setPermissionToDriverOutsideCity(model.isPermittedToDriveOutsideCity() ? "Y" : "N");
        requestVO.setPlateNumber(model.getPlateNumber());
        requestVO.setStatusCode(null); //todo no status on screen
        requestVO.setStatusId(null);
        requestVO.setVehicleDetailsId(null); //todo id
        requestVO.setVehicleName(model.getVehicleName());
        requestVO.setVehiclePhoto1(null); //todo later
        requestVO.setVehiclePhoto2(null); //todo later
        requestVO.setVehiclePhoto3(null); //todo later
        requestVO.setVehicleQRCode(null); //todo later
        requestVO.setWeaponSerialNumber1(model.getWeapon1SerialNumber());
        requestVO.setWeaponSerialNumber2(model.getWeapon2SerialNumber());
        requestVO.setWeaponSerialNumber3(model.getWeapon3SerialNumber());
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
