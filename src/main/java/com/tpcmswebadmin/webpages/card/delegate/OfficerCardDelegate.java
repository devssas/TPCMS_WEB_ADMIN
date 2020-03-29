package com.tpcmswebadmin.webpages.card.delegate;

import com.tpcmswebadmin.infrastructure.domain.LoginUserDo;
import com.tpcmswebadmin.service.policeofficer.domain.dto.PoliceOfficerCardDto;
import com.tpcmswebadmin.service.policeofficer.service.PoliceOfficerClientService;
import com.tpcmswebadmin.webpages.card.domain.OfficerCardModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@RequiredArgsConstructor
public class OfficerCardDelegate {

    private final PoliceOfficerClientService policeOfficerClientService;

    public OfficerCardModel getPoliceOfficerByOfficerId(String officerId, HttpServletRequest httpServletRequest) {
        LoginUserDo loginUserDo = LoginUserDo.makeLoginUser(httpServletRequest);
        PoliceOfficerCardDto policeOfficerCardDto = policeOfficerClientService.getPoliceOfficerByOfficerId(officerId, loginUserDo);

        return OfficerCardModel.builder()
                .commandCenter(policeOfficerCardDto.getCommandCenter())
                .officerId(policeOfficerCardDto.getOfficerCode())
                .officerName(policeOfficerCardDto.getOfficerName())
                .expiryDate(policeOfficerCardDto.getExpiryDate())
                .unit(policeOfficerCardDto.getUnit())
                .rank(policeOfficerCardDto.getRank())
                .weaponType(policeOfficerCardDto.getWeaponType())
                .weaponSrl(policeOfficerCardDto.getWeaponSrl())
                .isCarryWeapon(policeOfficerCardDto.getIsPermittedCarryWeapon())
                .bloodGroup(policeOfficerCardDto.getBloodGroup())
                .image(policeOfficerCardDto.getImage())
                .build();
    }
}
