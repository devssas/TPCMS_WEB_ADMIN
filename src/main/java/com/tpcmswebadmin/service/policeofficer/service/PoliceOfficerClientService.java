package com.tpcmswebadmin.service.policeofficer.service;

import com.ssas.tpcms.engine.vo.request.ViewOfficersProfileRequestVO;
import com.ssas.tpcms.engine.vo.request.ViewVehicleDetailsRequestVO;
import com.ssas.tpcms.engine.vo.response.TPEngineResponse;
import com.tpcmswebadmin.infrastructure.client.TPCMSClient;
import com.tpcmswebadmin.infrastructure.client.response.DataDto;
import com.tpcmswebadmin.infrastructure.client.response.ResponseAPIDto;
import com.tpcmswebadmin.infrastructure.domain.LoginUserDo;
import com.tpcmswebadmin.infrastructure.service.ClientServiceAPI;
import com.tpcmswebadmin.infrastructure.utils.ImageUtility;
import com.tpcmswebadmin.service.credentials.CredentialsService;
import com.tpcmswebadmin.service.credentials.domain.TpCmsWebAdminAppCredentials;
import com.tpcmswebadmin.service.policeofficer.domain.dto.PoliceOfficerCardDto;
import com.tpcmswebadmin.service.policeofficer.domain.dto.PoliceOfficerDto;
import com.tpcmswebadmin.service.policeofficer.service.mapper.PoliceOfficerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class PoliceOfficerClientService implements ClientServiceAPI<PoliceOfficerDto, LoginUserDo, ViewOfficersProfileRequestVO> {

    private final TPCMSClient tpcmsClient;

    private final CredentialsService credentialsService;

    public PoliceOfficerCardDto getPoliceOfficerByOfficerId(String officerId, LoginUserDo loginUserDo) {
        ViewOfficersProfileRequestVO viewOfficersProfileRequestVO = new ViewOfficersProfileRequestVO();
        viewOfficersProfileRequestVO.setOfficerProfileId(officerId);

        setFullCredentials(viewOfficersProfileRequestVO, loginUserDo);

        try {
            log.info("Get Police officer request will be sent to client. {}", viewOfficersProfileRequestVO.getMobileAppUserName());

            return preparePoliceOfficerDto(tpcmsClient.tpcmsWebAdminClient().getTPCMSCoreServices().getOfficersProfileList(viewOfficersProfileRequestVO));
        } catch (RemoteException | ServiceException e) {
            log.warn("Something wrong on  request. " + e.getMessage());
        }
        return null;
    }

    private PoliceOfficerCardDto preparePoliceOfficerDto(TPEngineResponse tpEngineResponse) {
        return PoliceOfficerCardDto.builder()
                .accessRole(tpEngineResponse.getOfficersProfileList()[0].getAccessRoleCode())
                .commandCenter(tpEngineResponse.getOfficersProfileList()[0].getContactAddress())
                .expiryDate(tpEngineResponse.getOfficersProfileList()[0].getExpiryDate())
                .unit(tpEngineResponse.getOfficersProfileList()[0].getReportingUnit())
                .rank(tpEngineResponse.getOfficersProfileList()[0].getOfficersRank())
                .weaponType(tpEngineResponse.getOfficersProfileList()[0].getAllowedWeaponType())
                .weaponSrl(tpEngineResponse.getOfficersProfileList()[0].getWeaponSerialNumber())
                .isPermittedCarryWeapon(tpEngineResponse.getOfficersProfileList()[0].getPermissionToCarryWeapon())
                .bloodGroup(tpEngineResponse.getOfficersProfileList()[0].getBloogroup())
                .image(ImageUtility.convertToBase64image(tpEngineResponse.getOfficersProfileList()[0].getProfilePhoto1()))
                .additionalRemarks(null)
                .contactAddress(tpEngineResponse.getOfficersProfileList()[0].getContactAddress())
                .dateOfBirth(tpEngineResponse.getOfficersProfileList()[0].getDateOfBirth())
                .drivingLicenseNumber(tpEngineResponse.getOfficersProfileList()[0].getDrivingLicenseNumber())
                .email(null) // todo nothing on response automatically sets null
                .emergencyContactPerson(tpEngineResponse.getOfficersProfileList()[0].getEmergencyContactPerson())
               // .emergencyContactCountryCode1(tpEngineResponse.getOfficersProfileList()[0].getEmerge)
                .emergencyContactNumber1(tpEngineResponse.getOfficersProfileList()[0].getEmergencyContactNumber1())
              //  .emergencyContactCountryCode2()
                .emergencyContactNumber2(tpEngineResponse.getOfficersProfileList()[0].getEmergencyContactNumber2())
                .employmentStartDate(tpEngineResponse.getOfficersProfileList()[0].getEmploymentStartDate())
                .relationshipWithContactPerson(tpEngineResponse.getOfficersProfileList()[0].getRelationshipWithContactPerson())
                .firstNameAr(tpEngineResponse.getOfficersProfileList()[0].getOfficer_FirstName_Ar())
                .firstNameEn(tpEngineResponse.getOfficersProfileList()[0].getOfficer_FirstName_En())
                .gender(tpEngineResponse.getOfficersProfileList()[0].getGender())
                .lastNameAr(tpEngineResponse.getOfficersProfileList()[0].getOfficer_LastName_Ar())
                .lastNameEn(tpEngineResponse.getOfficersProfileList()[0].getOfficer_LastName_En())
                .livingCity(tpEngineResponse.getOfficersProfileList()[0].getLivingCity())
                .middleNameAr(tpEngineResponse.getOfficersProfileList()[0].getMiddleName_Ar())
                .middleNameEn(tpEngineResponse.getOfficersProfileList()[0].getMiddleName_En())
                .countryCode(tpEngineResponse.getOfficersProfileList()[0].getMobileNumberCountryCode())
                .mobileNumber(tpEngineResponse.getOfficersProfileList()[0].getMobileNumber())
                .missionDescription(null) //todo nothing here
                .missionType(null) //todo nothing here
                .officerName(tpEngineResponse.getOfficersProfileList()[0].getOfficer_FirstName_Ar() + " " + tpEngineResponse.getOfficersProfileList()[0].getOfficer_LastName_Ar())
                .nationalId(tpEngineResponse.getOfficersProfileList()[0].getNationalIdNumber())
                .nextOfKin(tpEngineResponse.getOfficersProfileList()[0].getNextOfKin())
                .officerCode(tpEngineResponse.getOfficersProfileList()[0].getOfficerCode())
                .officerProfileId(tpEngineResponse.getOfficersProfileList()[0].getOfficerProfileId())
                .officersIdNumber(tpEngineResponse.getOfficersProfileList()[0].getOfficersIdNumber())
                .officerGrade(tpEngineResponse.getOfficersProfileList()[0].getOfficersGrade())
                .otherNotes(null)
                .passportNumber(tpEngineResponse.getOfficersProfileList()[0].getPassportNumber())
                .reportingOfficer(tpEngineResponse.getOfficersProfileList()[0].getReportingOfficerProfileId())
                .statusCode(tpEngineResponse.getOfficersProfileList()[0].getStatusCode())
                .visualIdentificationMark(tpEngineResponse.getOfficersProfileList()[0].getVisualIdentificationMark())
                .build();
    }

    @Override
    public ResponseAPIDto<PoliceOfficerDto> getResponseDto(HttpServletRequest request) {
        LoginUserDo loginUserDo = LoginUserDo.makeLoginUser(request);

        TPEngineResponse response = makeClientCall(loginUserDo);

        return prepareResponseDto(PoliceOfficerMapper.makePoliceStaffDtoList(response.getOfficersProfileList()), true, response);
    }

    @Override
    public TPEngineResponse makeClientCall(LoginUserDo loginUserDo) {
        ViewOfficersProfileRequestVO viewOfficersProfileRequestVO = new ViewOfficersProfileRequestVO();
        viewOfficersProfileRequestVO.setPageNumber(String.valueOf(loginUserDo.getPageNumber()));
        viewOfficersProfileRequestVO.setLimit(String.valueOf(loginUserDo.getLimit()));
        viewOfficersProfileRequestVO.setOfficerProfileSeeAll("Y");

        setFullCredentials(viewOfficersProfileRequestVO, loginUserDo);

        try {
            log.info("Get Officer profile request will be sent to client. {}", viewOfficersProfileRequestVO.getMobileAppUserName());

            return tpcmsClient.tpcmsWebAdminClient().getTPCMSCoreServices().getOfficersProfileList(viewOfficersProfileRequestVO);
        } catch (RemoteException | ServiceException e) {
            log.warn("Something wrong on Get Officer profile request. " + e.getMessage());
        }
        return null;
    }

    @Override
    public ResponseAPIDto<PoliceOfficerDto> prepareResponseDto(List<PoliceOfficerDto> list, boolean status, TPEngineResponse response) {
        ResponseAPIDto<PoliceOfficerDto> responseAPIDto = new ResponseAPIDto<>();
        DataDto<PoliceOfficerDto> dataDto = new DataDto<>();

        dataDto.setTbody(list);
        dataDto.setThead(setTableColumnNames());

        responseAPIDto.setData(dataDto);
        responseAPIDto.setMessage("status");
        responseAPIDto.setStatus("true");

        return responseAPIDto;
    }

    public void setFullCredentials(ViewOfficersProfileRequestVO requestVO, LoginUserDo loginUserDo) {
        requestVO.setLoginOfficerUnitNumber(loginUserDo.getLoginOfficerUnitNumber());
        requestVO.setMobileAppDeviceId(loginUserDo.getMobileAppDeviceId());
        requestVO.setLoginOfficersCode(loginUserDo.getLoginOfficersCode());
        requestVO.setMobileAppDeviceId(loginUserDo.getMobileAppDeviceId());

        setCredentials(requestVO);
    }

    @Override
    public void setCredentials(ViewOfficersProfileRequestVO viewOfficersProfileRequestVO) {
        TpCmsWebAdminAppCredentials credentials = credentialsService.getCredentialsOfWebAdmin();

        viewOfficersProfileRequestVO.setMobileAppUserName(credentials.getMobileAppUserName());
        viewOfficersProfileRequestVO.setMobileAppPassword(credentials.getMobileAppPassword());
        viewOfficersProfileRequestVO.setMobileAppSmartSecurityKey(credentials.getMobileAppSmartSecurityKey());
    }

    @Override
    public List<String> setTableColumnNames() {
        List<String> list = new ArrayList<>();

        list.add("Officer Code");
        list.add("Officer Name");
        list.add("Address");
        list.add("City");
        list.add("Access Code");
        list.add("Last Login");
        list.add("Status");
        list.add("Actions");

        return list;
    }

}
