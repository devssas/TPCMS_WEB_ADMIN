package com.tpcmswebadmin.webpages.criminals.controller;

import com.tpcmswebadmin.infrastructure.domain.constant.Pages;
import com.tpcmswebadmin.infrastructure.domain.constant.TpCmsConstants;
import com.tpcmswebadmin.infrastructure.domain.dto.ResponseMVCDto;
import com.tpcmswebadmin.webpages.criminals.delegate.ManageCrimeReportUpdateDelegate;
import com.tpcmswebadmin.webpages.criminals.model.CrimeReportUpdateModel;
import com.tpcmswebadmin.webpages.reference.delegate.ReferenceDelegate;
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

import static com.tpcmswebadmin.infrastructure.domain.enums.Roles.ADMIN;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ManageCrimeReportUpdateController {

    private final ManageCrimeReportUpdateDelegate manageCrimeReportUpdateDelegate;

    private final ReferenceDelegate referenceDelegate;

    @GetMapping("/updateCrimeReport")
    public String getUpdateCrimeReport(@RequestParam("reportId") String reportId,
                                       Model model, HttpServletRequest httpServletRequest) {
        CrimeReportUpdateModel crimeReportUpdateModel = manageCrimeReportUpdateDelegate.getCrimeReportByReportId(reportId, httpServletRequest);
        callAttributes(model, httpServletRequest, crimeReportUpdateModel);

        return "criminal_crime_report_update_crime_report";
    }

    @PostMapping("/updateCrimeReport")
    public String reviewCrimeReport(@RequestParam("reportId") String reportId,
                                    @RequestParam(value = "acceptButton", required = false) String acceptButton,
                                    @RequestParam(value ="rejectButton", required = false) String rejectButton,
                                    @Valid @ModelAttribute("updateCrimeReport") CrimeReportUpdateModel crimeReportUpdateModel,
                                    BindingResult bindingResult, Model model, HttpServletRequest httpServletRequest) {

        if (bindingResult.hasErrors()) {
            log.warn("Errors {}", bindingResult.getAllErrors());

            return "criminal_crime_report_update_crime_report";
        }

        if(acceptButton == null)
            crimeReportUpdateModel.setReviewStatus("REJECT");
        else
            crimeReportUpdateModel.setReviewStatus("APPROVED");

        crimeReportUpdateModel.setId(reportId);

        ResponseMVCDto response = manageCrimeReportUpdateDelegate.updateCrimeReport(crimeReportUpdateModel, httpServletRequest);

        if(response.isResult()) {
            return "redirect:/crimeReports";
        } else {
            crimeReportUpdateModel = manageCrimeReportUpdateDelegate.getCrimeReportByReportId(reportId, httpServletRequest);
            callAttributes(model, httpServletRequest, crimeReportUpdateModel);
            model.addAttribute("httpError", response.getMessage());

            return "criminal_crime_report_update_crime_report";
        }
    }

    private void callAttributes(Model model, HttpServletRequest httpServletRequest, CrimeReportUpdateModel crimeReportUpdateModel) {
        model.addAttribute("updateCrimeReport", crimeReportUpdateModel);
        model.addAttribute("officerName", httpServletRequest.getSession().getAttribute(TpCmsConstants.OFFICER_NAME));
        model.addAttribute("officerProfilePicture", httpServletRequest.getSession().getAttribute(TpCmsConstants.OFFICER_PROFILE_PICTURE));

        String adminRole = (String) httpServletRequest.getSession().getAttribute(TpCmsConstants.ACCESS_ROLE);
        model.addAttribute("accessRole", adminRole);
        model.addAttribute("statuses", referenceDelegate.getClientStatus());
        model.addAttribute("crimeTypes", referenceDelegate.getCrimeTypesTitle());
        model.addAttribute("crimeNames", referenceDelegate.getCrimeTypesName());

        if(crimeReportUpdateModel.getInitialStatus().equals("ACT"))
             model.addAttribute("buttonType", null);
        else
            model.addAttribute("buttonType", "disabled");


        if(adminRole.equals(ADMIN.name())) {
            model.addAttribute("disabled", TpCmsConstants.LIST_DISABLE);
            model.addAttribute("dashboardPage", Pages.DASHBOARD_ADMIN_JSON);
        } else {
            model.addAttribute("dashboardPage", Pages.DASHBOARD_SUPERADMIN_JSON);
            model.addAttribute("prosecutorPage", Pages.MENU_BAR_SUPERADMIN_PROSECUTION_HOME);
        }
    }
}
