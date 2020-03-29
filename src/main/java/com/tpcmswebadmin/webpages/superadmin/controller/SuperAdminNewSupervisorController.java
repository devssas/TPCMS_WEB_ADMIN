package com.tpcmswebadmin.webpages.superadmin.controller;

import com.tpcmswebadmin.infrastructure.domain.constant.Pages;
import com.tpcmswebadmin.infrastructure.domain.constant.TpCmsConstants;
import com.tpcmswebadmin.infrastructure.domain.dto.ResponseMVCDto;
import com.tpcmswebadmin.webpages.superadmin.delegate.SupervisorNewDelegate;
import com.tpcmswebadmin.webpages.superadmin.model.SupervisorNewModel;
import com.tpcmswebadmin.webpages.reference.delegate.ReferenceDelegate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class SuperAdminNewSupervisorController {

    private final ReferenceDelegate referenceDelegate;

    private final SupervisorNewDelegate supervisorNewDelegate;

    @GetMapping("/newSupervisor")
    public String getCreateSupervisorPage(Model model, HttpServletRequest httpServletRequest) {
        callAttributes(model, httpServletRequest, new SupervisorNewModel());

        return "system_administrator_add_new_supervisor";
    }

    @PostMapping("/newSupervisor")
    public String createSupervisor(@Valid @ModelAttribute("newSupervisor") SupervisorNewModel supervisorNewModel,
                                   BindingResult bindingResult, Model model, HttpServletRequest httpServletRequest) {

        if (bindingResult.hasErrors()) {
            log.warn("Error on creating supervisor  {}", bindingResult.getAllErrors());
            return "system_administrator_add_new_supervisor";
        }

        callAttributes(model, httpServletRequest, supervisorNewModel);

        supervisorNewModel.setCommandCenterId(referenceDelegate.getCommandCenterId(supervisorNewModel.getCommandCenter()).getCommandCenterId());
        ResponseMVCDto response = supervisorNewDelegate.createSupervisor(supervisorNewModel, httpServletRequest);

        if(response.isResult()) {
            return "redirect:/systemAdministrator";
        } else {
            callAttributes(model, httpServletRequest, supervisorNewModel);
            model.addAttribute("httpError", response.getMessage());

            return "system_administrator_add_new_supervisor";
        }
    }

    private void callAttributes(Model model, HttpServletRequest httpServletRequest, SupervisorNewModel supervisorNewModel) {
        model.addAttribute("newSupervisor", supervisorNewModel);
        model.addAttribute("officerName", httpServletRequest.getSession().getAttribute(TpCmsConstants.OFFICER_NAME));
        model.addAttribute("officerProfilePicture", httpServletRequest.getSession().getAttribute(TpCmsConstants.OFFICER_PROFILE_PICTURE));

        String adminRole = (String) httpServletRequest.getSession().getAttribute(TpCmsConstants.ACCESS_ROLE);
        model.addAttribute("accessRole", adminRole);

        model.addAttribute("reportingUnits", referenceDelegate.getOfficerUnits());
        model.addAttribute("officerGrades", referenceDelegate.getOfficerGrades());
        model.addAttribute("officerRanks", referenceDelegate.getOfficersRanks());
        model.addAttribute("accessRoles", referenceDelegate.getAccessRoles());
        model.addAttribute("dashboardPage", Pages.DASHBOARD_SUPERADMIN_JSON);
        model.addAttribute("commandCenters", referenceDelegate.getCommandCenter());
        model.addAttribute("status", referenceDelegate.getClientStatus());
    }
}
