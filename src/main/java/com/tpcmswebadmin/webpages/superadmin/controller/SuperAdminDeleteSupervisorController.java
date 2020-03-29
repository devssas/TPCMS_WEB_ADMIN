package com.tpcmswebadmin.webpages.superadmin.controller;

import com.tpcmswebadmin.infrastructure.domain.LoginUserDo;
import com.tpcmswebadmin.infrastructure.domain.constant.Pages;
import com.tpcmswebadmin.infrastructure.domain.constant.TpCmsConstants;
import com.tpcmswebadmin.service.superadmin.service.SupervisorDeleteClientService;
import com.tpcmswebadmin.webpages.superadmin.delegate.SupervisorViewDelegate;
import com.tpcmswebadmin.webpages.superadmin.model.ScreenInfoModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class SuperAdminDeleteSupervisorController {

    private final SupervisorDeleteClientService supervisorDeleteClientService;

    @GetMapping("/supervisor")
    public String getSystemAdministrator(@RequestParam("supervisorId") String supervisorId, Model model, HttpServletRequest httpServletRequest) {
        LoginUserDo loginUserDo = LoginUserDo.makeLoginUser(httpServletRequest);
        supervisorDeleteClientService.delete(supervisorId, loginUserDo);

        return "redirect:/systemAdministrator";
    }
}
