package com.tpcmswebadmin.webpages.criminals.controller;

import com.tpcmswebadmin.infrastructure.domain.constant.Pages;
import com.tpcmswebadmin.infrastructure.domain.constant.TpCmsConstants;
import com.tpcmswebadmin.infrastructure.domain.dto.ResponseMVCDto;
import com.tpcmswebadmin.webpages.criminals.delegate.FileCriminalCaseNewDelegate;
import com.tpcmswebadmin.webpages.criminals.model.FileCriminalCaseCreateModel;
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

import static com.tpcmswebadmin.infrastructure.domain.enums.Roles.ADMIN;

@Slf4j
@Controller
@RequiredArgsConstructor
public class FileCriminalCaseNewController {

    private final FileCriminalCaseNewDelegate fileCriminalCaseNewDelegate;

    private final ReferenceDelegate referenceDelegate;

    @GetMapping("/fileCriminalCase")
    public String getFileCrimeCasePage(Model model, HttpServletRequest httpServletRequest) {
        callAttributes(model, httpServletRequest, new FileCriminalCaseCreateModel());

        return "criminal_file_criminal_case";
    }

    @PostMapping("/fileCriminalCase")
    public String fileCrimeCasePage(@Valid @ModelAttribute("newFileCriminalCase") FileCriminalCaseCreateModel fileCriminalCaseCreateModel,
                                    BindingResult bindingResult, Model model, HttpServletRequest httpServletRequest) {

        if (bindingResult.hasErrors()) {
            log.warn("Errors {}", bindingResult.getAllErrors());

            return "criminal_file_criminal_case";
        }

        ResponseMVCDto response = fileCriminalCaseNewDelegate.createCriminalCase(fileCriminalCaseCreateModel, httpServletRequest);

        if(response.isResult()) {
            return "redirect:/criminalsMenu";
        } else {
            callAttributes(model, httpServletRequest, fileCriminalCaseCreateModel);
            model.addAttribute("httpError", response.getMessage());

            return "criminal_file_criminal_case";
        }
    }

    private void callAttributes(Model model, HttpServletRequest httpServletRequest, FileCriminalCaseCreateModel policeOfficerCreateModel) {
        model.addAttribute("newFileCriminalCase", policeOfficerCreateModel);
        model.addAttribute("officerName", httpServletRequest.getSession().getAttribute(TpCmsConstants.OFFICER_NAME));
        model.addAttribute("officerProfilePicture", httpServletRequest.getSession().getAttribute(TpCmsConstants.OFFICER_PROFILE_PICTURE));

        String adminRole = (String) httpServletRequest.getSession().getAttribute(TpCmsConstants.ACCESS_ROLE);
        model.addAttribute("accessRole", adminRole);
        model.addAttribute("statuses", referenceDelegate.getClientStatus());
        model.addAttribute("crimeTypes", referenceDelegate.getCrimeTypesTitle());
        model.addAttribute("crimeNames", referenceDelegate.getCrimeTypesName());

        if(adminRole.equals(ADMIN.name())) {
            model.addAttribute("disabled", TpCmsConstants.LIST_DISABLE);
            model.addAttribute("dashboardPage", Pages.DASHBOARD_ADMIN_JSON);
        } else {
            model.addAttribute("dashboardPage", Pages.DASHBOARD_SUPERADMIN_JSON);
            model.addAttribute("prosecutorPage", Pages.MENU_BAR_SUPERADMIN_PROSECUTION_HOME);
        }
    }
}
