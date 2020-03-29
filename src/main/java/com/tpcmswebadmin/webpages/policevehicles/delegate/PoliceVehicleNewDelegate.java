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
        LoginUserDo loginUserDo = LoginUserDo.makeLoginUser(httpServletRequest);
        TPEngineResponse response = policeVehicleCreateClientService.create(policeVehicleNewModel, loginUserDo);

        return ResponseMVCDto.prepareResponse(response);
    }

}
