package com.tpcmswebadmin.service.calender.service;

import com.ssas.tpcms.engine.vo.request.AdminAppointmentRequestVO;
import com.ssas.tpcms.engine.vo.request.ViewCriminalProfileRequestVO;
import com.ssas.tpcms.engine.vo.response.TPEngineResponse;
import com.tpcmswebadmin.infrastructure.client.TPCMSClient;
import com.tpcmswebadmin.infrastructure.client.response.DataDto;
import com.tpcmswebadmin.infrastructure.client.response.ResponseDto;
import com.tpcmswebadmin.infrastructure.domain.LoginUserDo;
import com.tpcmswebadmin.infrastructure.domain.constant.TpCmsConstants;
import com.tpcmswebadmin.infrastructure.service.ClientServiceAPI;
import com.tpcmswebadmin.service.calender.domain.CalenderDto;
import com.tpcmswebadmin.service.calender.service.mapper.CalenderEventMapper;
import com.tpcmswebadmin.service.credentials.CredentialsService;
import com.tpcmswebadmin.service.credentials.domain.TpCmsWebAdminAppCredentials;
import com.tpcmswebadmin.service.criminals.domain.CasesDto;
import com.tpcmswebadmin.service.criminals.service.mapper.CriminalProfileMapper;
import com.tpcmswebadmin.service.sos.domain.SosCallDto;
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
public class CalenderEventService implements ClientServiceAPI<CalenderDto, LoginUserDo, AdminAppointmentRequestVO> {

    private final TPCMSClient tpcmsClient;

    private final CredentialsService credentialsService;

    public ResponseDto<CalenderDto> getResponseDto(String date, HttpServletRequest request) {
        ResponseDto<CalenderDto> initialResponse = new ResponseDto<>();
        LoginUserDo loginUserDo = LoginUserDo.builder()
                .loginOfficersCode((String) request.getSession().getAttribute(TpCmsConstants.OFFICER_CODE))
                .loginOfficerUnitNumber((String) request.getSession().getAttribute(TpCmsConstants.REPORT_UNIT))
                .mobileAppDeviceId((String) request.getSession().getAttribute(TpCmsConstants.MOBILE_APP_DEVICE_ID))
                .calenderDate(date)
                .build();

        TPEngineResponse response = makeClientCall(loginUserDo);

        return response.getAppointmentDetailsList() == null ? initialResponse : prepareResponseDto(CalenderEventMapper.makeCalenderEventList(response.getAppointmentDetailsList()));
    }

    @Override
    public ResponseDto<CalenderDto> getResponseDto(HttpServletRequest request) {
        log.info("Dummy method, no use");
        return null;
    }

    @Override
    public ResponseDto<CalenderDto> prepareResponseDto(List<CalenderDto> list) {
        ResponseDto<CalenderDto> responseDto = new ResponseDto<>();
        DataDto<CalenderDto> dataDto = new DataDto<>();

        dataDto.setTbody(list);
        dataDto.setThead(setTableColumnNames());

        responseDto.setData(dataDto);
        responseDto.setMessage("status");
        responseDto.setStatus("true");

        return responseDto;
    }

    @Override
    public TPEngineResponse makeClientCall(LoginUserDo loginUserDo) {
        AdminAppointmentRequestVO adminAppointmentRequestVO = new AdminAppointmentRequestVO();
        adminAppointmentRequestVO.setLoginOfficersCode(loginUserDo.getLoginOfficersCode());
        adminAppointmentRequestVO.setMobileAppDeviceId(loginUserDo.getMobileAppDeviceId());
        adminAppointmentRequestVO.setAppointmentDate(loginUserDo.getCalenderDate());

        setCredentials(adminAppointmentRequestVO);

        try {
            log.info("criminal reports request will be sent to client. {}", adminAppointmentRequestVO.getMobileAppUserName());

            return tpcmsClient.tpcmsWebAdminClient().getTPCMSCoreServices().getAppointmentDetails(adminAppointmentRequestVO);
        } catch (RemoteException | ServiceException e) {
            log.warn("Something wrong on criminal reports request. " + adminAppointmentRequestVO.getMobileAppUserName());
        }
        return null;
    }

    @Override
    public void setCredentials(AdminAppointmentRequestVO requestVO) {
        TpCmsWebAdminAppCredentials credentials = credentialsService.getCredentialsOfWebAdmin();

        requestVO.setMobileAppUserName(credentials.getMobileAppUserName());
        requestVO.setMobileAppPassword(credentials.getMobileAppPassword());
        requestVO.setMobileAppSmartSecurityKey(credentials.getMobileAppSmartSecurityKey());
    }

    @Override
    public List<String> setTableColumnNames() {
        List<String> list = new ArrayList<>();

        list.add("To Whom");
        list.add("Day");
        list.add("Time");
        list.add("Title");
        list.add("Actions");

        return list;
    }

}
