package com.tpcmswebadmin.webpages.card.delegate;


import com.tpcmswebadmin.service.administrator.domain.SupervisorCardDto;
import com.tpcmswebadmin.service.administrator.service.SupervisorClientService;
import com.tpcmswebadmin.webpages.card.domain.SupervisorCardModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@RequiredArgsConstructor
public class SupervisorCardDelegate {

    private final SupervisorClientService supervisorClientService;

    public SupervisorCardModel getSupervisorBySupervisorId(String supervisorId, HttpServletRequest httpServletRequest) {
        SupervisorCardDto supervisorCardDto = supervisorClientService.getSupervisorBySupervisorId(supervisorId, httpServletRequest);

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
