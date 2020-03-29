package com.tpcmswebadmin.webpages.policeofficer.delegate;

import com.ssas.tpcms.engine.vo.response.TPEngineResponse;
import com.tpcmswebadmin.infrastructure.domain.LoginUserDo;
import com.tpcmswebadmin.infrastructure.domain.constant.TpCmsConstants;
import com.tpcmswebadmin.infrastructure.domain.dto.ResponseMVCDto;
import com.tpcmswebadmin.service.policeofficer.service.PoliceOfficerCreateClientService;
import com.tpcmswebadmin.webpages.policeofficer.model.PoliceOfficerCreateModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@RequiredArgsConstructor
public class PoliceOfficerNewDelegate {

    private final PoliceOfficerCreateClientService policeOfficerCreateClientService;

    public ResponseMVCDto createOfficer(PoliceOfficerCreateModel policeOfficerCreateModel, HttpServletRequest httpServletRequest) {
        LoginUserDo loginUserDo = LoginUserDo.makeLoginUser(httpServletRequest);
        TPEngineResponse response = policeOfficerCreateClientService.create(policeOfficerCreateModel, loginUserDo);

        return ResponseMVCDto.prepareResponse(response);
    }

}
