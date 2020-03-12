package com.tpcmswebadmin.service.prosecutionoffice.service;

import com.ssas.tpcms.engine.vo.request.ViewCriminalProfileRequestVO;
import com.ssas.tpcms.engine.vo.response.TPEngineResponse;
import com.tpcmswebadmin.infrastructure.client.TPCMSClient;
import com.tpcmswebadmin.infrastructure.client.response.DataDto;
import com.tpcmswebadmin.infrastructure.client.response.ResponseAPIDto;
import com.tpcmswebadmin.infrastructure.domain.LoginUserDo;
import com.tpcmswebadmin.infrastructure.domain.constant.TpCmsConstants;
import com.tpcmswebadmin.infrastructure.service.ClientServiceAPI;
import com.tpcmswebadmin.service.credentials.CredentialsService;
import com.tpcmswebadmin.service.credentials.domain.TpCmsWebAdminAppCredentials;
import com.tpcmswebadmin.service.prosecutionoffice.domain.ProsecutionCasesDto;
import com.tpcmswebadmin.service.prosecutionoffice.service.mapper.ProsecutionProfileMapper;
import com.tpcmswebadmin.service.reference.domain.enums.ClientStatus;
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
public class ProsecutionCasesSubmitReviewClientService implements ClientServiceAPI<ProsecutionCasesDto, LoginUserDo, ViewCriminalProfileRequestVO> {

    private final TPCMSClient tpcmsClient;

    private final CredentialsService credentialsService;

    @Override
    public ResponseAPIDto<ProsecutionCasesDto> getResponseDto(HttpServletRequest request) {
        LoginUserDo loginUserDo = LoginUserDo.builder()
                .loginOfficersCode((String) request.getSession().getAttribute(TpCmsConstants.OFFICER_CODE))
                .loginOfficerUnitNumber((String) request.getSession().getAttribute(TpCmsConstants.REPORT_UNIT))
                .mobileAppDeviceId((String) request.getSession().getAttribute(TpCmsConstants.MOBILE_APP_DEVICE_ID))
                .build();

        TPEngineResponse response = makeClientCall(loginUserDo);

        if(response.getCriminalProfileList() == null) {
            return prepareResponseDto(Collections.emptyList(), false, response);
        } else {
            List<ProsecutionCasesDto> resultList = ProsecutionProfileMapper.makeProsecutionCasesDtoList(response.getCriminalProfileList());
            return prepareResponseDto(resultList, true, response);
        }
    }

    @Override
    public ResponseAPIDto<ProsecutionCasesDto> prepareResponseDto(List<ProsecutionCasesDto> list, boolean status, TPEngineResponse response) {
        ResponseAPIDto<ProsecutionCasesDto> responseAPIDto = new ResponseAPIDto<>();
        DataDto<ProsecutionCasesDto> dataDto = new DataDto<>();

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
            responseAPIDto.setMessage("failure");
            responseAPIDto.setStatus("false");
        }

        return responseAPIDto;
    }

    @Override
    public TPEngineResponse makeClientCall(LoginUserDo loginUserDo) {
        ViewCriminalProfileRequestVO viewCriminalProfileRequestVO = new ViewCriminalProfileRequestVO();
        viewCriminalProfileRequestVO.setLoginOfficersCode(loginUserDo.getLoginOfficersCode());
        viewCriminalProfileRequestVO.setPageNumber(String.valueOf(loginUserDo.getPageNumber()));
        viewCriminalProfileRequestVO.setLimit(String.valueOf(loginUserDo.getLimit()));
        viewCriminalProfileRequestVO.setCriminalsProfileSeeAll("Y");
        viewCriminalProfileRequestVO.setStatusCode(ClientStatus.SUBMIT_FOR_CASE_REVIEW.getClientName());

        viewCriminalProfileRequestVO.setMobileAppDeviceId(loginUserDo.getMobileAppDeviceId());
        setCredentials(viewCriminalProfileRequestVO);

        try {
            log.info("criminal reports request will be sent to client. {}", viewCriminalProfileRequestVO.getMobileAppUserName());

            return tpcmsClient.tpcmsWebAdminClient().getTPCMSCoreServices().getCriminalsProfile(viewCriminalProfileRequestVO);
        } catch (RemoteException | ServiceException e) {
            log.warn("Something wrong on criminal reports request. " + viewCriminalProfileRequestVO.getMobileAppUserName());
        }
        return null;
    }

    @Override
    public void setCredentials(ViewCriminalProfileRequestVO requestVO) {
        TpCmsWebAdminAppCredentials credentials = credentialsService.getCredentialsOfWebAdmin();

        requestVO.setMobileAppUserName(credentials.getMobileAppUserName());
        requestVO.setMobileAppPassword(credentials.getMobileAppPassword());
        requestVO.setMobileAppSmartSecurityKey(credentials.getMobileAppSmartSecurityKey());
    }

    @Override
    public List<String> setTableColumnNames() {
        List<String> list = new ArrayList<>();

        list.add("Case ID");
        list.add("Case Date");
        list.add("User Id");
        list.add("Location");
        list.add("Crime Type");
        list.add("Status");
        list.add("Actions");

        return list;
    }
}
