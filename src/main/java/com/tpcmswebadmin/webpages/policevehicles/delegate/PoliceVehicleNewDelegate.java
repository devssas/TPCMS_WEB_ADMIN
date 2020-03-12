package com.tpcmswebadmin.webpages.policevehicles.delegate;

import com.ssas.tpcms.engine.vo.response.TPEngineResponse;
import com.tpcmswebadmin.infrastructure.domain.LoginUserDo;
import com.tpcmswebadmin.infrastructure.domain.constant.TpCmsConstants;
import com.tpcmswebadmin.service.policevehicles.service.PoliceVehicleCreateClientService;
import com.tpcmswebadmin.infrastructure.domain.dto.ResponseMVCDto;
import com.tpcmswebadmin.webpages.policevehicles.model.PoliceVehicleNewModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@RequiredArgsConstructor
public class PoliceVehicleNewDelegate {

    private final PoliceVehicleCreateClientService policeVehicleCreateClientService;

    public ResponseMVCDto createVehicle(PoliceVehicleNewModel policeVehicleNewModel, HttpServletRequest httpServletRequest) {
        LoginUserDo loginUserDo = makeLoginUser(httpServletRequest);
        TPEngineResponse response = policeVehicleCreateClientService.create(policeVehicleNewModel, loginUserDo);

         if(response.getResponseCodeVO().getResponseCode().startsWith("OPS")) {
             return ResponseMVCDto.builder()
                     .message(null)
                     .result(true)
                     .build();
         } else {
             return ResponseMVCDto.builder()
                     .message(response.getResponseCodeVO().getResponseValue())
                     .result(false)
                     .build();
         }
    }

    private LoginUserDo makeLoginUser(HttpServletRequest httpServletRequest) {
        return LoginUserDo.builder()
                .loginOfficersCode((String) httpServletRequest.getSession().getAttribute(TpCmsConstants.OFFICER_CODE))
                .loginOfficerUnitNumber((String) httpServletRequest.getSession().getAttribute(TpCmsConstants.REPORT_UNIT))
                .mobileAppDeviceId((String) httpServletRequest.getSession().getAttribute(TpCmsConstants.MOBILE_APP_DEVICE_ID))
                .build();
    }
}
