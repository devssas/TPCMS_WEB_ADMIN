package com.tpcmswebadmin.service.policevehicles.service;

import com.ssas.tpcms.engine.vo.request.ViewVehicleDetailsRequestVO;
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
import com.tpcmswebadmin.service.policevehicles.domain.dto.PoliceVehicleCardDto;
import com.tpcmswebadmin.service.policevehicles.domain.dto.PoliceVehicleDto;
import com.tpcmswebadmin.service.policevehicles.service.mapper.PoliceVehicleMapper;
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
public class PoliceVehicleClientService implements ClientServiceAPI<PoliceVehicleDto, LoginUserDo, ViewVehicleDetailsRequestVO> {

    private final TPCMSClient tpcmsClient;

    private final CredentialsService credentialsService;

    public PoliceVehicleCardDto getPoliceVehiclesByVehicleId(String vehicleId, LoginUserDo loginUserDo) {
        ViewVehicleDetailsRequestVO viewVehicleDetailsRequestVO = new ViewVehicleDetailsRequestVO();
        viewVehicleDetailsRequestVO.setVehicleId(vehicleId);

        setFullCredentials(viewVehicleDetailsRequestVO, loginUserDo);

        try {
            log.info("Police vehicle details request will be sent to client. {}", viewVehicleDetailsRequestVO.getMobileAppUserName());

            return prepareVehicleCardDto(tpcmsClient.tpcmsWebAdminClient().getTPCMSCoreServices().getVehicleDetails(viewVehicleDetailsRequestVO));
        } catch (RemoteException | ServiceException e) {
            log.warn("Something wrong on police vehicle details request. " + e.getMessage());
        }

        return null;
    }

    private PoliceVehicleCardDto prepareVehicleCardDto(TPEngineResponse vehicleDetails) {
        return PoliceVehicleCardDto.builder()
                .vehicleId(vehicleDetails.getVehicleDetailsList()[0].getVehicleId())
                .name(vehicleDetails.getVehicleDetailsList()[0].getVehicleName())
                .commandCenter(vehicleDetails.getVehicleDetailsList()[0].getCommandCenter())
                .unit(vehicleDetails.getVehicleDetailsList()[0].getUnitNumber())
                .chaseNumber(vehicleDetails.getVehicleDetailsList()[0].getChasisNumber())
                .plateNumber(vehicleDetails.getVehicleDetailsList()[0].getPlateNumber())
                .activationDate(vehicleDetails.getVehicleDetailsList()[0].getActivationDate())
                .expiryDate(vehicleDetails.getVehicleDetailsList()[0].getExpiryDate())
                .weaponType(vehicleDetails.getVehicleDetailsList()[0].getAllowedWeaponType1())
                .weaponSrl(vehicleDetails.getVehicleDetailsList()[0].getWeaponSerialNumber1())
                .isCarryWeapon(vehicleDetails.getVehicleDetailsList()[0].getPermissionToCarryWeapon())
                .isNightPatrol(vehicleDetails.getVehicleDetailsList()[0].getPermissionForNightPatrol())
                .isCarryCivilians(vehicleDetails.getVehicleDetailsList()[0].getPermissionToCarryCivilians())
                .isCarryPrisoners(vehicleDetails.getVehicleDetailsList()[0].getPermissionToCarryPrisoners())
                .isDriverOutsideCity(vehicleDetails.getVehicleDetailsList()[0].getPermissionToDriverOutsideCity())
                .image(ImageUtility.convertToBase64image(vehicleDetails.getVehicleDetailsList()[0].getVehiclePhoto1()))
                .driverOfficerId1(vehicleDetails.getVehicleDetailsList()[0].getDriverOfficerId_1())
                .driverOfficerId2(vehicleDetails.getVehicleDetailsList()[0].getDriverOfficerId_2())
                .additionalRemarks(vehicleDetails.getVehicleDetailsList()[0].getAdditionalRemarks())
                .vehicleQrCode(vehicleDetails.getVehicleDetailsList()[0].getVehicleQRCode())
                .statusCode(vehicleDetails.getVehicleDetailsList()[0].getStatusCode())
                .vehicleDetailsId(vehicleDetails.getVehicleDetailsList()[0].getVehicleDetailsId())
                .build();
    }

    @Override
    public ResponseAPIDto<PoliceVehicleDto> getResponseDto(HttpServletRequest request) {
        String searchKey =  request.getSession().getAttribute("search") == null ? null : (String) request.getSession().getAttribute("search");
        String status =  request.getSession().getAttribute("status") == null ? null : (String) request.getSession().getAttribute("status");

        LoginUserDo loginUserDo = LoginUserDo.builder()
                .accessRole((String) request.getSession().getAttribute(TpCmsConstants.ACCESS_ROLE))
                .loginOfficersCode((String) request.getSession().getAttribute(TpCmsConstants.OFFICER_CODE))
                .loginOfficerUnitNumber((String) request.getSession().getAttribute(TpCmsConstants.REPORT_UNIT))
                .mobileAppDeviceId((String) request.getSession().getAttribute(TpCmsConstants.MOBILE_APP_DEVICE_ID))
                .reportingUnit((String) request.getSession().getAttribute(TpCmsConstants.REPORT_UNIT))
                .searchKey(searchKey)
                .status(status)
                .build();

        TPEngineResponse response = makeClientCall(loginUserDo);

        if (response.getVehicleDetailsList() == null)
            return prepareResponseDto(Collections.emptyList(), false, response);
        else
            return prepareResponseDto(PoliceVehicleMapper.makePoliceVehicleDtoList(response.getVehicleDetailsList()), true, response);
    }

    @Override
    public ResponseAPIDto<PoliceVehicleDto> prepareResponseDto(List<PoliceVehicleDto> list, boolean status, TPEngineResponse response) {
        ResponseAPIDto<PoliceVehicleDto> responseAPIDto = new ResponseAPIDto<>();
        DataDto<PoliceVehicleDto> dataDto = new DataDto<>();

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
            responseAPIDto.setMessage(response.getResponseCodeVO().getResponseCode() + " - " + response.getResponseCodeVO().getResponseValue());
            responseAPIDto.setStatus("false");
        }

        return responseAPIDto;
    }

    @Override
    public TPEngineResponse makeClientCall(LoginUserDo loginUserDo) {
        ViewVehicleDetailsRequestVO viewVehicleDetailsRequestVO = new ViewVehicleDetailsRequestVO();
        viewVehicleDetailsRequestVO.setPageNumber(String.valueOf(loginUserDo.getPageNumber()));
        viewVehicleDetailsRequestVO.setLimit(String.valueOf(loginUserDo.getLimit()));
        viewVehicleDetailsRequestVO.setVehicleDetailsSeeAll("Y");

        setFullCredentials(viewVehicleDetailsRequestVO, loginUserDo);

        try {
            log.info("Get vehicle list request will be sent to client. {}", viewVehicleDetailsRequestVO.getMobileAppUserName());

            return tpcmsClient.tpcmsWebAdminClient().getTPCMSCoreServices().getVehicleDetails(viewVehicleDetailsRequestVO);
        } catch (RemoteException | ServiceException e) {
            log.warn("Something wrong on get vehicle list request. " + viewVehicleDetailsRequestVO.getMobileAppUserName());
        }
        return null;
    }

    public void setFullCredentials(ViewVehicleDetailsRequestVO requestVO, LoginUserDo loginUserDo) {
        requestVO.setAccessRoleCode(loginUserDo.getAccessRole());

        if(loginUserDo.getAccessRole().equals("ADMIN"))
            requestVO.setUnitNumber(loginUserDo.getReportingUnit());

        requestVO.setLoginOfficersCode(loginUserDo.getLoginOfficersCode());
        requestVO.setMobileAppDeviceId(loginUserDo.getMobileAppDeviceId());

        setCredentials(requestVO);
    }

    @Override
    public void setCredentials(ViewVehicleDetailsRequestVO requestVO) {
        TpCmsWebAdminAppCredentials credentials = credentialsService.getCredentialsOfWebAdmin();

        requestVO.setMobileAppUserName(credentials.getMobileAppUserName());
        requestVO.setMobileAppPassword(credentials.getMobileAppPassword());
        requestVO.setMobileAppSmartSecurityKey(credentials.getMobileAppSmartSecurityKey());
    }

    @Override
    public List<String> setTableColumnNames() {
        List<String> list = new ArrayList<>();

        list.add("Vehicle ID");
        list.add("Weapon Type");
        list.add("Vehicle Name");
        list.add("Plate Number");
        list.add("Chase Number");
        list.add("Command Center");
        list.add("Status");
        list.add("Actions");

        return list;
    }
}
