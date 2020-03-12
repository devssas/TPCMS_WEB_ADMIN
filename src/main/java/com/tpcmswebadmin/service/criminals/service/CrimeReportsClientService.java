package com.tpcmswebadmin.service.criminals.service;

import com.ssas.tpcms.engine.vo.request.ViewCrimeReportRequestVO;
import com.ssas.tpcms.engine.vo.response.TPEngineResponse;
import com.tpcmswebadmin.infrastructure.client.TPCMSClient;
import com.tpcmswebadmin.infrastructure.client.response.DataDto;
import com.tpcmswebadmin.infrastructure.client.response.ResponseAPIDto;
import com.tpcmswebadmin.infrastructure.domain.LoginUserDo;
import com.tpcmswebadmin.infrastructure.domain.constant.TpCmsConstants;
import com.tpcmswebadmin.infrastructure.service.ClientServiceAPI;
import com.tpcmswebadmin.infrastructure.utils.ImageUtility;
import com.tpcmswebadmin.service.credentials.CredentialsService;
import com.tpcmswebadmin.service.credentials.domain.TpCmsWebAdminAppCredentials;
import com.tpcmswebadmin.service.criminals.domain.card.CrimeReportCard;
import com.tpcmswebadmin.service.criminals.domain.dto.CrimeReportDto;
import com.tpcmswebadmin.service.criminals.service.mapper.CrimeReportsMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CrimeReportsClientService implements ClientServiceAPI<CrimeReportDto, LoginUserDo, ViewCrimeReportRequestVO> {

    private final TPCMSClient tpcmsClient;

    private final CredentialsService credentialsService;

    public CrimeReportCard getCrimeReportCardByCrimeReportId(String crimeReportId, HttpServletRequest httpServletRequest) {
        ViewCrimeReportRequestVO viewCrimeReportRequestVO = new ViewCrimeReportRequestVO();
        viewCrimeReportRequestVO.setLoginOfficersCode((String) httpServletRequest.getSession().getAttribute(TpCmsConstants.OFFICER_CODE));
        viewCrimeReportRequestVO.setCrimeReportsId(crimeReportId);

        viewCrimeReportRequestVO.setMobileAppDeviceId((String) httpServletRequest.getSession().getAttribute(TpCmsConstants.MOBILE_APP_DEVICE_ID));
        setCredentials(viewCrimeReportRequestVO);

        try {
            log.info("CrimeReportCard ById Request will be sent to client. {}", viewCrimeReportRequestVO.getMobileAppUserName());

            return prepareResponse(tpcmsClient.tpcmsWebAdminClient().getTPCMSCoreServices().getCrimeReports(viewCrimeReportRequestVO));
        } catch (RemoteException | ServiceException e) {
            log.warn("Something wrong on CrimeReportCard ById request. " + viewCrimeReportRequestVO.getMobileAppUserName());
        }
        return null;
    }

    private CrimeReportCard prepareResponse(TPEngineResponse tpEngineResponse) {
        return CrimeReportCard.builder()
                .crimeTitle(tpEngineResponse.getCrimeReportList()[0].getCrimeName())
                .reportId(tpEngineResponse.getCrimeReportList()[0].getCrimeReportsId())
                .status(tpEngineResponse.getCrimeReportList()[0].getCrimianlStatusCode())
                .crimeScene(tpEngineResponse.getCrimeReportList()[0].getCrimeScene())
                .suspects(tpEngineResponse.getCrimeReportList()[0].getListOfSuspects())
                .reportedDate(tpEngineResponse.getCrimeReportList()[0].getReportedDate())
                .images(Arrays.asList(ImageUtility.convertToBase64image(tpEngineResponse.getCrimeReportList()[0].getCasePhoto1()), ImageUtility.convertToBase64image(tpEngineResponse.getCrimeReportList()[0].getCasePhoto2()),
                                      ImageUtility.convertToBase64image(tpEngineResponse.getCrimeReportList()[0].getCasePhoto3()), ImageUtility.convertToBase64image(tpEngineResponse.getCrimeReportList()[0].getCasePhoto4()),
                                      ImageUtility.convertToBase64image(tpEngineResponse.getCrimeReportList()[0].getCasePhoto5()), ImageUtility.convertToBase64image(tpEngineResponse.getCrimeReportList()[0].getCasePhoto6()),
                                      ImageUtility.convertToBase64image(tpEngineResponse.getCrimeReportList()[0].getCasePhoto7()), ImageUtility.convertToBase64image(tpEngineResponse.getCrimeReportList()[0].getCasePhoto8()),
                                      ImageUtility.convertToBase64image(tpEngineResponse.getCrimeReportList()[0].getCasePhoto9()), ImageUtility.convertToBase64image(tpEngineResponse.getCrimeReportList()[0].getCasePhoto10())))
                .caseBrief(tpEngineResponse.getCrimeReportList()[0].getCaseBriefDesc())
                .build();
    }

    @Override
    public ResponseAPIDto<CrimeReportDto> getResponseDto(HttpServletRequest request) {
        LoginUserDo loginUserDo = LoginUserDo.builder()
                .loginOfficersCode((String) request.getSession().getAttribute(TpCmsConstants.OFFICER_CODE))
                .loginOfficerUnitNumber((String) request.getSession().getAttribute(TpCmsConstants.REPORT_UNIT))
                .mobileAppDeviceId((String) request.getSession().getAttribute(TpCmsConstants.MOBILE_APP_DEVICE_ID))
                .build();

        TPEngineResponse response = makeClientCall(loginUserDo);

        return prepareResponseDto(CrimeReportsMapper.makeCrimeReportDtoList(response.getCrimeReportList()), true, response);
    }

    @Override
    public ResponseAPIDto<CrimeReportDto> prepareResponseDto(List<CrimeReportDto> list, boolean status, TPEngineResponse response) {
        ResponseAPIDto<CrimeReportDto> responseAPIDto = new ResponseAPIDto<>();
        DataDto<CrimeReportDto> dataDto = new DataDto<>();

        dataDto.setTbody(list);
        dataDto.setThead(setTableColumnNames());

        responseAPIDto.setData(dataDto);
        responseAPIDto.setMessage("status");
        responseAPIDto.setStatus("true");

        return responseAPIDto;
    }

    @Override
    public TPEngineResponse makeClientCall(LoginUserDo loginUserDo) {
        ViewCrimeReportRequestVO viewCrimeReportRequestVO = new ViewCrimeReportRequestVO();
        viewCrimeReportRequestVO.setLoginOfficersCode(loginUserDo.getLoginOfficersCode());
        viewCrimeReportRequestVO.setPageNumber(String.valueOf(loginUserDo.getPageNumber()));
        viewCrimeReportRequestVO.setLimit(String.valueOf(loginUserDo.getLimit()));
        viewCrimeReportRequestVO.setCrimeReportSeeAll("Y");

        viewCrimeReportRequestVO.setMobileAppDeviceId(loginUserDo.getMobileAppDeviceId());
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
        requestVO.setMobileAppPassword(credentials.getMobileAppPassword());
        requestVO.setMobileAppSmartSecurityKey(credentials.getMobileAppSmartSecurityKey());
    }

    @Override
    public List<String> setTableColumnNames() {
        List<String> list = new ArrayList<>();

        list.add("Report ID");
        list.add("Officer Name");
        list.add("Address");
        list.add("City");
        list.add("Created");
        list.add("Wanted By");
        list.add("Status");
        list.add("Actions");

        return list;
    }

}
