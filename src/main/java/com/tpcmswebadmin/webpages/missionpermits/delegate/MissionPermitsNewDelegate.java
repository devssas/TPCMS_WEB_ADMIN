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
public class MissionPermitsNewDelegate {

    private final MissionPermitsCreateClientService missionPermitsCreateClientService;

    public ResponseMVCDto createNewMissionCard(MissionPermitCardCreateModel missionPermitCardCreateModel, HttpServletRequest request) {
        LoginUserDo loginUserDo = LoginUserDo.makeLoginUser(request);
        TPEngineResponse response = missionPermitsCreateClientService.create(missionPermitCardCreateModel, loginUserDo);

        return ResponseMVCDto.prepareResponse(response);
    }

}
