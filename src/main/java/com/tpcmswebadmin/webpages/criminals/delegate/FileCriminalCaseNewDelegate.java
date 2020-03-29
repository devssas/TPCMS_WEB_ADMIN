package com.tpcmswebadmin.webpages.criminals.delegate;

import com.ssas.tpcms.engine.vo.response.TPEngineResponse;
import com.tpcmswebadmin.infrastructure.domain.LoginUserDo;
import com.tpcmswebadmin.infrastructure.domain.dto.ResponseMVCDto;
import com.tpcmswebadmin.service.criminals.service.CriminalCaseCreateService;
import com.tpcmswebadmin.webpages.criminals.model.FileCriminalCaseCreateModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Component
@RequiredArgsConstructor
public class FileCriminalCaseNewDelegate {

    private final CriminalCaseCreateService criminalCaseCreateService;

    public ResponseMVCDto createCriminalCase(FileCriminalCaseCreateModel fileCriminalCaseCreateModel, HttpServletRequest httpServletRequest) {
        LoginUserDo loginUserDo = LoginUserDo.makeLoginUser(httpServletRequest);
        TPEngineResponse response = criminalCaseCreateService.create(fileCriminalCaseCreateModel, loginUserDo);

        return ResponseMVCDto.prepareResponse(response);
    }

}
