package com.tpcmswebadmin.service.sos.service;

import com.ssas.tpcms.engine.vo.request.ViewSOSRequestVO;
import com.ssas.tpcms.engine.vo.response.TPEngineResponse;
import com.tpcmswebadmin.infrastructure.client.TPCMSClient;
import com.tpcmswebadmin.infrastructure.client.response.DataDto;
import com.tpcmswebadmin.infrastructure.client.response.ResponseDto;
import com.tpcmswebadmin.infrastructure.domain.LoginUserDo;
import com.tpcmswebadmin.infrastructure.domain.constant.TpCmsConstants;
import com.tpcmswebadmin.infrastructure.service.ClientServiceAPI;
import com.tpcmswebadmin.service.credentials.CredentialsService;
import com.tpcmswebadmin.service.credentials.domain.TpCmsWebAdminAppCredentials;
import com.tpcmswebadmin.service.sos.domain.SosCallDto;
import com.tpcmswebadmin.service.sos.service.mapper.SosCallsMapper;
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
public class SosCallsClientService implements ClientServiceAPI<SosCallDto, LoginUserDo, ViewSOSRequestVO> {

    private final TPCMSClient tpcmsClient;

    private final CredentialsService credentialsService;

    @Override
    public ResponseDto<SosCallDto> getResponseDto(HttpServletRequest request) {
        LoginUserDo loginUserDo = LoginUserDo.builder()
                .loginOfficersCode((String) request.getSession().getAttribute(TpCmsConstants.OFFICER_CODE))
                .loginOfficerUnitNumber((String) request.getSession().getAttribute(TpCmsConstants.REPORT_UNIT))
                .mobileAppDeviceId((String) request.getSession().getAttribute(TpCmsConstants.MOBILE_APP_DEVICE_ID))
                .build();

        TPEngineResponse response = makeClientCall(loginUserDo);

        return prepareResponseDto(SosCallsMapper.makeSosCallDtoList(response.getSosRequestList()));
    }

    @Override
    public ResponseDto<SosCallDto> prepareResponseDto(List<SosCallDto> list) {
        ResponseDto<SosCallDto> responseDto = new ResponseDto<>();
        DataDto<SosCallDto> dataDto = new DataDto<>();

        dataDto.setTbody(list);
        dataDto.setThead(setTableColumnNames());

        responseDto.setData(dataDto);
        responseDto.setMessage("status");
        responseDto.setStatus("true");

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
        list.add("emergency Location");
        list.add("Phone");
        list.add("Remarks");
        list.add("Status");
        list.add("Actions");

        return list;
    }
}
