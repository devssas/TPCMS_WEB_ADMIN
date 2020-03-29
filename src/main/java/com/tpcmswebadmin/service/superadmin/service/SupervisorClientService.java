package com.tpcmswebadmin.service.superadmin.service;

import com.ssas.tpcms.engine.vo.request.ViewOfficersProfileRequestVO;
import com.ssas.tpcms.engine.vo.response.TPEngineResponse;
import com.tpcmswebadmin.infrastructure.client.TPCMSClient;
import com.tpcmswebadmin.infrastructure.client.response.DataDto;
import com.tpcmswebadmin.infrastructure.client.response.ResponseAPIDto;
import com.tpcmswebadmin.infrastructure.domain.LoginUserDo;
import com.tpcmswebadmin.infrastructure.service.ClientServiceAPI;
import com.tpcmswebadmin.infrastructure.utils.ImageUtility;
import com.tpcmswebadmin.infrastructure.utils.StringUtility;
import com.tpcmswebadmin.service.superadmin.domain.SupervisorCardDto;
import com.tpcmswebadmin.service.superadmin.domain.SupervisorDto;
import com.tpcmswebadmin.service.superadmin.service.mapper.SupervisorMapper;
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

    public TPEngineResponse getSupervisorBySupervisorIdRaw(String supervisorId, LoginUserDo loginUserDo) {
        ViewOfficersProfileRequestVO viewOfficersProfileRequestVO = new ViewOfficersProfileRequestVO();
        viewOfficersProfileRequestVO.setOfficerProfileId(supervisorId);

        setFullCredentials(viewOfficersProfileRequestVO, loginUserDo);

        try {
            log.info("Get supervisor Profile request will be sent to client. {}", supervisorId);

            return tpcmsClient.tpcmsWebAdminClient().getTPCMSCoreServices().getOfficersProfileList(viewOfficersProfileRequestVO);
        } catch (RemoteException | ServiceException e) {
            log.warn("Something wrong on Officers Profile list request. {}", e.getMessage());
        }
        return null;
    }

    public SupervisorCardDto getSupervisorBySupervisorId(String supervisorId, LoginUserDo loginUserDo) {
        ViewOfficersProfileRequestVO viewOfficersProfileRequestVO = new ViewOfficersProfileRequestVO();
        viewOfficersProfileRequestVO.setOfficerProfileId(supervisorId);

        setFullCredentials(viewOfficersProfileRequestVO, loginUserDo);

        try {
            log.info("Get supervisor Profile request will be sent to client. {}", supervisorId);

            return prepareSupervisorCardDto(tpcmsClient.tpcmsWebAdminClient().getTPCMSCoreServices().getOfficersProfileList(viewOfficersProfileRequestVO));
        } catch (RemoteException | ServiceException e) {
            log.warn("Something wrong on Officers Profile list request. {}", e.getMessage());
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
                .expiryDate(officersProfileList.getOfficersProfileList()[0].getExpiryDate() == null ? "N/A" : officersProfileList.getOfficersProfileList()[0].getExpiryDate())
                .image(ImageUtility.convertToBase64image(officersProfileList.getOfficersProfileList()[0].getProfilePhoto1()))
                .build();
    }

    @Override
    public ResponseAPIDto<SupervisorDto> getResponseDto(HttpServletRequest request) {
        LoginUserDo loginUserDo = LoginUserDo.makeLoginUser(request);

        TPEngineResponse response = makeClientCall(loginUserDo);

        return prepareResponseDto(SupervisorMapper.makeSupervisorDtoList(response.getOfficersProfileList()), true, response);
    }

    @Override
    public ResponseAPIDto<SupervisorDto> prepareResponseDto(List<SupervisorDto> list, boolean status, TPEngineResponse response) {
        ResponseAPIDto<SupervisorDto> responseAPIDto = new ResponseAPIDto<>();
        DataDto<SupervisorDto> dataDto = new DataDto<>();

        dataDto.setTbody(list);
        dataDto.setThead(setTableColumnNames());

        responseAPIDto.setData(dataDto);
        responseAPIDto.setMessage("status");
        responseAPIDto.setStatus("true");

        return responseAPIDto;
    }

    @Override
    public TPEngineResponse makeClientCall(LoginUserDo loginUserDo) {
        ViewOfficersProfileRequestVO viewOfficersProfileRequestVO = new ViewOfficersProfileRequestVO();
        viewOfficersProfileRequestVO.setPageNumber(String.valueOf(loginUserDo.getPageNumber()));
        viewOfficersProfileRequestVO.setLimit(String.valueOf(loginUserDo.getLimit()));
        viewOfficersProfileRequestVO.setOfficerProfileSeeAll("Y");

        setFullCredentials(viewOfficersProfileRequestVO, loginUserDo);

        try {
            log.info("Get Officers Profile list request will be sent to client. {}", viewOfficersProfileRequestVO.getMobileAppUserName());

            return tpcmsClient.tpcmsWebAdminClient().getTPCMSCoreServices().getOfficersProfileList(viewOfficersProfileRequestVO);
        } catch (RemoteException | ServiceException e) {
            log.warn("Something wrong on Officers Profile list request. {}", e.getMessage());
        }
        return null;
    }

    public void setFullCredentials(ViewOfficersProfileRequestVO requestVO, LoginUserDo loginUserDo) {
        requestVO.setLoginOfficerUnitNumber(loginUserDo.getLoginOfficerUnitNumber());
        requestVO.setLoginOfficersCode(loginUserDo.getLoginOfficersCode());
        requestVO.setMobileAppDeviceId(loginUserDo.getMobileAppDeviceId());
        requestVO.setLoginOfficersCode(loginUserDo.getLoginOfficersCode());
        requestVO.setMobileAppDeviceId(loginUserDo.getMobileAppDeviceId());

        setCredentials(requestVO);
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
