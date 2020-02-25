package com.tpcmswebadmin.service.administrator.service;

import com.ssas.tpcms.engine.vo.request.ViewOfficersProfileRequestVO;
import com.ssas.tpcms.engine.vo.response.TPEngineResponse;
import com.tpcmswebadmin.infrastructure.client.TPCMSClient;
import com.tpcmswebadmin.infrastructure.client.response.DataDto;
import com.tpcmswebadmin.infrastructure.client.response.ResponseDto;
import com.tpcmswebadmin.infrastructure.domain.LoginUserDo;
import com.tpcmswebadmin.infrastructure.domain.constant.TpCmsConstants;
import com.tpcmswebadmin.infrastructure.service.ClientServiceAPI;
import com.tpcmswebadmin.infrastructure.utils.ImageUtility;
import com.tpcmswebadmin.infrastructure.utils.StringUtility;
import com.tpcmswebadmin.service.administrator.domain.SupervisorCardDto;
import com.tpcmswebadmin.service.administrator.domain.SupervisorDto;
import com.tpcmswebadmin.service.administrator.service.mapper.AdministratorMapper;
import com.tpcmswebadmin.service.credentials.CredentialsService;
import com.tpcmswebadmin.service.credentials.domain.TpCmsWebAdminAppCredentials;
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
public class SupervisorClientService implements ClientServiceAPI<SupervisorDto, LoginUserDo, ViewOfficersProfileRequestVO> {

    private final TPCMSClient tpcmsClient;

    private final CredentialsService credentialsService;

    public SupervisorCardDto getSupervisorBySupervisorId(String supervisorId, HttpServletRequest httpServletRequest) {
        ViewOfficersProfileRequestVO viewOfficersProfileRequestVO = new ViewOfficersProfileRequestVO();
        viewOfficersProfileRequestVO.setLoginOfficersCode("LYeGOV55397TP"); //todo test static and change
        viewOfficersProfileRequestVO.setLoginOfficerUnitNumber("105"); //todo static and change
        viewOfficersProfileRequestVO.setOfficerProfileId(supervisorId);

        viewOfficersProfileRequestVO.setMobileAppDeviceId((String) httpServletRequest.getSession().getAttribute(TpCmsConstants.MOBILE_APP_DEVICE_ID));
        setCredentials(viewOfficersProfileRequestVO);

        try {
            log.info("Get Officers Profile list request will be sent to client. {}", viewOfficersProfileRequestVO.getMobileAppUserName());

            return prepareSupervisorCardDto(tpcmsClient.tpcmsWebAdminClient().getTPCMSCoreServices().getOfficersProfileList(viewOfficersProfileRequestVO));
        } catch (RemoteException | ServiceException e) {
            log.warn("Something wrong on Officers Profile list request. " + viewOfficersProfileRequestVO.getMobileAppUserName());
        }
        return null;
    }

    private SupervisorCardDto prepareSupervisorCardDto(TPEngineResponse officersProfileList) {
        return SupervisorCardDto.builder()
                .officerName(StringUtility.makeFullName(officersProfileList.getOfficersProfileList()[0].getOfficer_FirstName_Ar(), officersProfileList.getOfficersProfileList()[0].getOfficer_LastName_Ar()))
                .officerId(officersProfileList.getOfficersProfileList()[0].getOfficerProfileId())
                .commandCenter(officersProfileList.getOfficersProfileList()[0].getContactAddress())
                .rank(officersProfileList.getOfficersProfileList()[0].getOfficersRank())
                .unit(officersProfileList.getOfficersProfileList()[0].getReportingUnit())
                .expiryDate(officersProfileList.getOfficersProfileList()[0].getExpiryDate())
                .image(ImageUtility.convertToBase64image(officersProfileList.getOfficersProfileList()[0].getProfilePhoto1()))
                .build();
    }

    @Override
    public ResponseDto<SupervisorDto> getResponseDto(HttpServletRequest request) {
        LoginUserDo loginUserDo = LoginUserDo.builder()
                .loginOfficersCode((String) request.getSession().getAttribute(TpCmsConstants.OFFICER_CODE))
                .loginOfficerUnitNumber((String) request.getSession().getAttribute(TpCmsConstants.REPORT_UNIT))
                .mobileAppDeviceId((String) request.getSession().getAttribute(TpCmsConstants.MOBILE_APP_DEVICE_ID))
                .build();

        TPEngineResponse response = makeClientCall(loginUserDo);

        return prepareResponseDto(AdministratorMapper.makeSupervisorDtoList(response.getOfficersProfileList()));
    }

    @Override
    public ResponseDto<SupervisorDto> prepareResponseDto(List<SupervisorDto> list) {
        ResponseDto<SupervisorDto> responseDto = new ResponseDto<>();
        DataDto<SupervisorDto> dataDto = new DataDto<>();

        dataDto.setTbody(list);
        dataDto.setThead(setTableColumnNames());

        responseDto.setData(dataDto);
        responseDto.setMessage("status");
        responseDto.setStatus("true");

        return responseDto;
    }

    @Override
    public TPEngineResponse makeClientCall(LoginUserDo loginUserDo) {
        ViewOfficersProfileRequestVO viewOfficersProfileRequestVO = new ViewOfficersProfileRequestVO();
        viewOfficersProfileRequestVO.setLoginOfficersCode("LYeGOV55397TP"); //todo test static and change
        viewOfficersProfileRequestVO.setLoginOfficerUnitNumber("105"); //todo static and change
        viewOfficersProfileRequestVO.setPageNumber(String.valueOf(loginUserDo.getPageNumber()));
        viewOfficersProfileRequestVO.setLimit(String.valueOf(loginUserDo.getLimit()));
        viewOfficersProfileRequestVO.setOfficerProfileSeeAll("Y");
        viewOfficersProfileRequestVO.setAccessRoleCode("ADMIN");

        setCredentials(viewOfficersProfileRequestVO);

        try {
            log.info("Get Officers Profile list request will be sent to client. {}", viewOfficersProfileRequestVO.getMobileAppUserName());

            return tpcmsClient.tpcmsWebAdminClient().getTPCMSCoreServices().getOfficersProfileList(viewOfficersProfileRequestVO);
        } catch (RemoteException | ServiceException e) {
            log.warn("Something wrong on Officers Profile list request. " + viewOfficersProfileRequestVO.getMobileAppUserName());
        }
        return null;
    }

    @Override
    public void setCredentials(ViewOfficersProfileRequestVO requestVO) {
        TpCmsWebAdminAppCredentials credentials = credentialsService.getCredentialsOfWebAdmin();

        requestVO.setMobileAppUserName(credentials.getMobileAppUserName());
        requestVO.setMobileAppPassword(credentials.getMobileAppPassword());
        requestVO.setMobileAppSmartSecurityKey(credentials.getMobileAppSmartSecurityKey());
    }

    @Override
    public List<String> setTableColumnNames() {
        List<String> list = new ArrayList<>();

        list.add("Admin ID");
        list.add("Name");
        list.add("Address");
        list.add("City");
        list.add("Access Role");
        list.add("Last Login");
        list.add("Status");
        list.add("Actions");

        return list;
    }


}
