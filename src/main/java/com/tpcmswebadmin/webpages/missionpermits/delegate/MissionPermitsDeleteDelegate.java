package com.tpcmswebadmin.webpages.missionpermits.delegate;

import com.ssas.tpcms.engine.vo.response.TPEngineResponse;
import com.tpcmswebadmin.infrastructure.domain.LoginUserDo;
import com.tpcmswebadmin.infrastructure.domain.constant.TpCmsConstants;
import com.tpcmswebadmin.infrastructure.domain.dto.ResponseMVCDto;
import com.tpcmswebadmin.service.missionpermits.service.MissionPermitsDeleteClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Component
@RequiredArgsConstructor
public class MissionPermitsDeleteDelegate {

    private final MissionPermitsDeleteClientService missionPermitsDeleteClientService;

    public ResponseMVCDto delete(String missionId, String missionQrCode, String officerId, HttpServletRequest httpServletRequest) {
        LoginUserDo loginUserDo = LoginUserDo.makeLoginUser(httpServletRequest);
        TPEngineResponse response = missionPermitsDeleteClientService.delete(missionId, missionQrCode, officerId, loginUserDo);

        return ResponseMVCDto.prepareResponse(response);
    }

}
