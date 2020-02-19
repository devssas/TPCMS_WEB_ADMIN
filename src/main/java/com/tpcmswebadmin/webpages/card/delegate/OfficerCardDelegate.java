package com.tpcmswebadmin.webpages.card.delegate;

import com.tpcmswebadmin.service.policestaff.domain.dto.PoliceOfficerDto;
import com.tpcmswebadmin.service.policestaff.service.PoliceStaffClientService;
import com.tpcmswebadmin.webpages.card.domain.OfficerCardModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@RequiredArgsConstructor
public class OfficerCardDelegate {

    private final PoliceStaffClientService policeStaffClientService;

    public OfficerCardModel getPoliceOfficerByOfficerId(String officerId, HttpServletRequest httpServletRequest) {
        PoliceOfficerDto policeOfficerDto = policeStaffClientService.getPoliceOfficerByOfficerId(officerId, httpServletRequest);

        return OfficerCardModel.builder()
                .commandCenter(policeOfficerDto.getCommandCenter())
                .officerId(policeOfficerDto.getOfficerCode())
                .officerName(policeOfficerDto.getOfficerName())
                .expiryDate(policeOfficerDto.getExpiryDate())
                .unit(policeOfficerDto.getUnit())
                .rank(policeOfficerDto.getRank())
                .weaponType(policeOfficerDto.getWeaponType())
                .weaponSrl(policeOfficerDto.getWeaponSrl())
                .isCarryWeapon(policeOfficerDto.getIsCarryWeapon())
                .bloodGroup(policeOfficerDto.getBloodGroup())
                .image(policeOfficerDto.getImage())
                .build();
    }
}
