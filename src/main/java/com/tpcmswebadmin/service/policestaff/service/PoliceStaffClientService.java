package com.tpcmswebadmin.service.policestaff.service;

import com.ssas.tpcms.engine.vo.request.ViewOfficersProfileRequestVO;
import com.ssas.tpcms.engine.vo.response.TPEngineResponse;
import com.tpcmswebadmin.infrastructure.client.TPCMSClient;
import com.tpcmswebadmin.infrastructure.client.response.DataDto;
import com.tpcmswebadmin.infrastructure.client.response.ResponseDto;
import com.tpcmswebadmin.infrastructure.domain.LoginUserDo;
import com.tpcmswebadmin.infrastructure.domain.constant.TpCmsConstants;
import com.tpcmswebadmin.infrastructure.service.ClientServiceAPI;
import com.tpcmswebadmin.infrastructure.utils.ImageUtility;
import com.tpcmswebadmin.service.credentials.CredentialsService;
import com.tpcmswebadmin.service.credentials.domain.TpCmsWebAdminAppCredentials;
import com.tpcmswebadmin.service.policestaff.domain.dto.PoliceOfficerDto;
import com.tpcmswebadmin.service.policestaff.domain.dto.PoliceStaffDto;
import com.tpcmswebadmin.service.policestaff.service.mapper.PoliceStaffMapper;
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
public class PoliceStaffClientService implements ClientServiceAPI<PoliceStaffDto, LoginUserDo, ViewOfficersProfileRequestVO> {

    private final TPCMSClient tpcmsClient;

    private final CredentialsService credentialsService;

    public PoliceOfficerDto getPoliceOfficerByOfficerId(String officerId, HttpServletRequest httpServletRequest) {
        LoginUserDo loginUserDo = LoginUserDo.builder()
                .loginOfficersCode((String) httpServletRequest.getSession().getAttribute(TpCmsConstants.OFFICER_CODE))
                .loginOfficerUnitNumber((String) httpServletRequest.getSession().getAttribute(TpCmsConstants.REPORT_UNIT))
                .build();

        ViewOfficersProfileRequestVO viewOfficersProfileRequestVO = new ViewOfficersProfileRequestVO();
        viewOfficersProfileRequestVO.setLoginOfficersCode(loginUserDo.getLoginOfficersCode());
        viewOfficersProfileRequestVO.setLoginOfficerUnitNumber(loginUserDo.getLoginOfficerUnitNumber());
        viewOfficersProfileRequestVO.setOfficerProfileId(officerId);

        setCredentials(viewOfficersProfileRequestVO);

        try {
            log.info("SignIn userName request will be sent to client. {}", viewOfficersProfileRequestVO.getMobileAppUserName());

            return preparePoliceOfficerDto(tpcmsClient.tpcmsWebAdminClient().getTPCMSCoreServices().getOfficersProfileList(viewOfficersProfileRequestVO));
        } catch (RemoteException | ServiceException e) {
            log.warn("Something wrong on signIn username request. " + viewOfficersProfileRequestVO.getMobileAppUserName());
        }
        return null;
    }

    private PoliceOfficerDto preparePoliceOfficerDto(TPEngineResponse tpEngineResponse) {
        return PoliceOfficerDto.builder()
                .commandCenter(tpEngineResponse.getOfficersProfileList()[0].getContactAddress())
                .officerCode(tpEngineResponse.getOfficersProfileList()[0].getOfficerCode())
                .officerName(tpEngineResponse.getOfficersProfileList()[0].getOfficer_FirstName_Ar() + " " + tpEngineResponse.getOfficersProfileList()[0].getOfficer_LastName_Ar())
                .expiryDate(tpEngineResponse.getOfficersProfileList()[0].getExpiryDate())
                .unit(tpEngineResponse.getOfficersProfileList()[0].getReportingUnit())
                .rank(tpEngineResponse.getOfficersProfileList()[0].getOfficersRank())
                .weaponType(tpEngineResponse.getOfficersProfileList()[0].getAllowedWeaponType())
                .weaponSrl(tpEngineResponse.getOfficersProfileList()[0].getWeaponSerialNumber())
                .isCarryWeapon(tpEngineResponse.getOfficersProfileList()[0].getPermissionToCarryWeapon())
                .bloodGroup(tpEngineResponse.getOfficersProfileList()[0].getBloogroup())
                .image(ImageUtility.convertToBase64image(tpEngineResponse.getOfficersProfileList()[0].getProfilePhoto1()))
                .build();
    }

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
        viewOfficersProfileRequestVO.setAccessRoleCode("OFFICER");

        setCredentials(viewOfficersProfileRequestVO);

        try {
            log.info("SignIn userName request will be sent to client. {}", viewOfficersProfileRequestVO.getMobileAppUserName());

            return tpcmsClient.tpcmsWebAdminClient().getTPCMSCoreServices().getOfficersProfileList(viewOfficersProfileRequestVO);
        } catch (RemoteException | ServiceException e) {
            log.warn("Something wrong on signIn username request. " + viewOfficersProfileRequestVO.getMobileAppUserName());
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
    public List<String> setTableColumnNames() {
        List<String> list = new ArrayList<>();

        list.add("Officer Code");
        list.add("Officer Name");
        list.add("Address");
        list.add("City");
        list.add("Access Code");
        list.add("Last Login");
        list.add("Status");
        list.add("Actions");

        return list;
    }

}
