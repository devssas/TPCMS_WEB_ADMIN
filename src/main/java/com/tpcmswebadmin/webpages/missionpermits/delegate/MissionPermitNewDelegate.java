package com.tpcmswebadmin.webpages.missionpermits.delegate;

import com.ssas.tpcms.engine.vo.response.TPEngineResponse;
import com.tpcmswebadmin.service.missionpermits.service.MissionPermitsClientService;
import com.tpcmswebadmin.webpages.missionpermits.domain.model.MissionPermitCardCreateModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
public class MissionPermitNewDelegate {

    private final MissionPermitsClientService missionPermitsClientService;

    public boolean createNewMissionCard(MissionPermitCardCreateModel missionPermitCardCreateModel) {
        TPEngineResponse response = missionPermitsClientService.createNewMissionCard(missionPermitCardCreateModel);

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
