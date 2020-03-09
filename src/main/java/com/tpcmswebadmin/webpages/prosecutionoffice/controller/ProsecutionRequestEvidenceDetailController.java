package com.tpcmswebadmin.webpages.prosecutionoffice.controller;

import com.tpcmswebadmin.infrastructure.domain.constant.TpCmsConstants;
import com.tpcmswebadmin.webpages.prosecutionoffice.delegate.ProsecutionRequestEvidenceDetailDelegate;
import com.tpcmswebadmin.webpages.prosecutionoffice.model.ManageCrimeFileModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class ProsecutionRequestEvidenceDetailController {

    private final ProsecutionRequestEvidenceDetailDelegate prosecutionRequestEvidenceDetailDelegate;

    @GetMapping("/prosecutionManageCrimeFile")
    public String getRequestEvidenceByCaseId(@RequestParam("caseId") String caseId, Model model, HttpServletRequest httpServletRequest) {
        ManageCrimeFileModel manageCrimeFileModel = prosecutionRequestEvidenceDetailDelegate.getRequestEvidenceByCaseId(caseId, httpServletRequest);

        model.addAttribute("prosecutorName", httpServletRequest.getSession().getAttribute(TpCmsConstants.PROSECUTOR_NAME));
        model.addAttribute("prosecutorProfilePicture", httpServletRequest.getSession().getAttribute(TpCmsConstants.PROSECUTOR_PROFILE_PICTURE));
        model.addAttribute("accessRole", httpServletRequest.getSession().getAttribute(TpCmsConstants.ACCESS_ROLE));
        model.addAttribute("manageCrimeFileModel", manageCrimeFileModel);

        return "prosecution_office_manage_crime_file";
    }

}
