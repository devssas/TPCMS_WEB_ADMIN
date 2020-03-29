package com.tpcmswebadmin.webpages.policeofficer.delegate;

import com.ssas.tpcms.engine.vo.response.TPEngineResponse;
import com.tpcmswebadmin.infrastructure.domain.LoginUserDo;
import com.tpcmswebadmin.infrastructure.domain.dto.ResponseMVCDto;
import com.tpcmswebadmin.service.policeofficer.service.PoliceOfficerDeleteClientService;
import com.tpcmswebadmin.service.policevehicles.service.PoliceVehicleDeleteClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@RequiredArgsConstructor
public class PoliceOfficerDeleteDelegate {

    private final PoliceOfficerDeleteClientService policeOfficerDeleteClientService;

    public ResponseMVCDto delete(String officerId, HttpServletRequest httpServletRequest) {
        LoginUserDo loginUserDo = LoginUserDo.makeLoginUser(httpServletRequest);
        TPEngineResponse response = policeOfficerDeleteClientService.delete(officerId, loginUserDo);

        return ResponseMVCDto.prepareResponse(response);
    }
}
