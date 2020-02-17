package com.tpcmswebadmin.service.missionpermits.service;

import com.ssas.tpcms.engine.vo.request.ViewNotificationsRequestVO;
import com.ssas.tpcms.engine.vo.request.ViewSpecialMissionRequestVO;
import com.ssas.tpcms.engine.vo.response.TPEngineResponse;
import com.tpcmswebadmin.infrastructure.client.TPCMSClient;
import com.tpcmswebadmin.infrastructure.client.response.DataDto;
import com.tpcmswebadmin.infrastructure.client.response.ResponseDto;
import com.tpcmswebadmin.infrastructure.domain.LoginUserDo;
import com.tpcmswebadmin.infrastructure.domain.constant.TpCmsConstants;
import com.tpcmswebadmin.infrastructure.service.ClientServiceAPI;
import com.tpcmswebadmin.service.credentials.CredentialsService;
import com.tpcmswebadmin.service.credentials.domain.TpCmsWebAdminAppCredentials;
import com.tpcmswebadmin.service.missionpermits.domain.MissionPermitsDto;
import com.tpcmswebadmin.service.missionpermits.service.mapper.MissionPermitsMapper;
import com.tpcmswebadmin.service.notification.domain.NotificationDto;
import com.tpcmswebadmin.service.notification.service.mapper.NotificationMapper;
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
public class MissionPermitsClientService implements ClientServiceAPI<MissionPermitsDto, LoginUserDo, ViewSpecialMissionRequestVO> {

    private final TPCMSClient tpcmsClient;

    private final CredentialsService credentialsService;

    @Override
    public ResponseDto<MissionPermitsDto> getResponseDto(HttpServletRequest request) {
        LoginUserDo loginUserDo = LoginUserDo.builder()
                .loginOfficersCode((String) request.getSession().getAttribute(TpCmsConstants.OFFICER_CODE))
                .loginOfficerUnitNumber((String) request.getSession().getAttribute(TpCmsConstants.REPORT_UNIT))
                .build();

        TPEngineResponse response = makeClientCall(loginUserDo);

        return prepareResponseDto(MissionPermitsMapper.makeMissionPermitsDtoList(response.getSpecialMissionList()));
    }

    @Override
    public ResponseDto<MissionPermitsDto> prepareResponseDto(List<MissionPermitsDto> list) {
        ResponseDto<MissionPermitsDto> responseDto = new ResponseDto<>();
        DataDto<MissionPermitsDto> dataDto = new DataDto<>();

        dataDto.setTbody(list);
        dataDto.setThead(setTableColumnNames());

        responseDto.setData(dataDto);
        responseDto.setMessage("status");
        responseDto.setStatus("true");

        return responseDto;
    }

    @Override
    public TPEngineResponse makeClientCall(LoginUserDo loginUserDo) {
        ViewSpecialMissionRequestVO viewSpecialMissionRequestVO = new ViewSpecialMissionRequestVO();
        viewSpecialMissionRequestVO.setLoginOfficersCode(loginUserDo.getLoginOfficersCode());
        viewSpecialMissionRequestVO.setPageNumber(String.valueOf(loginUserDo.getPageNumber()));
        viewSpecialMissionRequestVO.setLimit(String.valueOf(loginUserDo.getLimit()));
        viewSpecialMissionRequestVO.setSpecialMissionSeeAll("Y");

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
        requestVO.setMobileAppDeviceId(TpCmsConstants.MOBILE_DEVICE_ID); //todo constant pass
        requestVO.setMobileAppPassword(credentials.getMobileAppPassword());
        requestVO.setMobileAppSmartSecurityKey(credentials.getMobileAppSmartSecurityKey());
    }

    @Override
    public List<String> setTableColumnNames() {
        List<String> list = new ArrayList<>();

        list.add("Permit ID");
        list.add("username");
        list.add("Mobile Number");
        list.add("City");
        list.add("State");
        list.add("Expiry Date");
        list.add("Status");
        list.add("Actions");

        return list;
    }
}
