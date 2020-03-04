package com.tpcmswebadmin.webpages.missionpermits.delegate;

import com.ssas.tpcms.engine.vo.response.TPEngineResponse;
import com.tpcmswebadmin.infrastructure.domain.LoginUserDo;
import com.tpcmswebadmin.infrastructure.domain.constant.TpCmsConstants;
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

    public boolean createNewMissionCard(MissionPermitCardCreateModel missionPermitCardCreateModel, HttpServletRequest request) {
        LoginUserDo loginUserDo = new LoginUserDo();
        loginUserDo.setMobileAppDeviceId((String) request.getSession().getAttribute(TpCmsConstants.MOBILE_APP_DEVICE_ID));

        TPEngineResponse response = missionPermitsCreateClientService.create(missionPermitCardCreateModel, loginUserDo);

        if (response.getResponseCodeVO().getResponseCode().startsWith("OPS")) {
            log.info("New mission permit card created {}", response.getResponseCodeVO().getResponseCode());
            return true;
        }
        else {
            log.warn("New mission permit card cant be created {}", response.getResponseCodeVO().getResponseMessage());
            return false;
        }
    }

}
