package com.tpcmswebadmin.service.policeofficer.service;

import com.ssas.tpcms.engine.vo.request.ViewOfficersProfileRequestVO;
import com.ssas.tpcms.engine.vo.response.CriminalsProfileResponseVO;
import com.ssas.tpcms.engine.vo.response.OfficersProfileResponseVO;
import com.ssas.tpcms.engine.vo.response.TPEngineResponse;
import com.tpcmswebadmin.infrastructure.client.TPCMSClient;
import com.tpcmswebadmin.infrastructure.domain.LoginUserDo;
import com.tpcmswebadmin.infrastructure.domain.constant.TpCmsConstants;
import com.tpcmswebadmin.infrastructure.domain.dto.ResponseSearchDataDto;
import com.tpcmswebadmin.infrastructure.domain.dto.ResponseTBodyDto;
import com.tpcmswebadmin.infrastructure.utils.StringUtility;
import com.tpcmswebadmin.service.credentials.CredentialsService;
import com.tpcmswebadmin.service.credentials.domain.TpCmsWebAdminAppCredentials;
import com.tpcmswebadmin.service.policeofficer.domain.dto.PoliceOfficerSearchDto;
import com.tpcmswebadmin.service.policeofficer.domain.dto.SearchDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PoliceOfficerSearchClientService {

    private final TPCMSClient tpcmsClient;

    private final CredentialsService credentialsService;

    public ResponseSearchDataDto<PoliceOfficerSearchDto> search(SearchDto searchDto, HttpServletRequest httpServletRequest) {
        ViewOfficersProfileRequestVO viewOfficersProfileRequestVO = new ViewOfficersProfileRequestVO();
        LoginUserDo loginUserDo = makeLoginUser(httpServletRequest);

        viewOfficersProfileRequestVO.setLoginOfficersCode(loginUserDo.getLoginOfficersCode());
        viewOfficersProfileRequestVO.setLoginOfficerUnitNumber(loginUserDo.getLoginOfficerUnitNumber());
        viewOfficersProfileRequestVO.setPageNumber(String.valueOf(loginUserDo.getPageNumber()));
        viewOfficersProfileRequestVO.setLimit(String.valueOf(loginUserDo.getLimit()));
        viewOfficersProfileRequestVO.setOfficerProfileSeeAll("Y");
        viewOfficersProfileRequestVO.setAccessRoleCode("OFFICER");

        viewOfficersProfileRequestVO.setMobileAppDeviceId(loginUserDo.getMobileAppDeviceId());
        setCredentials(viewOfficersProfileRequestVO, loginUserDo);

        try {
            log.info("SignIn userName request will be sent to client. {}", viewOfficersProfileRequestVO.getMobileAppUserName());

            return makeSearchResponse(tpcmsClient.tpcmsWebAdminClient().getTPCMSCoreServices().getOfficersProfileList(viewOfficersProfileRequestVO));
        } catch (RemoteException | ServiceException e) {
            log.warn("Something wrong on signIn username request. " + e.getMessage());
        }
        return null;
    }

    private ResponseSearchDataDto<PoliceOfficerSearchDto> makeSearchResponse(TPEngineResponse tpEngineResponse) {
        List<PoliceOfficerSearchDto> list = Arrays.stream(tpEngineResponse.getOfficersProfileList())
                .map(PoliceOfficerSearchClientService::makePoliceOfficerSearchDto)
                .collect(Collectors.toList());

        ResponseTBodyDto<PoliceOfficerSearchDto> responseTBodyDto = new ResponseTBodyDto();
        responseTBodyDto.setTbody(list);

        return ResponseSearchDataDto.<PoliceOfficerSearchDto>builder()
                .data(responseTBodyDto)
                .message("success")
                .status("true")
                .build();
    }

    private static PoliceOfficerSearchDto makePoliceOfficerSearchDto(OfficersProfileResponseVO officersProfileResponseVO) {
        return PoliceOfficerSearchDto.builder()
                .officerName(StringUtility.makeFullName(officersProfileResponseVO.getOfficer_FirstName_Ar(), officersProfileResponseVO.getOfficer_LastName_Ar()))
                .commandCenter(officersProfileResponseVO.getCommandCenterId())
                .officerId(officersProfileResponseVO.getOfficerProfileId())
                .build();
    }

    private LoginUserDo makeLoginUser(HttpServletRequest httpServletRequest) {
        return LoginUserDo.builder()
                .loginOfficersCode((String) httpServletRequest.getSession().getAttribute(TpCmsConstants.OFFICER_CODE))
                .loginOfficerUnitNumber((String) httpServletRequest.getSession().getAttribute(TpCmsConstants.REPORT_UNIT))
                .mobileAppDeviceId((String) httpServletRequest.getSession().getAttribute(TpCmsConstants.MOBILE_APP_DEVICE_ID))
                .build();
    }

    private void setCredentials(ViewOfficersProfileRequestVO viewOfficersProfileRequestVO, LoginUserDo loginUserDo) {
        TpCmsWebAdminAppCredentials credentials = credentialsService.getCredentialsOfWebAdmin();

        viewOfficersProfileRequestVO.setLoginOfficersCode(loginUserDo.getLoginOfficersCode());
        viewOfficersProfileRequestVO.setMobileAppUserName(credentials.getMobileAppUserName());
        viewOfficersProfileRequestVO.setMobileAppPassword(credentials.getMobileAppPassword());
        viewOfficersProfileRequestVO.setMobileAppSmartSecurityKey(credentials.getMobileAppSmartSecurityKey());
    }

}
