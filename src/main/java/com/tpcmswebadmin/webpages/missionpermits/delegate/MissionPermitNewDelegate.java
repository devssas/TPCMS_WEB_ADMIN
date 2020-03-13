package com.tpcmswebadmin.webpages.missionpermits.delegate;

import com.ssas.tpcms.engine.vo.response.TPEngineResponse;
import com.tpcmswebadmin.infrastructure.domain.LoginUserDo;
import com.tpcmswebadmin.infrastructure.domain.constant.TpCmsConstants;
import com.tpcmswebadmin.infrastructure.domain.dto.ResponseMVCDto;
import com.tpcmswebadmin.service.missionpermits.service.MissionPermitsClientService;
import com.tpcmswebadmin.service.missionpermits.service.MissionPermitsCreateClientService;
import com.tpcmswebadmin.webpages.missionpermits.model.MissionPermitCardCreateModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;


@Slf4j
@Component
@RequiredArgsConstructor
public class MissionPermitNewDelegate {

    private final MissionPermitsCreateClientService missionPermitsCreateClientService;

    public ResponseMVCDto createNewMissionCard(MissionPermitCardCreateModel missionPermitCardCreateModel, HttpServletRequest request) {
        LoginUserDo loginUserDo = makeLoginUser(request);
        TPEngineResponse response = missionPermitsCreateClientService.create(missionPermitCardCreateModel, loginUserDo);

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
