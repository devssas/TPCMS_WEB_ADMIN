package com.tpcmswebadmin.service.calender.service;

import com.ssas.tpcms.engine.vo.request.AdminAppointmentRequestVO;
import com.ssas.tpcms.engine.vo.request.ViewVehicleDetailsRequestVO;
import com.ssas.tpcms.engine.vo.response.TPEngineResponse;
import com.tpcmswebadmin.infrastructure.client.TPCMSClient;
import com.tpcmswebadmin.infrastructure.client.response.DataDto;
import com.tpcmswebadmin.infrastructure.client.response.ResponseAPIDto;
import com.tpcmswebadmin.infrastructure.domain.LoginUserDo;
import com.tpcmswebadmin.infrastructure.domain.constant.TpCmsConstants;
import com.tpcmswebadmin.infrastructure.service.ClientServiceAPI;
import com.tpcmswebadmin.service.calender.domain.CalendarDto;
import com.tpcmswebadmin.service.calender.service.mapper.CalenderEventMapper;
import com.tpcmswebadmin.service.credentials.CredentialsService;
import com.tpcmswebadmin.service.credentials.domain.TpCmsWebAdminAppCredentials;
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
public class CalendarEventService implements ClientServiceAPI<CalendarDto, LoginUserDo, AdminAppointmentRequestVO> {

    private final TPCMSClient tpcmsClient;

    private final CredentialsService credentialsService;

    public ResponseAPIDto<CalendarDto> getResponseDto(String date, HttpServletRequest request) {
        List<CalendarDto> initialResponse = Collections.emptyList();
        LoginUserDo loginUserDo = LoginUserDo.makeLoginUser(request);
        loginUserDo.setCalenderDate(date);

        TPEngineResponse response = makeClientCall(loginUserDo);

        return response.getAppointmentDetailsList() == null ? prepareResponseDto(initialResponse, false, response) : prepareResponseDto(CalenderEventMapper.makeCalenderEventList(response.getAppointmentDetailsList()), true, response);
    }

    @Override
    public ResponseAPIDto<CalendarDto> getResponseDto(HttpServletRequest request) {
        log.info("Dummy method, no use");
        return null;
    }

    @Override
    public ResponseAPIDto<CalendarDto> prepareResponseDto(List<CalendarDto> list, boolean status, TPEngineResponse response) {
        ResponseAPIDto<CalendarDto> responseAPIDto = new ResponseAPIDto<>();
        DataDto<CalendarDto> dataDto = new DataDto<>();

        dataDto.setTbody(list);
        dataDto.setThead(setTableColumnNames());

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
            responseAPIDto.setMessage(response.getResponseCodeVO().getResponseValue());
            responseAPIDto.setStatus("false");
        }

        return responseAPIDto;
    }

    @Override
    public TPEngineResponse makeClientCall(LoginUserDo loginUserDo) {
        AdminAppointmentRequestVO adminAppointmentRequestVO = new AdminAppointmentRequestVO();
        adminAppointmentRequestVO.setAppointmentDate(loginUserDo.getCalenderDate());

        setFullCredentials(adminAppointmentRequestVO, loginUserDo);

        try {
            log.info("Calender event will be sent to client. {}", adminAppointmentRequestVO.getMobileAppUserName());

            return tpcmsClient.tpcmsWebAdminClient().getTPCMSCoreServices().getAppointmentDetails(adminAppointmentRequestVO);
        } catch (RemoteException | ServiceException e) {
            log.warn("Something wrong on Calender event request. {}",  e.getMessage());
        }
        return null;
    }

    public void setFullCredentials(AdminAppointmentRequestVO requestVO, LoginUserDo loginUserDo) {
        requestVO.setLoginOfficersCode(loginUserDo.getLoginOfficersCode());
        requestVO.setMobileAppDeviceId(loginUserDo.getMobileAppDeviceId());

        setCredentials(requestVO);
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
