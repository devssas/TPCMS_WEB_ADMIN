package com.tpcmswebadmin.webpages.card.delegate;

import com.tpcmswebadmin.service.missionpermits.domain.MissionCardDto;
import com.tpcmswebadmin.service.missionpermits.service.MissionPermitsClientService;
import com.tpcmswebadmin.webpages.card.domain.MissionCardModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@RequiredArgsConstructor
public class MissionCardDelegate {

    private final MissionPermitsClientService missionPermitsClientService;

    public MissionCardModel getMissionCardByMissionId(String missionId, String missionQrCode, HttpServletRequest httpServletRequest) {
        MissionCardDto missionCardDto = missionPermitsClientService.getSpecialMissionsByMissionId(missionId, missionQrCode, httpServletRequest);

        return MissionCardModel.builder()
                .officerName(missionCardDto.getOfficerName())
                .commandCenter(missionCardDto.getCommandCenter())
                .officerId(missionCardDto.getOfficerId())
                .rank(missionCardDto.getRank())
                .unit(missionCardDto.getUnit())
                .officerId(missionCardDto.getOfficerId())
                .expiryDate(missionCardDto.getExpiryDate())
                .isPermittedCarryWeapon(missionCardDto.getIsPermittedCarryWeapon())
                .weaponType(missionCardDto.getWeaponType())
                .missionType(missionCardDto.getMissionType())
                .missionDescription(missionCardDto.getMissionDescription())
                .image(missionCardDto.getImage())
                .build();
    }
}
