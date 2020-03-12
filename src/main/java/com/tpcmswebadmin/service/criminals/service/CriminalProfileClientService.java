package com.tpcmswebadmin.service.criminals.service;

import com.ssas.tpcms.engine.vo.request.ViewCriminalProfileRequestVO;
import com.ssas.tpcms.engine.vo.response.TPEngineResponse;
import com.tpcmswebadmin.infrastructure.client.TPCMSClient;
import com.tpcmswebadmin.infrastructure.client.response.DataDto;
import com.tpcmswebadmin.infrastructure.client.response.ResponseAPIDto;
import com.tpcmswebadmin.infrastructure.domain.LoginUserDo;
import com.tpcmswebadmin.infrastructure.domain.constant.TpCmsConstants;
import com.tpcmswebadmin.infrastructure.service.ClientServiceAPI;
import com.tpcmswebadmin.infrastructure.utils.ImageUtility;
import com.tpcmswebadmin.infrastructure.utils.StringUtility;
import com.tpcmswebadmin.service.credentials.CredentialsService;
import com.tpcmswebadmin.service.credentials.domain.TpCmsWebAdminAppCredentials;
import com.tpcmswebadmin.service.criminals.domain.card.ManageCasesCard;
import com.tpcmswebadmin.service.criminals.domain.dto.CasesDto;
import com.tpcmswebadmin.service.criminals.service.mapper.CriminalProfileMapper;
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
public class CriminalProfileClientService implements ClientServiceAPI<CasesDto, LoginUserDo, ViewCriminalProfileRequestVO> {

    private final TPCMSClient tpcmsClient;

    private final CredentialsService credentialsService;

    public ManageCasesCard getCriminalCaseCardByCriminalsCode(String criminalsCode, HttpServletRequest httpServletRequest) {
        ViewCriminalProfileRequestVO viewCriminalProfileRequestVO = new ViewCriminalProfileRequestVO();
        viewCriminalProfileRequestVO.setLoginOfficersCode((String) httpServletRequest.getSession().getAttribute(TpCmsConstants.OFFICER_CODE));
        viewCriminalProfileRequestVO.setMobileAppDeviceId((String) httpServletRequest.getSession().getAttribute(TpCmsConstants.MOBILE_APP_DEVICE_ID));
        viewCriminalProfileRequestVO.setCriminalsCode(criminalsCode);

        setCredentials(viewCriminalProfileRequestVO);

        try {
            log.info("ManageCasesCard ById Request will be sent to client. {}", viewCriminalProfileRequestVO.getMobileAppUserName());

            return prepareResponse(tpcmsClient.tpcmsWebAdminClient().getTPCMSCoreServices().getCriminalsProfile(viewCriminalProfileRequestVO));
        } catch (RemoteException | ServiceException e) {
            log.warn("Something wrong on ManageCasesCard ById request. " + viewCriminalProfileRequestVO.getMobileAppUserName());
        }
        return null;
    }

    private ManageCasesCard prepareResponse(TPEngineResponse tpEngineResponse) {
        return ManageCasesCard.builder()
                .crimeType(tpEngineResponse.getCriminalProfileList()[0].getCrimeName())
                .criminalName(StringUtility.makeFullName(tpEngineResponse.getCriminalProfileList()[0].getFirstName_Ar(), tpEngineResponse.getCriminalProfileList()[0].getLastName_Ar()))
                .caseId(tpEngineResponse.getCriminalProfileList()[0].getCaseId())
                .status(tpEngineResponse.getCriminalProfileList()[0].getCrimianlStatusCode())
                .requestUnit(tpEngineResponse.getCriminalProfileList()[0].getTypeOfCrime())
                .images(ImageUtility.convertToBase64image(tpEngineResponse.getCriminalProfileList()[0].getProfilePhoto1()))
                .caseBrief(tpEngineResponse.getCriminalProfileList()[0].getCaseBriefDesc())
                .date(tpEngineResponse.getCriminalProfileList()[0].getFlagedDate())
                .build();
    }

    @Override
    public ResponseAPIDto<CasesDto> getResponseDto(HttpServletRequest request) {
        LoginUserDo loginUserDo = LoginUserDo.builder()
                .loginOfficersCode((String) request.getSession().getAttribute(TpCmsConstants.OFFICER_CODE))
                .loginOfficerUnitNumber((String) request.getSession().getAttribute(TpCmsConstants.REPORT_UNIT))
                .mobileAppDeviceId((String) request.getSession().getAttribute(TpCmsConstants.MOBILE_APP_DEVICE_ID))
                .build();

        TPEngineResponse response = makeClientCall(loginUserDo);

        return prepareResponseDto(CriminalProfileMapper.makeCasesDtoList(response.getCriminalProfileList()), true, response);
    }

    @Override
    public ResponseAPIDto<CasesDto> prepareResponseDto(List<CasesDto> list, boolean status, TPEngineResponse response) {
        ResponseAPIDto<CasesDto> responseAPIDto = new ResponseAPIDto<>();
        DataDto<CasesDto> dataDto = new DataDto<>();

        dataDto.setTbody(list);
        dataDto.setThead(setTableColumnNames());

        responseAPIDto.setData(dataDto);
        responseAPIDto.setMessage("status");
        responseAPIDto.setStatus("true");

        return responseAPIDto;
    }

    @Override
    public TPEngineResponse makeClientCall(LoginUserDo loginUserDo) {
        ViewCriminalProfileRequestVO viewCriminalProfileRequestVO = new ViewCriminalProfileRequestVO();
        viewCriminalProfileRequestVO.setLoginOfficersCode(loginUserDo.getLoginOfficersCode());
        viewCriminalProfileRequestVO.setPageNumber(String.valueOf(loginUserDo.getPageNumber()));
        viewCriminalProfileRequestVO.setLimit(String.valueOf(loginUserDo.getLimit()));
        viewCriminalProfileRequestVO.setCriminalsProfileSeeAll("Y");

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

        list.add("National ID");
        list.add("Criminal Name");
        list.add("Criminals Code");
        list.add("Address");
        list.add("City");
        list.add("Status");
        list.add("Actions");

        return list;
    }

}
