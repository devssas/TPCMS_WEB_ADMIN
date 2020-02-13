package com.tpcmswebadmin.webpages.authentication.controller;

import com.tpcmswebadmin.infrastructure.utils.StringUtility;
import com.tpcmswebadmin.service.authentication.domain.model.SignInUserCodeModel;
import com.tpcmswebadmin.webpages.authentication.delegate.SignInUserCodeDelegate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class SignInUserCodeController {

    private final SignInUserCodeDelegate signInUserCodeDelegate;

    public SignInUserCodeController(SignInUserCodeDelegate signInUserCodeDelegate) {
        this.signInUserCodeDelegate = signInUserCodeDelegate;
    }

    @GetMapping("/signInUserCode")
    public String getSignInUserCode(Model model) {
        model.addAttribute("signInUserCodeModel", new SignInUserCodeModel());

        return "signin_usercode";
    }

    @PostMapping("/signInUserCode")
    public String signInWithUserCode(@Valid @ModelAttribute("signInUserCodeModel") SignInUserCodeModel signInUserCodeModel, BindingResult bindingResult, HttpServletRequest request) {
        String userCode = generateUserCode(signInUserCodeModel);

        signInUserCodeModel.setUsername((String) request.getSession().getAttribute("username"));
        signInUserCodeModel.setUserCodeFull(userCode);

        if (signInUserCodeDelegate.signInUserCode(signInUserCodeModel)) {
            request.getSession().setAttribute("userCode", userCode);

            return "redirect:signInPassCode";
        } else {
            return "redirect:signInUserCode";
        }
    }

    private String generateUserCode(SignInUserCodeModel signInUserCodeModel) {
        return StringUtility.concat(signInUserCodeModel.getUserCode1(), signInUserCodeModel.getUserCode2(), signInUserCodeModel.getUserCode3(), signInUserCodeModel.getUserCode4(),
                                    signInUserCodeModel.getUserCode5());
    }

}
