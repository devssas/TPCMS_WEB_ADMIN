package com.tpcmswebadmin.webpages.criminals.controller;

import com.tpcmswebadmin.infrastructure.domain.constant.Pages;
import com.tpcmswebadmin.infrastructure.domain.constant.TpCmsConstants;
import com.tpcmswebadmin.webpages.criminals.delegate.CriminalCaseNewDelegate;
import com.tpcmswebadmin.webpages.criminals.model.CriminalCaseCreateModel;
import com.tpcmswebadmin.webpages.missionpermits.delegate.MissionPermitNewDelegate;
import com.tpcmswebadmin.webpages.missionpermits.model.MissionPermitCardCreateModel;
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

import static com.tpcmswebadmin.infrastructure.domain.enums.Roles.ADMIN;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CriminalCaseNewController {

    private final CriminalCaseNewDelegate criminalCaseNewDelegate;

    @GetMapping("/fileCriminalCase")
    public String getFileCrimeCasePage(Model model, HttpServletRequest httpServletRequest) {
        model.addAttribute("officerName", httpServletRequest.getSession().getAttribute(TpCmsConstants.OFFICER_NAME));
        model.addAttribute("officerProfilePicture", httpServletRequest.getSession().getAttribute(TpCmsConstants.OFFICER_PROFILE_PICTURE));

        String adminRole = (String) httpServletRequest.getSession().getAttribute(TpCmsConstants.ACCESS_ROLE);
        model.addAttribute("accessRole", adminRole);

        if(adminRole.equals(ADMIN.name())) {
            model.addAttribute("disabled", TpCmsConstants.LIST_DISABLE);
            model.addAttribute("dashboardPage", Pages.DASHBOARD_ADMIN_JSON);
        } else {
            model.addAttribute("dashboardPage", Pages.DASHBOARD_SUPERADMIN_JSON);
            model.addAttribute("prosecutorPage", Pages.MENU_BAR_SUPERADMIN_PROSECUTION_HOME);
        }

        return "criminal_file_criminal_case_new";
    }

    @PostMapping("/fileCriminalCase")
    public String fileCrimeCasePage(@Valid @ModelAttribute("fileCriminalCase") CriminalCaseCreateModel criminalCaseCreateModel,
                                    BindingResult bindingResult, Model model, HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            log.warn("Errors {}", bindingResult.getAllErrors());

            return "criminal_file_criminal_case_new";
        }

        if(criminalCaseNewDelegate.createCriminalCase(criminalCaseCreateModel, request))
            return "redirect:/criminal_database_menu";

        return null;
    }
}
