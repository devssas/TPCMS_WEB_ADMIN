package com.tpcmswebadmin.webpages.missionpermits.delegate;

import com.ssas.tpcms.engine.vo.response.TPEngineResponse;
import com.tpcmswebadmin.infrastructure.domain.LoginUserDo;
import com.tpcmswebadmin.infrastructure.domain.constant.TpCmsConstants;
import com.tpcmswebadmin.infrastructure.domain.dto.ResponseMVCDto;
import com.tpcmswebadmin.service.missionpermits.domain.MissionCardDto;
import com.tpcmswebadmin.service.missionpermits.service.MissionPermitsClientService;
import com.tpcmswebadmin.service.missionpermits.service.MissionPermitsUpdateClientService;
import com.tpcmswebadmin.webpages.missionpermits.model.MissionPermitCardUpdateModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Component
@RequiredArgsConstructor
public class MissionPermitsUpdateDelegate {

    private final MissionPermitsClientService missionPermitsClientService;

    private final MissionPermitsUpdateClientService missionPermitsUpdateClientService;

    public MissionPermitCardUpdateModel getMissionCard(String missionId, String missionQrCode, HttpServletRequest httpServletRequest) {
        LoginUserDo loginUserDo = LoginUserDo.makeLoginUser(httpServletRequest);
        MissionCardDto missionCardDto = missionPermitsClientService.getSpecialMissionsByMissionId(missionId, missionQrCode, loginUserDo);

        return MissionPermitCardUpdateModel.builder()
                .officerId(missionCardDto.getOfficerId())
                .officerName(missionCardDto.getOfficerName())
                .commandCenter(missionCardDto.getCommandCenter())
                .permittedToCarryWeapon(missionCardDto.getIsPermittedCarryWeapon().equals("Y"))
                .weaponType(missionCardDto.getWeaponType())
                .missionType(missionCardDto.getMissionType())
                .missionDescription(missionCardDto.getMissionDescription())
                .activationDate(missionCardDto.getActivationDate())
                .expiryDate(missionCardDto.getExpiryDate())
                .additionalRemarks(missionCardDto.getAdditionalRemarks())
                .build();
    }

    public ResponseMVCDto updateMissionPermitCard(String missionId, String missionQrCode, MissionPermitCardUpdateModel missionPermitCardUpdateModel, HttpServletRequest httpServletRequest) {
        LoginUserDo loginUserDo = LoginUserDo.makeLoginUser(httpServletRequest);
        missionPermitCardUpdateModel.setMissionId(missionId);
        missionPermitCardUpdateModel.setMissionQrCode(missionQrCode);
        TPEngineResponse response = missionPermitsUpdateClientService.update(missionPermitCardUpdateModel, loginUserDo);

        return ResponseMVCDto.prepareResponse(response);
    }

}
