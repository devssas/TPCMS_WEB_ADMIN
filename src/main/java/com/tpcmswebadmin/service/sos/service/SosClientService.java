package com.tpcmswebadmin.service.sos.service;

import com.ssas.tpcms.engine.vo.request.ViewSOSRequestVO;
import com.ssas.tpcms.engine.vo.response.SOSResponseVO;
import com.ssas.tpcms.engine.vo.response.TPEngineResponse;
import com.tpcmswebadmin.infrastructure.client.TPCMSClient;
import com.tpcmswebadmin.infrastructure.client.response.DataDto;
import com.tpcmswebadmin.infrastructure.client.response.ResponseDto;
import com.tpcmswebadmin.infrastructure.domain.LoginUserDo;
import com.tpcmswebadmin.infrastructure.domain.constant.TpCmsConstants;
import com.tpcmswebadmin.infrastructure.service.ClientServiceAPI;
import com.tpcmswebadmin.service.credentials.CredentialsService;
import com.tpcmswebadmin.service.credentials.domain.TpCmsWebAdminAppCredentials;
import com.tpcmswebadmin.service.dashboard.domain.MapCenter;
import com.tpcmswebadmin.service.policevehicles.service.mapper.PoliceVehicleMapper;
import com.tpcmswebadmin.service.sos.domain.SosCallDetailDto;
import com.tpcmswebadmin.service.sos.domain.SosCallDto;
import com.tpcmswebadmin.service.sos.service.mapper.SosCallsMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SosClientService implements ClientServiceAPI<SosCallDto, LoginUserDo, ViewSOSRequestVO> {

    private final TPCMSClient tpcmsClient;

    private final CredentialsService credentialsService;

    public SosCallDetailDto getSosDetailById(HttpServletRequest httpServletRequest) {
        LoginUserDo loginUserDo = LoginUserDo.builder()
                .loginOfficersCode((String) httpServletRequest.getSession().getAttribute(TpCmsConstants.OFFICER_CODE))
                .loginOfficerUnitNumber((String) httpServletRequest.getSession().getAttribute(TpCmsConstants.REPORT_UNIT))
                .mobileAppDeviceId((String) httpServletRequest.getSession().getAttribute(TpCmsConstants.MOBILE_APP_DEVICE_ID))
                .build();

        return prepareSosCallDetailDto();
    }

    private SosCallDetailDto prepareSosCallDetailDto() {
        return SosCallDetailDto.builder()
                .mapCenter(new MapCenter())
                .data(null)
                .build();
    }

    public TPEngineResponse makeClientCall(String sosId, LoginUserDo loginUserDo) {
        ViewSOSRequestVO viewSOSRequestVO = new ViewSOSRequestVO();
        viewSOSRequestVO.setLoginOfficersCode(loginUserDo.getLoginOfficersCode());
        viewSOSRequestVO.setLoginOfficersUnitNumber(loginUserDo.getLoginOfficerUnitNumber());
        viewSOSRequestVO.setSosRequestId(sosId);
        viewSOSRequestVO.setMobileAppDeviceId(loginUserDo.getMobileAppDeviceId());
        setCredentials(viewSOSRequestVO);

        try {
            log.info("SignIn userName request will be sent to client. {}", viewSOSRequestVO.getMobileAppUserName());

            return tpcmsClient.tpcmsWebAdminClient().getTPCMSCoreServices().getSOSRequestDetailsView(viewSOSRequestVO);
        } catch (RemoteException | ServiceException e) {
            log.warn("Something wrong on signIn username request. " + viewSOSRequestVO.getMobileAppUserName());
        }
        return null;
    }

    @Override
    public ResponseDto<SosCallDto> getResponseDto(HttpServletRequest request) {
        LoginUserDo loginUserDo = LoginUserDo.builder()
                .loginOfficersCode((String) request.getSession().getAttribute(TpCmsConstants.OFFICER_CODE))
                .loginOfficerUnitNumber((String) request.getSession().getAttribute(TpCmsConstants.REPORT_UNIT))
                .mobileAppDeviceId((String) request.getSession().getAttribute(TpCmsConstants.MOBILE_APP_DEVICE_ID))
                .build();

        TPEngineResponse response = makeClientCall(loginUserDo);

        if (response.getSosRequestList() == null)
            return prepareResponseDto(Collections.emptyList(), false);
        else
            return prepareResponseDto(SosCallsMapper.makeSosCallDtoList(response.getSosRequestList()), true);
    }

    @Override
    public ResponseDto<SosCallDto> prepareResponseDto(List<SosCallDto> list, boolean status) {
        ResponseDto<SosCallDto> responseDto = new ResponseDto<>();
        DataDto<SosCallDto> dataDto = new DataDto<>();

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
        ViewSOSRequestVO viewSOSRequestVO = new ViewSOSRequestVO();
        viewSOSRequestVO.setLoginOfficersCode(loginUserDo.getLoginOfficersCode());
        viewSOSRequestVO.setLoginOfficersUnitNumber(loginUserDo.getLoginOfficerUnitNumber());
        viewSOSRequestVO.setPageNumber(String.valueOf(loginUserDo.getPageNumber()));
        viewSOSRequestVO.setLimit(String.valueOf(loginUserDo.getLimit()));
        viewSOSRequestVO.setSosRequestSeeAll("Y");

        viewSOSRequestVO.setMobileAppDeviceId(loginUserDo.getMobileAppDeviceId());
        setCredentials(viewSOSRequestVO);

        try {
            log.info("SignIn userName request will be sent to client. {}", viewSOSRequestVO.getMobileAppUserName());

            return tpcmsClient.tpcmsWebAdminClient().getTPCMSCoreServices().getSOSRequestDetailsView(viewSOSRequestVO);
        } catch (RemoteException | ServiceException e) {
            log.warn("Something wrong on signIn username request. " + viewSOSRequestVO.getMobileAppUserName());
        }
        return null;
    }

    @Override
    public void setCredentials(ViewSOSRequestVO requestVO) {
        TpCmsWebAdminAppCredentials credentials = credentialsService.getCredentialsOfWebAdmin();

        requestVO.setMobileAppUserName(credentials.getMobileAppUserName());
        requestVO.setMobileAppPassword(credentials.getMobileAppPassword());
        requestVO.setMobileAppSmartSecurityKey(credentials.getMobileAppSmartSecurityKey());
    }

    @Override
    public List<String> setTableColumnNames() {
        List<String> list = new ArrayList<>();

        list.add("Request Number");
        list.add("Request Date");
        list.add("User ID");
        list.add("Emergency Location");
        list.add("Phone");
        list.add("Status");
        list.add("Actions");

        return list;
    }

}
