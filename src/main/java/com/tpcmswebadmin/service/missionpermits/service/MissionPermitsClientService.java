package com.tpcmswebadmin.service.missionpermits.service;

import com.ssas.tpcms.engine.vo.request.SpecialMissionRequestVO;
import com.ssas.tpcms.engine.vo.request.ViewSpecialMissionRequestVO;
import com.ssas.tpcms.engine.vo.request.ViewVehicleDetailsRequestVO;
import com.ssas.tpcms.engine.vo.response.TPEngineResponse;
import com.tpcmswebadmin.infrastructure.client.TPCMSClient;
import com.tpcmswebadmin.infrastructure.client.response.DataDto;
import com.tpcmswebadmin.infrastructure.client.response.ResponseAPIDto;
import com.tpcmswebadmin.infrastructure.domain.LoginUserDo;
import com.tpcmswebadmin.infrastructure.domain.constant.TpCmsConstants;
import com.tpcmswebadmin.infrastructure.service.ClientServiceAPI;
import com.tpcmswebadmin.infrastructure.utils.DateUtility;
import com.tpcmswebadmin.infrastructure.utils.ImageUtility;
import com.tpcmswebadmin.infrastructure.utils.StringUtility;
import com.tpcmswebadmin.service.credentials.CredentialsService;
import com.tpcmswebadmin.service.credentials.domain.TpCmsWebAdminAppCredentials;
import com.tpcmswebadmin.service.missionpermits.domain.MissionCardDto;
import com.tpcmswebadmin.service.missionpermits.domain.MissionPermitsDto;
import com.tpcmswebadmin.service.missionpermits.service.mapper.MissionPermitsMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MissionPermitsClientService implements ClientServiceAPI<MissionPermitsDto, LoginUserDo, ViewSpecialMissionRequestVO> {

    private final TPCMSClient tpcmsClient;

    private final CredentialsService credentialsService;

    public MissionCardDto getSpecialMissionsByMissionId(String missionId, String missionQrCode, LoginUserDo loginUserDo) {
        ViewSpecialMissionRequestVO viewSpecialMissionRequestVO = new ViewSpecialMissionRequestVO();
        viewSpecialMissionRequestVO.setLoginOfficersCode(loginUserDo.getLoginOfficersCode());
        viewSpecialMissionRequestVO.setSpmissionId(missionId);
        viewSpecialMissionRequestVO.setSpecialMissionQRCode(missionQrCode);

        viewSpecialMissionRequestVO.setMobileAppDeviceId(loginUserDo.getMobileAppDeviceId());
        setCredentials(viewSpecialMissionRequestVO);

        try {
            log.info("Get Special mission list request will be sent to client. {}", viewSpecialMissionRequestVO.getMobileAppUserName());

            return prepareMissionCardDto(tpcmsClient.tpcmsWebAdminClient().getTPCMSCoreServices().getSpecialMissionDetails(viewSpecialMissionRequestVO));
        } catch (RemoteException | ServiceException e) {
            log.warn("Something wrong on get Special mission list request. " + viewSpecialMissionRequestVO.getMobileAppUserName());
        }
        
        return null;
    }

    private MissionCardDto prepareMissionCardDto(TPEngineResponse specialMissionDetails) {
        return MissionCardDto.builder()
                .officerName(StringUtility.makeFullName(specialMissionDetails.getSpecialMissionList()[0].getFirstName_Ar(), specialMissionDetails.getSpecialMissionList()[0].getLastName_Ar()))
                .commandCenter(specialMissionDetails.getSpecialMissionList()[0].getCommandCenterCode())
                .rank(specialMissionDetails.getSpecialMissionList()[0].getOfficersRank())
                .unit(specialMissionDetails.getSpecialMissionList()[0].getReportingUnit())
                .officerId(specialMissionDetails.getSpecialMissionList()[0].getOfficersProfileId())
                .activationDate(DateUtility.convertToFormat(specialMissionDetails.getSpecialMissionList()[0].getActivationDate(), TpCmsConstants.SCREEN_DATE_FORMAT))
                .expiryDate(DateUtility.convertToFormat(specialMissionDetails.getSpecialMissionList()[0].getExpiryDate(), TpCmsConstants.SCREEN_DATE_FORMAT))
                .isPermittedCarryWeapon(specialMissionDetails.getSpecialMissionList()[0].getPermissionToCarryWeapon())
                .weaponType(specialMissionDetails.getSpecialMissionList()[0].getAllowedWeaponType())
                .missionType(specialMissionDetails.getSpecialMissionList()[0].getMissionType())
                .missionDescription(specialMissionDetails.getSpecialMissionList()[0].getMissionDescription())
                .image(ImageUtility.convertToBase64image(specialMissionDetails.getSpecialMissionList()[0].getAttachmentPhoto1()))
                .additionalRemarks(specialMissionDetails.getSpecialMissionList()[0].getOtherNotes())
                .build();
    }

    @Override
    public ResponseAPIDto<MissionPermitsDto> getResponseDto(HttpServletRequest request) {
        LoginUserDo loginUserDo = LoginUserDo.builder()
                .accessRole((String) request.getSession().getAttribute(TpCmsConstants.ACCESS_ROLE))
                .loginOfficersCode((String) request.getSession().getAttribute(TpCmsConstants.OFFICER_CODE))
                .loginOfficerUnitNumber((String) request.getSession().getAttribute(TpCmsConstants.REPORT_UNIT))
                .mobileAppDeviceId((String) request.getSession().getAttribute(TpCmsConstants.MOBILE_APP_DEVICE_ID))
                .build();

        TPEngineResponse response = makeClientCall(loginUserDo);

        if (response.getSpecialMissionList() == null)
            return prepareResponseDto(Collections.emptyList(), false, response);
        else
            return prepareResponseDto(MissionPermitsMapper.makeMissionPermitsDtoList(response.getSpecialMissionList()), true, response);
    }

    @Override
    public ResponseAPIDto<MissionPermitsDto> prepareResponseDto(List<MissionPermitsDto> list, boolean status, TPEngineResponse response) {
        ResponseAPIDto<MissionPermitsDto> responseAPIDto = new ResponseAPIDto<>();
        DataDto<MissionPermitsDto> dataDto = new DataDto<>();

        if(status) {
            dataDto.setTbody(list);
            dataDto.setThead(setTableColumnNames());

            responseAPIDto.setData(dataDto);
            responseAPIDto.setMessage("success");
            responseAPIDto.setStatus("true");
        } else {
            dataDto.setTbody(Collections.emptyList());
            dataDto.setThead(setTableColumnNames());

            responseAPIDto.setData(dataDto);
            responseAPIDto.setMessage(response.getResponseCodeVO().getResponseCode() + " - " + response.getResponseCodeVO().getResponseValue());
            responseAPIDto.setStatus("false");
        }

        return responseAPIDto;
    }

    @Override
    public TPEngineResponse makeClientCall(LoginUserDo loginUserDo) {
        ViewSpecialMissionRequestVO viewSpecialMissionRequestVO = new ViewSpecialMissionRequestVO();
        viewSpecialMissionRequestVO.setPageNumber(String.valueOf(loginUserDo.getPageNumber()));
        viewSpecialMissionRequestVO.setLimit(String.valueOf(loginUserDo.getLimit()));
        viewSpecialMissionRequestVO.setSpecialMissionSeeAll("Y");

        setFullCredentials(viewSpecialMissionRequestVO, loginUserDo);

        try {
            log.info("Get Special mission list request will be sent to client. {}", viewSpecialMissionRequestVO.getMobileAppUserName());

            return tpcmsClient.tpcmsWebAdminClient().getTPCMSCoreServices().getSpecialMissionDetails(viewSpecialMissionRequestVO);
        } catch (RemoteException | ServiceException e) {
            log.warn("Something wrong on get Special mission list request. " + viewSpecialMissionRequestVO.getMobileAppUserName());
        }
        return null;
    }

    public void setFullCredentials(ViewSpecialMissionRequestVO requestVO, LoginUserDo loginUserDo) {
        requestVO.setLoginOfficersCode(loginUserDo.getLoginOfficersCode());
        requestVO.setMobileAppDeviceId(loginUserDo.getMobileAppDeviceId());
        requestVO.setAccessRoleCode(loginUserDo.getAccessRole());
        requestVO.setMobileAppDeviceId(loginUserDo.getMobileAppDeviceId());

        setCredentials(requestVO);
    }

    @Override
    public void setCredentials(ViewSpecialMissionRequestVO requestVO) {
        TpCmsWebAdminAppCredentials credentials = credentialsService.getCredentialsOfWebAdmin();

        requestVO.setMobileAppUserName(credentials.getMobileAppUserName());
        requestVO.setMobileAppPassword(credentials.getMobileAppPassword());
        requestVO.setMobileAppSmartSecurityKey(credentials.getMobileAppSmartSecurityKey());
    }

    @Override
    public List<String> setTableColumnNames() {
        List<String> list = new ArrayList<>();

        list.add("Permit ID");
        list.add("username");
        list.add("Mission Type");
        list.add("Mission QrCode");
        list.add("Expiry Date");
        list.add("Status");
        list.add("Actions");

        return list;
    }
}
