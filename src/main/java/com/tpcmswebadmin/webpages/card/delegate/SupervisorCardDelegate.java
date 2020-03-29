package com.tpcmswebadmin.webpages.card.delegate;


import com.tpcmswebadmin.infrastructure.domain.LoginUserDo;
import com.tpcmswebadmin.service.superadmin.domain.SupervisorCardDto;
import com.tpcmswebadmin.service.superadmin.service.SupervisorClientService;
import com.tpcmswebadmin.webpages.card.domain.SupervisorCardModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@RequiredArgsConstructor
public class SupervisorCardDelegate {

    private final SupervisorClientService supervisorClientService;

    public SupervisorCardModel getSupervisorBySupervisorId(String supervisorId, HttpServletRequest httpServletRequest) {
        LoginUserDo loginUserDo = LoginUserDo.makeLoginUser(httpServletRequest);
        SupervisorCardDto supervisorCardDto = supervisorClientService.getSupervisorBySupervisorId(supervisorId, loginUserDo);

        return SupervisorCardModel.builder()
                .officerName(supervisorCardDto.getOfficerName())
                .commandCenter(supervisorCardDto.getCommandCenter())
                .rank(supervisorCardDto.getRank())
                .unit(supervisorCardDto.getUnit())
                .officerId(supervisorCardDto.getOfficerId())
                .expiryDate(supervisorCardDto.getExpiryDate())
                .image(supervisorCardDto.getImage())
                .build();
    }

}
