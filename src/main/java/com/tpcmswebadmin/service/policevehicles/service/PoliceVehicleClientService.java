package com.tpcmswebadmin.service.policevehicles.service;

import com.ssas.tpcms.engine.vo.request.ViewVehicleDetailsRequestVO;
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
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PoliceVehicleClientService implements ClientServiceAPI<PoliceVehicleDto, LoginUserDo, ViewVehicleDetailsRequestVO> {

    private final TPCMSClient tpcmsClient;

    private final CredentialsService credentialsService;

    public PoliceVehicleCardDto getPoliceVehiclesByVehicleId(String vehicleId, HttpServletRequest httpServletRequest) {
        LoginUserDo loginUserDo = LoginUserDo.builder()
                .loginOfficersCode((String) httpServletRequest.getSession().getAttribute(TpCmsConstants.OFFICER_CODE))
                .loginOfficerUnitNumber((String) httpServletRequest.getSession().getAttribute(TpCmsConstants.REPORT_UNIT))
                .build();

        ViewVehicleDetailsRequestVO viewVehicleDetailsRequestVO = new ViewVehicleDetailsRequestVO();
        viewVehicleDetailsRequestVO.setLoginOfficersCode(loginUserDo.getLoginOfficersCode());
        viewVehicleDetailsRequestVO.setUnitNumber(loginUserDo.getLoginOfficerUnitNumber());
        viewVehicleDetailsRequestVO.setVehicleId(vehicleId);

        setCredentials(viewVehicleDetailsRequestVO);

        try {
            log.info("Police vehicle details request will be sent to client. {}", viewVehicleDetailsRequestVO.getMobileAppUserName());

            return prepareVehicleCardDto(tpcmsClient.tpcmsWebAdminClient().getTPCMSCoreServices().getVehicleDetails(viewVehicleDetailsRequestVO));
        } catch (RemoteException | ServiceException e) {
            log.warn("Something wrong on police vehicle details request. " + viewVehicleDetailsRequestVO.getMobileAppUserName());
        }

        return null;
    }

    private PoliceVehicleCardDto prepareVehicleCardDto(TPEngineResponse vehicleDetails) {
        return PoliceVehicleCardDto.builder()
                .vehicleId(vehicleDetails.getVehicleDetailsList()[0].getVehicleId())
                .name(vehicleDetails.getVehicleDetailsList()[0].getVehicleName())
                .commandCenter(vehicleDetails.getVehicleDetailsList()[0].getCommandCenter())
                .unit(vehicleDetails.getVehicleDetailsList()[0].getUnitNumber())
                .plateNumber(vehicleDetails.getVehicleDetailsList()[0].getPlateNumber())
                .expiryDate(vehicleDetails.getVehicleDetailsList()[0].getExpiryDate())
                .weaponType(vehicleDetails.getVehicleDetailsList()[0].getAllowedWeaponType1())
                .weaponSrl(vehicleDetails.getVehicleDetailsList()[0].getWeaponSerialNumber1())
                .isCarryWeapon(vehicleDetails.getVehicleDetailsList()[0].getPermissionToCarryWeapon())
                .isNightPatrol(vehicleDetails.getVehicleDetailsList()[0].getPermissionForNightPatrol())
                .isCarryCivilians(vehicleDetails.getVehicleDetailsList()[0].getPermissionToCarryCivilians())
                .isCarryPrisoners(vehicleDetails.getVehicleDetailsList()[0].getPermissionToCarryPrisoners())
                .isDriverOutsideCity(vehicleDetails.getVehicleDetailsList()[0].getPermissionToDriverOutsideCity())
                .image(ImageUtility.convertToBase64image(vehicleDetails.getVehicleDetailsList()[0].getVehiclePhoto1()))
                .build();
    }

    @Override
    public ResponseDto<PoliceVehicleDto> getResponseDto(HttpServletRequest request) {
        LoginUserDo loginUserDo = LoginUserDo.builder()
                .loginOfficersCode((String) request.getSession().getAttribute(TpCmsConstants.OFFICER_CODE))
                .loginOfficerUnitNumber((String) request.getSession().getAttribute(TpCmsConstants.REPORT_UNIT))
                .build();

        TPEngineResponse response = makeClientCall(loginUserDo);

        return prepareResponseDto(PoliceVehicleMapper.makePoliceVehicleDtoList(response.getVehicleDetailsList()));
    }

    @Override
    public ResponseDto<PoliceVehicleDto> prepareResponseDto(List<PoliceVehicleDto> list) {
        ResponseDto<PoliceVehicleDto> responseDto = new ResponseDto<>();
        DataDto<PoliceVehicleDto> dataDto = new DataDto<>();

        dataDto.setTbody(list);
        dataDto.setThead(setTableColumnNames());

        responseDto.setData(dataDto);
        responseDto.setMessage("status");
        responseDto.setStatus("true");

        return responseDto;
    }

    @Override
    public TPEngineResponse makeClientCall(LoginUserDo loginUserDo) {
        ViewVehicleDetailsRequestVO viewVehicleDetailsRequestVO = new ViewVehicleDetailsRequestVO();
        viewVehicleDetailsRequestVO.setLoginOfficersCode(loginUserDo.getLoginOfficersCode());
        viewVehicleDetailsRequestVO.setPageNumber(String.valueOf(loginUserDo.getPageNumber()));
        viewVehicleDetailsRequestVO.setLimit(String.valueOf(loginUserDo.getLimit()));
        viewVehicleDetailsRequestVO.setVehicleDetailsSeeAll("Y");

        setCredentials(viewVehicleDetailsRequestVO);

        try {
            log.info("Get vehicle list request will be sent to client. {}", viewVehicleDetailsRequestVO.getMobileAppUserName());

            return tpcmsClient.tpcmsWebAdminClient().getTPCMSCoreServices().getVehicleDetails(viewVehicleDetailsRequestVO);
        } catch (RemoteException | ServiceException e) {
            log.warn("Something wrong on get vehicle list request. " + viewVehicleDetailsRequestVO.getMobileAppUserName());
        }
        return null;
    }

    @Override
    public void setCredentials(ViewVehicleDetailsRequestVO requestVO) {
        TpCmsWebAdminAppCredentials credentials = credentialsService.getCredentialsOfWebAdmin();

        requestVO.setMobileAppUserName(credentials.getMobileAppUserName());
        requestVO.setMobileAppDeviceId(TpCmsConstants.MOBILE_DEVICE_ID); //todo constant pass
        requestVO.setMobileAppPassword(credentials.getMobileAppPassword());
        requestVO.setMobileAppSmartSecurityKey(credentials.getMobileAppSmartSecurityKey());
    }

    @Override
    public List<String> setTableColumnNames() {
        List<String> list = new ArrayList<>();

        list.add("Vehicle ID");
        list.add("Type");
        list.add("Plate Number");
        list.add("City");
        list.add("State");
        list.add("Last Login");
        list.add("Status");
        list.add("Actions");

        return list;
    }
}
