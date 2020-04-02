package com.tpcmswebadmin.service.policeofficer.service;

import com.ssas.tpcms.engine.vo.request.OfficersProfileRequestVO;
import com.ssas.tpcms.engine.vo.response.TPEngineResponse;
import com.tpcmswebadmin.infrastructure.client.TPCMSClient;
import com.tpcmswebadmin.infrastructure.domain.LoginUserDo;
import com.tpcmswebadmin.infrastructure.service.ClientCreateServiceAPI;
import com.tpcmswebadmin.service.credentials.CredentialsService;
import com.tpcmswebadmin.service.credentials.domain.TpCmsWebAdminAppCredentials;
import com.tpcmswebadmin.service.images.domain.ImageDto;
import com.tpcmswebadmin.service.images.service.ImageService;
import com.tpcmswebadmin.service.missionpermits.exception.MissionPermitsException;
import com.tpcmswebadmin.webpages.policeofficer.model.PoliceOfficerCreateModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;

import javax.xml.rpc.ServiceException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PoliceOfficerCreateClientService implements ClientCreateServiceAPI<PoliceOfficerCreateModel, OfficersProfileRequestVO> {

    private final TPCMSClient tpcmsClient;

    private final CredentialsService credentialsService;

    private final ImageService imageService;

    @Override
    public TPEngineResponse create(PoliceOfficerCreateModel model, LoginUserDo loginUserDo) {
        OfficersProfileRequestVO officersProfileRequestVO = new OfficersProfileRequestVO();
        setFields(model, officersProfileRequestVO);
        setCredentials(officersProfileRequestVO, loginUserDo);

        String pageName = model.getCurrentPageName();
        List<ImageDto> images = getImages(loginUserDo, pageName);
        setImages(officersProfileRequestVO, images);

        log.info("Officer to be created. {} {}", model.getFirstName(), model.getLastName());

        try {
            TPEngineResponse response = tpcmsClient.tpcmsWebAdminClient().getTPCMSCoreServices().createOfficersProfile(officersProfileRequestVO);

            imageService.delete(images.get(0).getFileName(), pageName, loginUserDo);

            return response;
        } catch (RemoteException | ServiceException e) {
            throw new MissionPermitsException("Something wrong on creating Police Officer request. " + e.getMessage());
        }
    }

    private List<ImageDto> getImages(LoginUserDo loginUserDo, String pageName) {
        String key = imageService.makeKey(loginUserDo);
        return imageService.getByKeyAndPage(key, pageName);
    }

    private void setImages(OfficersProfileRequestVO officersProfileRequestVO, List<ImageDto> images) {
        officersProfileRequestVO.setProfilePhoto1(convertFileContentToBlob(images.get(0).getFileBase64()));
    }

    public static byte[] convertFileContentToBlob(String filePath) {
        try {
            return Base64.decodeBase64(filePath.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new MissionPermitsException("Something wrong on creating Police Officer request. " + e.getMessage());
        }
    }

    @Override
    public void setFields(PoliceOfficerCreateModel model, OfficersProfileRequestVO request) {
        request.setNationalIdNumber(model.getNationalId());
        request.setFirstName_Ar(model.getFirstName());
        request.setFirstName_En(model.getFirstName());
        request.setLastName_Ar(model.getLastName());
        request.setLastName_En(model.getLastName());
        request.setMiddleName_Ar(model.getMiddleName());
        request.setMiddleName_En(model.getMiddleName());
        request.setDateOfBirth(model.getDateOfBirth());
        request.setGender(model.isGender() ? "M" : "F");
        request.setPassportNumber(model.getPassportNumber());
        request.setMobileNumberCountryCode(model.getCountryCode());
        request.setMobileNumber(model.getMobileNumber());
        request.setContactEmailAddress(model.getEmail());
        request.setPermissionToCarryWeapon(model.isPermittedToCarryWeapon() ? "Y" : "N");
        request.setAllowedWeaponType(model.getWeaponType());
        request.setWeaponSerialNumber(model.getSerialNumber());
        request.setStatusCode(model.getStatus());
        request.setContactAddress(model.getContactAddress());
        request.setLivingCity(model.getCity());
        request.setAdminUserName(null); //todo
        request.setUserCode(model.getUserCode());
        request.setPassCode(model.getPassCode());
        request.setReportingOfficerId(model.getReportingOfficer());
        request.setOfficersGrade(model.getOfficerGrade());
        request.setOfficersRank(model.getOfficerRank());
        request.setReportingUnit(model.getReportingUnit());
        request.setAccessRoleCode(model.getAccessRole());
        request.setOfficersIdNumber(model.getOfficerId());
        request.setEmploymentStartDate(model.getEmploymentStartDate());
        request.setExpiryDate(model.getExpiryDate());
        request.setNextOfKin(model.getNextOfKin());
        request.setEmergencyContactPerson(model.getEmergencyContactPerson());
        request.setRelationshipWithContactPerson(model.getRelationshipWithContactPerson());
        request.setEmergencyContactNumber1(model.getEmergencyContactNumber1());
        request.setEmergencyContactNumber2(model.getEmergencyContactNumber2());
        request.setBloogroup(model.getBloodGroup());
        request.setVisualIdentificationMark(model.getVisualIdentificationMark());
        request.setReportingOfficerId(model.getReportingOfficer());
    }

    @Override
    public void setCredentials(OfficersProfileRequestVO request, LoginUserDo loginUserDo) {
        TpCmsWebAdminAppCredentials credentials = credentialsService.getCredentialsOfWebAdmin();

        request.setLoginOfficerCode(loginUserDo.getLoginOfficersCode());
        request.setMobileAppUserName(credentials.getMobileAppUserName());
        request.setMobileAppPassword(credentials.getMobileAppPassword());
        request.setMobileAppSmartSecurityKey(credentials.getMobileAppSmartSecurityKey());
        request.setMobileAppDeviceId(loginUserDo.getMobileAppDeviceId());
        request.setLoginOfficerUnitNumber(loginUserDo.getLoginOfficerUnitNumber());
    }
}
