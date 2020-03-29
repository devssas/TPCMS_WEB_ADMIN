package com.tpcmswebadmin.webpages.password;

import com.tpcmswebadmin.infrastructure.domain.LoginUserDo;
import com.tpcmswebadmin.service.password.service.ResetPasswordClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class ResetPasswordController {

    private final ResetPasswordClientService resetPasswordClientService;

    @GetMapping("/resetPassword")
    public String getCrimeReportCard(@RequestParam("officerId") String officerId,
                                     @RequestParam("officerProfileCode") String officerProfileCode,
                                     HttpServletRequest httpServletRequest) {
        LoginUserDo loginUserDo = LoginUserDo.makeLoginUser(httpServletRequest);
        resetPasswordClientService.resetPassword(officerId, officerProfileCode, loginUserDo);

        return "redirect:/officerProfiles";
    }
}
