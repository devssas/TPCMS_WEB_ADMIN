package com.tpcmswebadmin.service.missionpermits.service;

import com.ssas.tpcms.engine.vo.request.SpecialMissionRequestVO;
import com.ssas.tpcms.engine.vo.request.ViewSpecialMissionRequestVO;
import com.ssas.tpcms.engine.vo.response.TPEngineResponse;
import com.tpcmswebadmin.infrastructure.client.TPCMSClient;
import com.tpcmswebadmin.infrastructure.client.response.DataDto;
import com.tpcmswebadmin.infrastructure.client.response.ResponseDto;
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
import com.tpcmswebadmin.webpages.missionpermits.model.MissionPermitCardCreateModel;
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

    public MissionCardDto getSpecialMissionsByMissionId(String missionId, String missionQrCode, HttpServletRequest httpServletRequest) {
        LoginUserDo loginUserDo = LoginUserDo.builder()
                .loginOfficersCode((String) httpServletRequest.getSession().getAttribute(TpCmsConstants.OFFICER_CODE))
                .loginOfficerUnitNumber((String) httpServletRequest.getSession().getAttribute(TpCmsConstants.REPORT_UNIT))
                .mobileAppDeviceId((String) httpServletRequest.getSession().getAttribute(TpCmsConstants.MOBILE_APP_DEVICE_ID))
                .build();

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
                .expiryDate(DateUtility.convertToFormat(specialMissionDetails.getSpecialMissionList()[0].getExpiryDate(), TpCmsConstants.SCREEN_DATE_FORMAT))
                .isPermittedCarryWeapon(specialMissionDetails.getSpecialMissionList()[0].getPermissionToCarryWeapon())
                .weaponType(specialMissionDetails.getSpecialMissionList()[0].getAllowedWeaponType())
                .missionType(specialMissionDetails.getSpecialMissionList()[0].getMissionType())
                .missionDescription(specialMissionDetails.getSpecialMissionList()[0].getMissionDescription())
                .image(ImageUtility.convertToBase64image(specialMissionDetails.getSpecialMissionList()[0].getAttachmentPhoto1()))
                .build();
    }

    @Override
    public ResponseDto<MissionPermitsDto> getResponseDto(HttpServletRequest request) {
        LoginUserDo loginUserDo = LoginUserDo.builder()
                .accessRole((String) request.getSession().getAttribute(TpCmsConstants.ACCESS_ROLE))
                .loginOfficersCode((String) request.getSession().getAttribute(TpCmsConstants.OFFICER_CODE))
                .loginOfficerUnitNumber((String) request.getSession().getAttribute(TpCmsConstants.REPORT_UNIT))
                .mobileAppDeviceId((String) request.getSession().getAttribute(TpCmsConstants.MOBILE_APP_DEVICE_ID))
                .build();

        TPEngineResponse response = makeClientCall(loginUserDo);

        if (response.getSpecialMissionList() == null)
            return prepareResponseDto(Collections.emptyList(), false);
        else
            return prepareResponseDto(MissionPermitsMapper.makeMissionPermitsDtoList(response.getSpecialMissionList()), true);
    }

    @Override
    public ResponseDto<MissionPermitsDto> prepareResponseDto(List<MissionPermitsDto> list, boolean status) {
        ResponseDto<MissionPermitsDto> responseDto = new ResponseDto<>();
        DataDto<MissionPermitsDto> dataDto = new DataDto<>();

        if(status) {
            dataDto.setTbody(list);
            dataDto.setThead(setTableColumnNames());

            responseDto.setData(dataDto);
            responseDto.setMessage("success");
            responseDto.setStatus("true");
        } else {
            dataDto.setTbody(Collections.emptyList());
            dataDto.setThead(setTableColumnNames());

            responseDto.setData(dataDto);
            responseDto.setMessage("There's no data available for loggedin user");
            responseDto.setStatus("false");
        }

        return responseDto;
    }

    @Override
    public TPEngineResponse makeClientCall(LoginUserDo loginUserDo) {
        ViewSpecialMissionRequestVO viewSpecialMissionRequestVO = new ViewSpecialMissionRequestVO();
        viewSpecialMissionRequestVO.setLoginOfficersCode(loginUserDo.getLoginOfficersCode());
        viewSpecialMissionRequestVO.setPageNumber(String.valueOf(loginUserDo.getPageNumber()));
        viewSpecialMissionRequestVO.setLimit(String.valueOf(loginUserDo.getLimit()));
        viewSpecialMissionRequestVO.setSpecialMissionSeeAll("Y");
        viewSpecialMissionRequestVO.setAccessRoleCode(loginUserDo.getAccessRole());

        viewSpecialMissionRequestVO.setMobileAppDeviceId(loginUserDo.getMobileAppDeviceId());
        setCredentials(viewSpecialMissionRequestVO);

        try {
            log.info("Get Special mission list request will be sent to client. {}", viewSpecialMissionRequestVO.getMobileAppUserName());

            return tpcmsClient.tpcmsWebAdminClient().getTPCMSCoreServices().getSpecialMissionDetails(viewSpecialMissionRequestVO);
        } catch (RemoteException | ServiceException e) {
            log.warn("Something wrong on get Special mission list request. " + viewSpecialMissionRequestVO.getMobileAppUserName());
        }
        return null;
    }

    @Override
    public void setCredentials(ViewSpecialMissionRequestVO requestVO) {
        TpCmsWebAdminAppCredentials credentials = credentialsService.getCredentialsOfWebAdmin();

        requestVO.setMobileAppUserName(credentials.getMobileAppUserName());
        requestVO.setMobileAppPassword(credentials.getMobileAppPassword());
        requestVO.setMobileAppSmartSecurityKey(credentials.getMobileAppSmartSecurityKey());
    }

    public void setCredentials(SpecialMissionRequestVO specialMissionRequestVO) {
        TpCmsWebAdminAppCredentials credentials = credentialsService.getCredentialsOfWebAdmin();

        specialMissionRequestVO.setMobileAppUserName(credentials.getMobileAppUserName());
        specialMissionRequestVO.setMobileAppPassword(credentials.getMobileAppPassword());
        specialMissionRequestVO.setMobileAppSmartSecurityKey(credentials.getMobileAppSmartSecurityKey());
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
