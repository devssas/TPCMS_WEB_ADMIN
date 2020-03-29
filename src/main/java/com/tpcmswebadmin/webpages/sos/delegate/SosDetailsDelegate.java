package com.tpcmswebadmin.webpages.sos.delegate;

import com.ssas.tpcms.engine.vo.response.TPEngineResponse;
import com.tpcmswebadmin.infrastructure.domain.LoginUserDo;
import com.tpcmswebadmin.infrastructure.domain.constant.TpCmsConstants;
import com.tpcmswebadmin.infrastructure.domain.dto.ResponseMVCDto;
import com.tpcmswebadmin.infrastructure.utils.DateUtility;
import com.tpcmswebadmin.infrastructure.utils.StringUtility;
import com.tpcmswebadmin.service.sos.service.SosClientService;
import com.tpcmswebadmin.service.sos.service.SosUpdateClientService;
import com.tpcmswebadmin.webpages.sos.model.SosCallModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Component
@RequiredArgsConstructor
public class SosDetailsDelegate {

    private final SosClientService sosClientService;

    private final SosUpdateClientService sosUpdateClientService;

    public SosCallModel getSosDetailsById(String sosId, HttpServletRequest httpServletRequest) {
        LoginUserDo loginUserDo = makeLoginUser(httpServletRequest);

        TPEngineResponse response = sosClientService.makeClientCall(sosId, loginUserDo);

        return SosCallModel.builder()
                .id(response.getSosRequestList()[0].getSosRequestId())
                .location("Lat: " + response.getSosRequestList()[0].getLatitudeDetails().substring(0,9) + " - Lng: " +response.getSosRequestList()[0].getLongitudeDetails().substring(0,9))
                .phoneNumber(response.getSosRequestList()[0].getContactMobileNumber())
                .username(StringUtility.makeFullName(response.getSosRequestList()[0].getOfficerFirstName_Ar(), response.getSosRequestList()[0].getOfficerLastName_Ar()))
                .requestDate(DateUtility.convertToFormat(response.getSosRequestList()[0].getRequestDate(), "dd/MM/YYYY"))
                .requestSerialNumber("N/A") //todo ask
                .remarks(response.getSosRequestList()[0].getAdditionalRemarks() == null ? "N/A" : response.getSosRequestList()[0].getAdditionalRemarks())
                .status(response.getSosRequestList()[0].getStatusCode())
                .build();
    }

    private LoginUserDo makeLoginUser(HttpServletRequest httpServletRequest) {
        return LoginUserDo.builder()
                .loginOfficersCode((String) httpServletRequest.getSession().getAttribute(TpCmsConstants.OFFICER_CODE))
                .loginOfficerUnitNumber((String) httpServletRequest.getSession().getAttribute(TpCmsConstants.REPORT_UNIT))
                .mobileAppDeviceId((String) httpServletRequest.getSession().getAttribute(TpCmsConstants.MOBILE_APP_DEVICE_ID))
                .build();
    }

    public ResponseMVCDto updateSos(SosCallModel sosCallUpdateModel, HttpServletRequest httpServletRequest) {
        LoginUserDo loginUserDo = LoginUserDo.makeLoginUser(httpServletRequest);
        TPEngineResponse response = sosUpdateClientService.update(sosCallUpdateModel, loginUserDo);

        return ResponseMVCDto.prepareResponse(response);
    }
}
