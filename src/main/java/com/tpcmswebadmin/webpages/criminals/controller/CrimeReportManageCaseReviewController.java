package com.tpcmswebadmin.webpages.criminals.controller;

import com.tpcmswebadmin.infrastructure.domain.constant.TpCmsConstants;
import com.tpcmswebadmin.webpages.criminals.delegate.CrimeReportDelegate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

import static com.tpcmswebadmin.infrastructure.domain.enums.Roles.ADMIN;

@Controller
@RequiredArgsConstructor
public class CrimeReportManageCaseReviewController {

    private final CrimeReportDelegate crimeReportDelegate;

    @GetMapping("/crimeReport")
    public String getCriminalsDatabase(@RequestParam("reportId") String reportId, Model model, HttpServletRequest httpServletRequest) {
        crimeReportDelegate.getCrimeReportByReportId(reportId, httpServletRequest);

        model.addAttribute("officerName", httpServletRequest.getSession().getAttribute(TpCmsConstants.OFFICER_NAME));
        model.addAttribute("officerProfilePicture", httpServletRequest.getSession().getAttribute(TpCmsConstants.OFFICER_PROFILE_PICTURE));

        String adminRole = (String) httpServletRequest.getSession().getAttribute(TpCmsConstants.ACCESS_ROLE);
        model.addAttribute("accessRole", adminRole);

        if(adminRole.equals(ADMIN.name()))
            model.addAttribute("disabled", TpCmsConstants.LIST_DISABLE);

        model.addAttribute("statuses", crimeReportDelegate.getManageCaseStatuses(httpServletRequest));

        return "criminal_crime_reports_view";
    }
}
