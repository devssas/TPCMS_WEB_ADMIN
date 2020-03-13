package com.tpcmswebadmin.service.policevehicles.service;


import com.ssas.tpcms.engine.vo.request.SpecialMissionRequestVO;
import com.ssas.tpcms.engine.vo.request.VehicleDetailsRequestVO;
import com.ssas.tpcms.engine.vo.response.TPEngineResponse;
import com.tpcmswebadmin.infrastructure.client.TPCMSClient;
import com.tpcmswebadmin.infrastructure.domain.LoginUserDo;
import com.tpcmswebadmin.infrastructure.service.ClientCreateServiceAPI;
import com.tpcmswebadmin.service.credentials.CredentialsService;
import com.tpcmswebadmin.service.credentials.domain.TpCmsWebAdminAppCredentials;
import com.tpcmswebadmin.service.missionpermits.exception.MissionPermitsException;
import com.tpcmswebadmin.webpages.policevehicles.model.PoliceVehicleNewModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;

@Slf4j
@Service
@RequiredArgsConstructor
public class PoliceVehicleCreateClientService implements ClientCreateServiceAPI<PoliceVehicleNewModel, VehicleDetailsRequestVO> {

    private final TPCMSClient tpcmsClient;

    private final CredentialsService credentialsService;

    @Override
    public TPEngineResponse create(PoliceVehicleNewModel model, LoginUserDo loginUserDo) {
        VehicleDetailsRequestVO vehicleDetailsRequestVO = new VehicleDetailsRequestVO();
        setFields(model, vehicleDetailsRequestVO);
        setCredentials(vehicleDetailsRequestVO, loginUserDo);

        try {
            return tpcmsClient.tpcmsWebAdminClient().getTPCMSCoreServices().createVehicleDetails(vehicleDetailsRequestVO);
        } catch (RemoteException | ServiceException e) {
            throw new MissionPermitsException("Something wrong on creating Special Mission request. " + vehicleDetailsRequestVO.getMobileAppUserName());
        }
    }

    @Override
    public void setFields(PoliceVehicleNewModel model, VehicleDetailsRequestVO requestVO) {
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
        requestVO.setIsDeleteVehicleDetails(model.getVehicleName());
        requestVO.setLoginOfficersCode(model.getActivationDate());
        requestVO.setMissionDescription(model.getActivationDate());
        requestVO.setMissionType(model.getVehicleType());
        requestVO.setMobileAppDeviceId(model.getDriverOfficerId1());
        requestVO.setMobileAppPassword(model.getActivationDate());
        requestVO.setMobileAppSmartSecurityKey(model.getActivationDate());
        requestVO.setMobileAppUserName(model.getVehicleName());
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
        requestVO.setUnitNumber(model.getUnit());
        requestVO.setVehicleDetailsId(null); //todo id
        requestVO.setVehicleId(String.valueOf(model.getVehicleId()));
        requestVO.setVehicleName(model.getVehicleName());
        requestVO.setVehiclePhoto1(null); //todo later
        requestVO.setVehiclePhoto2(null); //todo later
        requestVO.setVehiclePhoto3(null); //todo later
        requestVO.setVehicleQRCode(null); //todo later
        requestVO.setWeaponSerialNumber1(model.getWeapon1SerialNumber());
        requestVO.setWeaponSerialNumber2(model.getWeapon2SerialNumber());
        requestVO.setWeaponSerialNumber3(model.getWeapon3SerialNumber());
    }

    @Override
    public void setCredentials(VehicleDetailsRequestVO requestVO, LoginUserDo loginUserDo) {
        TpCmsWebAdminAppCredentials credentials = credentialsService.getCredentialsOfWebAdmin();

        requestVO.setMobileAppUserName(credentials.getMobileAppUserName());
        requestVO.setMobileAppPassword(credentials.getMobileAppPassword());
        requestVO.setMobileAppSmartSecurityKey(credentials.getMobileAppSmartSecurityKey());
        requestVO.setMobileAppDeviceId(loginUserDo.getMobileAppDeviceId());
        requestVO.setLoginOfficersCode(loginUserDo.getLoginOfficersCode());
    }
}
