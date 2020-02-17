package com.tpcmswebadmin.service.criminals.service;

import com.ssas.tpcms.engine.vo.request.ViewCrimeReportRequestVO;
import com.ssas.tpcms.engine.vo.response.TPEngineResponse;
import com.tpcmswebadmin.infrastructure.client.TPCMSClient;
import com.tpcmswebadmin.infrastructure.client.response.DataDto;
import com.tpcmswebadmin.infrastructure.client.response.ResponseDto;
import com.tpcmswebadmin.infrastructure.domain.LoginUserDo;
import com.tpcmswebadmin.infrastructure.domain.constant.TpCmsConstants;
import com.tpcmswebadmin.infrastructure.service.ClientServiceAPI;
import com.tpcmswebadmin.service.credentials.CredentialsService;
import com.tpcmswebadmin.service.credentials.domain.TpCmsWebAdminAppCredentials;
import com.tpcmswebadmin.service.criminals.domain.CrimeReportDto;
import com.tpcmswebadmin.service.criminals.service.mapper.CrimeReportsMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CrimeReportsClientService implements ClientServiceAPI<CrimeReportDto, LoginUserDo, ViewCrimeReportRequestVO> {

    private final TPCMSClient tpcmsClient;

    private final CredentialsService credentialsService;

    @Override
    public ResponseDto<CrimeReportDto> getResponseDto(HttpServletRequest request) {
        LoginUserDo loginUserDo = LoginUserDo.builder()
                .loginOfficersCode((String) request.getSession().getAttribute(TpCmsConstants.OFFICER_CODE))
                .loginOfficerUnitNumber((String) request.getSession().getAttribute(TpCmsConstants.REPORT_UNIT))
                .build();

        TPEngineResponse response = makeClientCall(loginUserDo);

        return prepareResponseDto(CrimeReportsMapper.makeCrimeReportDtoList(response.getCrimeReportList()));
    }

    @Override
    public ResponseDto<CrimeReportDto> prepareResponseDto(List<CrimeReportDto> list) {
        ResponseDto<CrimeReportDto> responseDto = new ResponseDto<>();
        DataDto<CrimeReportDto> dataDto = new DataDto<>();

        dataDto.setTbody(list);
        dataDto.setThead(setTableColumnNames());

        responseDto.setData(dataDto);
        responseDto.setMessage("status");
        responseDto.setStatus("true");

        return responseDto;
    }

    @Override
    public TPEngineResponse makeClientCall(LoginUserDo loginUserDo) {
        ViewCrimeReportRequestVO viewCrimeReportRequestVO = new ViewCrimeReportRequestVO();
        viewCrimeReportRequestVO.setLoginOfficersCode(loginUserDo.getLoginOfficersCode());
        viewCrimeReportRequestVO.setPageNumber(String.valueOf(loginUserDo.getPageNumber()));
        viewCrimeReportRequestVO.setLimit(String.valueOf(loginUserDo.getLimit()));
        viewCrimeReportRequestVO.setCrimeReportSeeAll("Y");

        setCredentials(viewCrimeReportRequestVO);

        try {
            log.info("crimeReports request will be sent to client. {}", viewCrimeReportRequestVO.getMobileAppUserName());

            return tpcmsClient.tpcmsWebAdminClient().getTPCMSCoreServices().getCrimeReports(viewCrimeReportRequestVO);
        } catch (RemoteException | ServiceException e) {
            log.warn("Something wrong on crimeReports request. " + viewCrimeReportRequestVO.getMobileAppUserName());
        }
        return null;
    }

    @Override
    public void setCredentials(ViewCrimeReportRequestVO requestVO) {
        TpCmsWebAdminAppCredentials credentials = credentialsService.getCredentialsOfWebAdmin();

        requestVO.setMobileAppUserName(credentials.getMobileAppUserName());
        requestVO.setMobileAppDeviceId(TpCmsConstants.MOBILE_DEVICE_ID); //todo constant pass
        requestVO.setMobileAppPassword(credentials.getMobileAppPassword());
        requestVO.setMobileAppSmartSecurityKey(credentials.getMobileAppSmartSecurityKey());
    }

    @Override
    public String prepareActionsColumn(Integer id) {
        String actionView = "<a href='/tpcmsWebAdmin/viewOfficer?officerId={officerId}' class='button-v1 btn-color-1'><i class='icon-eye'></i></a>";
        String actionUpdate = "<a href='/tpcmsWebAdmin/updateOfficer?officerId={officerId}' class='button-v1 btn-color-1'><i class='icon-edit'></i></a>";

        return actionView.replace("{officerId}", String.valueOf(id)) + actionUpdate.replace("{officerId}", String.valueOf(id));
    }

    @Override
    public List<String> setTableColumnNames() {
        List<String> list = new ArrayList<>();

        list.add("National ID");
        list.add("Criminal Name");
        list.add("Address");
        list.add("City");
        list.add("State");
        list.add("Wanted By");
        list.add("Status");
        list.add("Actions");

        return list;
    }
}
