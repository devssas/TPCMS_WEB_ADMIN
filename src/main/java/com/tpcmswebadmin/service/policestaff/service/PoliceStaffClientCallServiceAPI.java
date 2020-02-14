package com.tpcmswebadmin.service.policestaff.service;

import com.ssas.tpcms.engine.vo.request.ViewOfficersProfileRequestVO;
import com.ssas.tpcms.engine.vo.response.TPEngineResponse;
import com.tpcmswebadmin.infrastructure.client.TPCMSClient;
import com.tpcmswebadmin.infrastructure.client.response.DataDto;
import com.tpcmswebadmin.infrastructure.client.response.ResponseDto;
import com.tpcmswebadmin.infrastructure.domain.LoginUserDo;
import com.tpcmswebadmin.infrastructure.domain.constant.TpCmsConstants;
import com.tpcmswebadmin.infrastructure.service.ClientCallServiceAPI;
import com.tpcmswebadmin.service.credentials.CredentialsService;
import com.tpcmswebadmin.service.credentials.domain.TpCmsWebAdminAppCredentials;
import com.tpcmswebadmin.service.policestaff.domain.dto.PoliceStaffDto;
import com.tpcmswebadmin.service.policestaff.service.mapper.PoliceStaffMapper;
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
public class PoliceStaffClientCallServiceAPI implements ClientCallServiceAPI<PoliceStaffDto, LoginUserDo, ViewOfficersProfileRequestVO> {

    private static final Logger logger = LoggerFactory.getLogger(PoliceStaffClientCallServiceAPI.class);

    private final TPCMSClient tpcmsClient;

    private final CredentialsService credentialsService;

    @Override
    public ResponseDto<PoliceStaffDto> getResponseDto(HttpServletRequest request) {
        LoginUserDo loginUserDo = LoginUserDo.builder()
                .loginOfficersCode((String) request.getSession().getAttribute(TpCmsConstants.OFFICER_CODE))
                .loginOfficerUnitNumber((String) request.getSession().getAttribute(TpCmsConstants.REPORT_UNIT))
                .build();

        TPEngineResponse response = makeClientCall(loginUserDo);

        return prepareResponseDto(PoliceStaffMapper.makePoliceStaffDtoList(response.getOfficersProfileList()));
    }

    @Override
    public TPEngineResponse makeClientCall(LoginUserDo loginUserDo) {
        ViewOfficersProfileRequestVO viewOfficersProfileRequestVO = new ViewOfficersProfileRequestVO();
        viewOfficersProfileRequestVO.setLoginOfficersCode(loginUserDo.getLoginOfficersCode());
        viewOfficersProfileRequestVO.setLoginOfficerUnitNumber(loginUserDo.getLoginOfficerUnitNumber());
        viewOfficersProfileRequestVO.setPageNumber(String.valueOf(loginUserDo.getPageNumber()));
        viewOfficersProfileRequestVO.setLimit(String.valueOf(loginUserDo.getLimit()));
        viewOfficersProfileRequestVO.setOfficerProfileSeeAll("Y");

        setCredentials(viewOfficersProfileRequestVO);

        try {
            logger.info("SignIn userName request will be sent to client. {}", viewOfficersProfileRequestVO.getMobileAppUserName());

            return tpcmsClient.tpcmsWebAdminClient().getTPCMSCoreServices().getOfficersProfileList(viewOfficersProfileRequestVO);
        } catch (RemoteException | ServiceException e) {
            logger.warn("Something wrong on signIn username request. " + viewOfficersProfileRequestVO.getMobileAppUserName());
        }
        return null;
    }

    @Override
    public ResponseDto<PoliceStaffDto> prepareResponseDto(List<PoliceStaffDto> list) {
        ResponseDto<PoliceStaffDto> responseDto = new ResponseDto<>();
        DataDto<PoliceStaffDto> dataDto = new DataDto<>();

        dataDto.setTbody(list);
        dataDto.setThead(setTableColumnNames());

        responseDto.setData(dataDto);
        responseDto.setMessage("status");
        responseDto.setStatus("true");

        return responseDto;
    }

    @Override
    public void setCredentials(ViewOfficersProfileRequestVO viewOfficersProfileRequestVO) {
        TpCmsWebAdminAppCredentials credentials = credentialsService.getCredentialsOfWebAdmin();

        viewOfficersProfileRequestVO.setMobileAppUserName(credentials.getMobileAppUserName());
        viewOfficersProfileRequestVO.setMobileAppDeviceId(TpCmsConstants.MOBILE_DEVICE_ID); //todo constant pass
        viewOfficersProfileRequestVO.setMobileAppPassword(credentials.getMobileAppPassword());
        viewOfficersProfileRequestVO.setMobileAppSmartSecurityKey(credentials.getMobileAppSmartSecurityKey());
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

        list.add("Officer ID");
        list.add("Officer Name");
        list.add("Address");
        list.add("City");
        list.add("State");
        list.add("Last Login");
        list.add("Status");
        list.add("Actions");

        return list;
    }

}
