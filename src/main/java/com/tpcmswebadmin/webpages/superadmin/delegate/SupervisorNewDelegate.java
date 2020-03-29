package com.tpcmswebadmin.webpages.superadmin.delegate;

import com.ssas.tpcms.engine.vo.response.TPEngineResponse;
import com.tpcmswebadmin.infrastructure.domain.LoginUserDo;
import com.tpcmswebadmin.infrastructure.domain.dto.ResponseMVCDto;
import com.tpcmswebadmin.service.superadmin.service.SupervisorCreateClientService;
import com.tpcmswebadmin.webpages.superadmin.model.SupervisorNewModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Component
@RequiredArgsConstructor
public class SupervisorNewDelegate {

    private final SupervisorCreateClientService supervisorCreateClientService;

    public ResponseMVCDto createSupervisor(SupervisorNewModel supervisorNewModel, HttpServletRequest httpServletRequest) {
        LoginUserDo loginUserDo = LoginUserDo.makeLoginUser(httpServletRequest);
        TPEngineResponse response = supervisorCreateClientService.create(supervisorNewModel, loginUserDo);

        return ResponseMVCDto.prepareResponse(response);
    }
}
