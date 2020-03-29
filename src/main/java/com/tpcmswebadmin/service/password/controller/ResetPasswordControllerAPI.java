package com.tpcmswebadmin.service.password.controller;

import com.tpcmswebadmin.infrastructure.domain.LoginUserDo;
import com.tpcmswebadmin.service.password.service.ResetPasswordClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("api")
public class ResetPasswordControllerAPI {

    private final ResetPasswordClientService resetPasswordClientService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("password/reset")
    public ResponseEntity<Void> getSpecialMissions(@RequestParam("officerId") String officerId,
                                                   @RequestParam("officerProfileCode") String officerProfileCode,
                                                   HttpServletRequest httpServletRequest) {
        LoginUserDo loginUserDo = LoginUserDo.makeLoginUser(httpServletRequest);
        resetPasswordClientService.resetPassword(officerId, officerProfileCode, loginUserDo);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
