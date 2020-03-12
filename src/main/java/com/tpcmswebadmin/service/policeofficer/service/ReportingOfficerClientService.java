package com.tpcmswebadmin.service.policeofficer.service;

import com.ssas.tpcms.engine.vo.request.ReportingOfficerRequestVO;
import com.ssas.tpcms.engine.vo.response.TPEngineResponse;
import com.tpcmswebadmin.infrastructure.client.TPCMSClient;
import com.tpcmswebadmin.infrastructure.client.response.DataDto;
import com.tpcmswebadmin.infrastructure.client.response.ResponseAPIDto;
import com.tpcmswebadmin.infrastructure.domain.LoginUserDo;
import com.tpcmswebadmin.infrastructure.domain.constant.TpCmsConstants;
import com.tpcmswebadmin.service.credentials.CredentialsService;
import com.tpcmswebadmin.service.credentials.domain.TpCmsWebAdminAppCredentials;
import com.tpcmswebadmin.service.policeofficer.domain.dto.ReportingOfficerDto;
import com.tpcmswebadmin.service.policeofficer.service.mapper.ReportingOfficerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReportingOfficerClientService  {

    private final TPCMSClient tpcmsClient;

    private final CredentialsService credentialsService;

    public ResponseAPIDto<ReportingOfficerDto> getResponseDto(String name, HttpServletRequest httpServletRequest) {
        LoginUserDo loginUserDo = LoginUserDo.builder()
                .loginOfficersCode((String) httpServletRequest.getSession().getAttribute(TpCmsConstants.OFFICER_CODE))
                .loginOfficerUnitNumber((String) httpServletRequest.getSession().getAttribute(TpCmsConstants.REPORT_UNIT))
                .mobileAppDeviceId((String) httpServletRequest.getSession().getAttribute(TpCmsConstants.MOBILE_APP_DEVICE_ID))
                .build();

        TPEngineResponse response = makeClientCall(name, loginUserDo);

        return prepareResponseDto(ReportingOfficerMapper.makeReportingOfficerDtoList(response.getOfficersProfileList()));
    }


    public ResponseAPIDto<ReportingOfficerDto> prepareResponseDto(List<ReportingOfficerDto> arrayList) {
        ResponseAPIDto<ReportingOfficerDto> responseAPIDto = new ResponseAPIDto<>();
        DataDto<ReportingOfficerDto> dataDto = new DataDto<>();

        if(arrayList.isEmpty()) {
            dataDto.setTbody(null);
            dataDto.setThead(null);

            responseAPIDto.setData(dataDto);
            responseAPIDto.setMessage(null);
            responseAPIDto.setStatus("true");
        } else {
            dataDto.setTbody(null);
            dataDto.setThead(null);

            responseAPIDto.setData(dataDto);
            responseAPIDto.setMessage(null);
            responseAPIDto.setStatus("false");
        }


        return responseAPIDto;
    }

    public TPEngineResponse makeClientCall(String name, LoginUserDo loginUserDo) {
        ReportingOfficerRequestVO reportingOfficerRequestVO =  new ReportingOfficerRequestVO();
        reportingOfficerRequestVO.setLoginOfficersCode(loginUserDo.getLoginOfficersCode());
        reportingOfficerRequestVO.setLoginOfficerUnitNumber(loginUserDo.getLoginOfficerUnitNumber());
        reportingOfficerRequestVO.setOfficersFirstNameAr(name);

        reportingOfficerRequestVO.setMobileAppDeviceId(loginUserDo.getMobileAppDeviceId());
        setCredentials(reportingOfficerRequestVO);

        try {
            log.info("Get reporting officer list will be sent to client. {}", reportingOfficerRequestVO.getMobileAppUserName());

            return tpcmsClient.tpcmsWebAdminClient().getTPCMSCoreServices().getReportingOfficersDetails(reportingOfficerRequestVO);
        } catch (RemoteException | ServiceException e) {
            log.warn("Something wrong on Get reporting officer list request. " + reportingOfficerRequestVO.getMobileAppUserName());
        }
        return null;
    }

    public void setCredentials(ReportingOfficerRequestVO requestVO) {
        TpCmsWebAdminAppCredentials credentials = credentialsService.getCredentialsOfWebAdmin();

        requestVO.setMobileAppUserName(credentials.getMobileAppUserName());
        requestVO.setMobileAppPassword(credentials.getMobileAppPassword());
        requestVO.setMobileAppSmartSecurityKey(credentials.getMobileAppSmartSecurityKey());
    }

}
