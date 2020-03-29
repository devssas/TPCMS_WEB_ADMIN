package com.tpcmswebadmin.webpages.policevehicles.delegate;

import com.ssas.tpcms.engine.vo.response.TPEngineResponse;
import com.tpcmswebadmin.infrastructure.domain.LoginUserDo;
import com.tpcmswebadmin.infrastructure.domain.constant.TpCmsConstants;
import com.tpcmswebadmin.infrastructure.domain.dto.ResponseMVCDto;
import com.tpcmswebadmin.service.policevehicles.service.PoliceVehicleDeleteClientService;
import com.tpcmswebadmin.webpages.policevehicles.model.PoliceVehicleUpdateModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Component
@RequiredArgsConstructor
public class PoliceVehicleDeleteDelegate {

    private final PoliceVehicleDeleteClientService policeVehicleDeleteClientService;

    public ResponseMVCDto delete(String vehicleId, HttpServletRequest httpServletRequest) {
        LoginUserDo loginUserDo = LoginUserDo.makeLoginUser(httpServletRequest);
        TPEngineResponse response = policeVehicleDeleteClientService.delete(vehicleId, loginUserDo);

        return ResponseMVCDto.prepareResponse(response);
    }

}
