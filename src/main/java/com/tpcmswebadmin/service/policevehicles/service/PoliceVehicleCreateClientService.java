package com.tpcmswebadmin.service.policevehicles.service;


import com.ssas.tpcms.engine.vo.request.OfficersProfileRequestVO;
import com.ssas.tpcms.engine.vo.request.SpecialMissionRequestVO;
import com.ssas.tpcms.engine.vo.request.VehicleDetailsRequestVO;
import com.ssas.tpcms.engine.vo.response.TPEngineResponse;
import com.tpcmswebadmin.infrastructure.client.TPCMSClient;
import com.tpcmswebadmin.infrastructure.domain.LoginUserDo;
import com.tpcmswebadmin.infrastructure.service.ClientCreateServiceAPI;
import com.tpcmswebadmin.service.credentials.CredentialsService;
import com.tpcmswebadmin.service.credentials.domain.TpCmsWebAdminAppCredentials;
import com.tpcmswebadmin.service.images.domain.ImageDto;
import com.tpcmswebadmin.service.images.service.ImageService;
import com.tpcmswebadmin.service.missionpermits.exception.MissionPermitsException;
import com.tpcmswebadmin.webpages.policevehicles.model.PoliceVehicleNewModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;

import javax.xml.rpc.ServiceException;
import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PoliceVehicleCreateClientService implements ClientCreateServiceAPI<PoliceVehicleNewModel, VehicleDetailsRequestVO> {

    private final TPCMSClient tpcmsClient;

    private final CredentialsService credentialsService;

    private final ImageService imageService;

    @Override
    public TPEngineResponse create(PoliceVehicleNewModel model, LoginUserDo loginUserDo) {
        VehicleDetailsRequestVO vehicleDetailsRequestVO = new VehicleDetailsRequestVO();
        setFields(model, vehicleDetailsRequestVO);
        setCredentials(vehicleDetailsRequestVO, loginUserDo);

        String pageName = model.getCurrentPageName();
        List<ImageDto> images = getImages(loginUserDo, pageName);
        setImages(vehicleDetailsRequestVO, images);

        log.info("Police vehicle to be created. {}", model.getVehicleId());
        try {
            TPEngineResponse response = tpcmsClient.tpcmsWebAdminClient().getTPCMSCoreServices().createVehicleDetails(vehicleDetailsRequestVO);
            deleteImages(images, pageName, loginUserDo);

            return response;
        } catch (RemoteException | ServiceException e) {
            throw new MissionPermitsException("Something wrong on creating Police vehicle request. " +  e.getMessage());
        }
    }

    private void deleteImages(List<ImageDto> images, String pageName, LoginUserDo loginUserDo) {
        if(!images.isEmpty()) {
            if(images.size() == 1) {
                imageService.delete(images.get(0).getFileName(), pageName, loginUserDo);
            }
            if(images.size() == 2) {
                imageService.delete(images.get(0).getFileName(), pageName, loginUserDo);
                imageService.delete(images.get(1).getFileName(), pageName, loginUserDo);
            }
            if(images.size() == 3) {
                imageService.delete(images.get(0).getFileName(), pageName, loginUserDo);
                imageService.delete(images.get(1).getFileName(), pageName, loginUserDo);
                imageService.delete(images.get(2).getFileName(), pageName, loginUserDo);
            }
        }
    }

    private List<ImageDto> getImages(LoginUserDo loginUserDo, String pageName) {
        String key = imageService.makeKey(loginUserDo);
        return imageService.getByKeyAndPage(key, pageName);
    }

    private void setImages(VehicleDetailsRequestVO vehicleDetailsRequestVO, List<ImageDto> images) {
        if(!images.isEmpty()) {
            if(images.size() == 1) {
                vehicleDetailsRequestVO.setVehiclePhoto1(convertFileContentToBlob(images.get(0).getFileBase64()));
            }
            if(images.size() == 2) {
                vehicleDetailsRequestVO.setVehiclePhoto1(convertFileContentToBlob(images.get(0).getFileBase64()));
                vehicleDetailsRequestVO.setVehiclePhoto2(convertFileContentToBlob(images.get(1).getFileBase64()));
            }
            if(images.size() == 3) {
                vehicleDetailsRequestVO.setVehiclePhoto1(convertFileContentToBlob(images.get(0).getFileBase64()));
                vehicleDetailsRequestVO.setVehiclePhoto2(convertFileContentToBlob(images.get(1).getFileBase64()));
                vehicleDetailsRequestVO.setVehiclePhoto3(convertFileContentToBlob(images.get(2).getFileBase64()));
            }
        }
    }

    public static byte[] convertFileContentToBlob(String filePath) {
        try {
            return Base64.decodeBase64(filePath.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new MissionPermitsException("Something wrong on creating Police vehicle request. " + e.getMessage());
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
        requestVO.setVehicleDetailsId(null); // set by client
        requestVO.setVehicleId(String.valueOf(model.getVehicleId()));
        requestVO.setVehicleName(model.getVehicleName());
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
        requestVO.setUnitNumber(loginUserDo.getLoginOfficerUnitNumber());
    }
}
