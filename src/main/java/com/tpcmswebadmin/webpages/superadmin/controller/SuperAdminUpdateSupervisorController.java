package com.tpcmswebadmin.webpages.superadmin.controller;

import com.tpcmswebadmin.infrastructure.domain.constant.Pages;
import com.tpcmswebadmin.infrastructure.domain.constant.TpCmsConstants;
import com.tpcmswebadmin.infrastructure.domain.dto.ResponseMVCDto;
import com.tpcmswebadmin.webpages.policeofficer.model.PoliceOfficerUpdateModel;
import com.tpcmswebadmin.webpages.reference.delegate.ReferenceDelegate;
import com.tpcmswebadmin.webpages.superadmin.delegate.SupervisorNewDelegate;
import com.tpcmswebadmin.webpages.superadmin.delegate.SupervisorUpdateDelegate;
import com.tpcmswebadmin.webpages.superadmin.model.SupervisorNewModel;
import com.tpcmswebadmin.webpages.superadmin.model.SupervisorUpdateModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class SuperAdminUpdateSupervisorController {

    private final ReferenceDelegate referenceDelegate;

    private final SupervisorUpdateDelegate supervisorUpdateDelegate;

    @GetMapping("/updateSupervisor")
    public String getUpdateSupervisorPage(@RequestParam("supervisorId") String supervisorId, Model model, HttpServletRequest httpServletRequest) {
        SupervisorUpdateModel supervisorUpdateModel = supervisorUpdateDelegate.getSupervisorById(supervisorId, httpServletRequest);
        callAttributes(model, httpServletRequest, supervisorUpdateModel);

        return "system_administrator_update_supervisor";
    }

    @PostMapping("updateSupervisor")
    public String updateSupervisor(@RequestParam("supervisorId") String supervisorId,
                                   @Valid @ModelAttribute("updateSupervisor") SupervisorUpdateModel supervisorUpdateModel,
                                      BindingResult bindingResult, Model model, HttpServletRequest httpServletRequest) {

        if (bindingResult.hasErrors()) {
            log.warn("Errors {}", bindingResult.getAllErrors());

            return "system_administrator_update_supervisor";
        }

        supervisorUpdateModel.setId(supervisorId);
        ResponseMVCDto response = supervisorUpdateDelegate.updateSupervisor(supervisorUpdateModel, httpServletRequest);

        if(response.isResult()) {
            return "redirect:/systemAdministrator";
        } else {
            callAttributes(model, httpServletRequest, supervisorUpdateModel);
            model.addAttribute("httpError", response.getMessage());
            return "system_administrator_update_supervisor";
        }
    }

    private void callAttributes(Model model, HttpServletRequest httpServletRequest, SupervisorUpdateModel supervisorUpdateModel) {
        model.addAttribute("updateSupervisor", supervisorUpdateModel);
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
