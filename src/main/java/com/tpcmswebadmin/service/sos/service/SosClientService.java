package com.tpcmswebadmin.service.sos.service;

import com.ssas.tpcms.engine.vo.request.ViewSOSRequestVO;
import com.ssas.tpcms.engine.vo.response.TPEngineResponse;
import com.tpcmswebadmin.infrastructure.client.TPCMSClient;
import com.tpcmswebadmin.infrastructure.client.response.DataDto;
import com.tpcmswebadmin.infrastructure.client.response.ResponseAPIDto;
import com.tpcmswebadmin.infrastructure.domain.LoginUserDo;
import com.tpcmswebadmin.infrastructure.service.ClientServiceAPI;
import com.tpcmswebadmin.service.calender.domain.CalendarDto;
import com.tpcmswebadmin.service.credentials.CredentialsService;
import com.tpcmswebadmin.service.credentials.domain.TpCmsWebAdminAppCredentials;
import com.tpcmswebadmin.service.dashboard.domain.MapCenter;
import com.tpcmswebadmin.service.sos.domain.SosCallDetailDto;
import com.tpcmswebadmin.service.sos.domain.SosCallDetailMapDto;
import com.tpcmswebadmin.service.sos.domain.SosCallDto;
import com.tpcmswebadmin.service.sos.service.mapper.SosCallsMapper;
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
public class SosClientService implements ClientServiceAPI<SosCallDto, LoginUserDo, ViewSOSRequestVO> {

    private final TPCMSClient tpcmsClient;

    private final CredentialsService credentialsService;

    public ResponseAPIDto<SosCallDetailDto> getSosDetailById(String sosId, LoginUserDo loginUserDo) {
        TPEngineResponse response = makeClientCall(sosId, loginUserDo);

        if (response.getSosRequestList() == null)
            return prepareResponseDetailsDto(Collections.emptyList(), false);
        else
            return prepareResponseDetailsDto(SosCallsMapper.makeSosCallDetailsDtoList(response.getSosRequestHistoryList()), true);

    }

    private ResponseAPIDto<SosCallDetailDto> prepareResponseDetailsDto(List<SosCallDetailDto> list, boolean status) {
        ResponseAPIDto<SosCallDetailDto> responseAPIDto = new ResponseAPIDto<>();
        DataDto<SosCallDetailDto> dataDto = new DataDto<>();

        if(status) {
            dataDto.setTbody(list);
            dataDto.setThead(setDetailsTableColumnNames());

            responseAPIDto.setData(dataDto);
            responseAPIDto.setMessage("success");
            responseAPIDto.setStatus("true");
        } else {
            dataDto.setTbody(Collections.emptyList());
            dataDto.setThead(setDetailsTableColumnNames());

            responseAPIDto.setData(dataDto);
            responseAPIDto.setMessage("There's no data available for loggedin user");
            responseAPIDto.setStatus("false");
        }

        return responseAPIDto;
    }

    public List<String> setDetailsTableColumnNames() {
        List<String> list = new ArrayList<>();

        list.add("Staff Name");
        list.add("Remark Date");
        list.add("Remark");

        return list;
    }

    public SosCallDetailMapDto getSosDetailMap(String id, LoginUserDo loginUserDo) {
        TPEngineResponse response = makeClientCall(id, loginUserDo);
        return prepareSosCallDetailDto();
    }

    private SosCallDetailMapDto prepareSosCallDetailDto() {
        return SosCallDetailMapDto.builder()
                .mapCenter(new MapCenter())
                .data(null)
                .build();
    }

    public TPEngineResponse makeClientCall(String sosId, LoginUserDo loginUserDo) {
        ViewSOSRequestVO viewSOSRequestVO = new ViewSOSRequestVO();
        viewSOSRequestVO.setSosRequestId(sosId);
        setFullCredentials(viewSOSRequestVO, loginUserDo);

        try {
            log.info("SignIn userName request will be sent to client. {}", viewSOSRequestVO.getMobileAppUserName());

            return tpcmsClient.tpcmsWebAdminClient().getTPCMSCoreServices().getSOSRequestDetailsView(viewSOSRequestVO);
        } catch (RemoteException | ServiceException e) {
            log.warn("Something wrong on signIn username request. {}", e.getMessage());
        }
        return null;
    }

    @Override
    public ResponseAPIDto<SosCallDto> getResponseDto(HttpServletRequest request) {
        LoginUserDo loginUserDo = LoginUserDo.makeLoginUser(request);

        TPEngineResponse response = makeClientCall(loginUserDo);

        if (response.getSosRequestList() == null)
            return prepareResponseDto(Collections.emptyList(), false, response);
        else
            return prepareResponseDto(SosCallsMapper.makeSosCallDtoList(response.getSosRequestList()), true, response);
    }

    @Override
    public ResponseAPIDto<SosCallDto> prepareResponseDto(List<SosCallDto> list, boolean status, TPEngineResponse response) {
        ResponseAPIDto<SosCallDto> responseAPIDto = new ResponseAPIDto<>();
        DataDto<SosCallDto> dataDto = new DataDto<>();

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
            responseAPIDto.setMessage("There's no data available for loggedin user");
            responseAPIDto.setStatus("false");
        }

        return responseAPIDto;
    }

    @Override
    public TPEngineResponse makeClientCall(LoginUserDo loginUserDo) {
        ViewSOSRequestVO viewSOSRequestVO = new ViewSOSRequestVO();
        viewSOSRequestVO.setPageNumber(String.valueOf(loginUserDo.getPageNumber()));
        viewSOSRequestVO.setLimit(String.valueOf(loginUserDo.getLimit()));
        viewSOSRequestVO.setSosRequestSeeAll("Y");

        setFullCredentials(viewSOSRequestVO, loginUserDo);

        try {
            log.info("SignIn userName request will be sent to client. {}", viewSOSRequestVO.getMobileAppUserName());

            return tpcmsClient.tpcmsWebAdminClient().getTPCMSCoreServices().getSOSRequestDetailsView(viewSOSRequestVO);
        } catch (RemoteException | ServiceException e) {
            log.warn("Something wrong on signIn username request. {}", e.getMessage());
        }
        return null;
    }

    public void setFullCredentials(ViewSOSRequestVO requestVO, LoginUserDo loginUserDo) {
        requestVO.setLoginOfficersCode(loginUserDo.getLoginOfficersCode());
        requestVO.setLoginOfficersUnitNumber(loginUserDo.getLoginOfficerUnitNumber());
        requestVO.setMobileAppDeviceId(loginUserDo.getMobileAppDeviceId());

        setCredentials(requestVO);
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
