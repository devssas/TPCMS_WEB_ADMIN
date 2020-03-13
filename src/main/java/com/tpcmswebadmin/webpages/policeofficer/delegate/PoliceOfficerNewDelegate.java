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
        LoginUserDo loginUserDo = makeLoginUser(httpServletRequest);
        TPEngineResponse response = policeOfficerCreateClientService.create(policeOfficerCreateModel, loginUserDo);

        if(response.getResponseCodeVO().getResponseCode().startsWith("OPS")) {
            return ResponseMVCDto.builder()
                    .message(null)
                    .result(true)
                    .build();
        } else {
            return ResponseMVCDto.builder()
                    .message(response.getResponseCodeVO().getResponseCode() + " - " + response.getResponseCodeVO().getResponseValue())
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
