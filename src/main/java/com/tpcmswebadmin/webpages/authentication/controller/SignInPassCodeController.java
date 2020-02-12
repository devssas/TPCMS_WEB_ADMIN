package com.tpcmswebadmin.webpages.authentication.controller;

import com.tpcmswebadmin.infrastructure.utils.StringUtility;
import com.tpcmswebadmin.service.authentication.domain.model.SignInPassCodeModel;
import com.tpcmswebadmin.service.authentication.domain.model.SignInUserCodeModel;
import com.tpcmswebadmin.webpages.authentication.delegate.SignInPassCodeDelegate;
import com.tpcmswebadmin.webpages.authentication.domain.SignInPassCodeDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class SignInPassCodeController {

    private final SignInPassCodeDelegate signInPassCodeDelegate;

    public SignInPassCodeController(SignInPassCodeDelegate signInPassCodeDelegate) {
        this.signInPassCodeDelegate = signInPassCodeDelegate;
    }

    @GetMapping("/signInPassCode")
    public String getSignInUsernamePage(Model model) {
        model.addAttribute("signInPassCodeModel", new SignInPassCodeModel());

        return "signInPassCode";
    }

    @PostMapping("/signInPassCode")
    public String getSignInPassCodePage(@Valid @ModelAttribute("signInPassCodeModel") SignInPassCodeModel signInPassCodeModel, BindingResult bindingResult, HttpServletRequest request) {
        signInPassCodeModel.setUserName((String) request.getSession().getAttribute("username"));
        signInPassCodeModel.setUserCode((String) request.getSession().getAttribute("userCode"));

        String passCode = generatePassCode(signInPassCodeModel);
        signInPassCodeModel.setPassCodeFull(passCode);

        SignInPassCodeDto signInPassCodeDto = signInPassCodeDelegate.signInPassCode(signInPassCodeModel);

        if (signInPassCodeDto.isHasResult()) {
            request.getSession().setAttribute("officerCode", signInPassCodeDto.getOfficerCode());
            request.getSession().setAttribute("reportUnit", signInPassCodeDto.getReportUnit());

            return "dashboard";
        } else {
            return "redirect:signInPassCode";
        }
    }

    private String generatePassCode(SignInPassCodeModel signInPassCodeModel) {
        return StringUtility.concat(signInPassCodeModel.getPassCode1(), signInPassCodeModel.getPassCode2(), signInPassCodeModel.getPassCode3());
    }

}
