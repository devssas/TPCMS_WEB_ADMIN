package com.tpcmswebadmin.webpages.prosecutionoffice.controller;

import com.tpcmswebadmin.infrastructure.domain.constant.Pages;
import com.tpcmswebadmin.infrastructure.domain.constant.TpCmsConstants;
import com.tpcmswebadmin.infrastructure.domain.dto.ResponseMVCDto;
import com.tpcmswebadmin.service.reference.domain.enums.ClientStatus;
import com.tpcmswebadmin.webpages.criminals.model.CrimeReportUpdateModel;
import com.tpcmswebadmin.webpages.prosecutionoffice.delegate.ProsecutionManageCasesDetailsDelegate;
import com.tpcmswebadmin.webpages.prosecutionoffice.model.ManageCrimeFileModel;
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
public class ProsecutionManageCaseDetailsController {

    private final ProsecutionManageCasesDetailsDelegate manageCasesDetailsDelegate;

    @GetMapping("/prosecutionManageCasesDetail")
    public String getProsecutionSubmitForReview(@RequestParam("criminalsCode") String criminalsCode, Model model, HttpServletRequest httpServletRequest) {
        ManageCrimeFileModel manageCrimeFileModel = manageCasesDetailsDelegate.getCaseDetail(criminalsCode, httpServletRequest);
        callAttributes(model, httpServletRequest, manageCrimeFileModel);

        return "prosecution_manage_case_detail";
    }

    @PostMapping("/prosecutionManageCasesDetail")
    public String updateSubmitForReviewReport(@RequestParam("criminalsCode") String criminalsCode,
                                              @RequestParam(value = "approveButton", required = false) String approveButton,
                                              @RequestParam(value = "rejectButton", required = false) String rejectButton,
                                              @RequestParam(value = "requestForEvidence", required = false) String requestForEvidence,
                                              @Valid @ModelAttribute("updateReport") ManageCrimeFileModel manageCrimeFileModel,
                                              BindingResult bindingResult, Model model, HttpServletRequest httpServletRequest) {

        if (bindingResult.hasErrors()) {
            log.warn("Errors {}", bindingResult.getAllErrors());

            return "prosecution_manage_case_detail";
        }

        if (approveButton == null && rejectButton == null)
            manageCrimeFileModel.setReviewStatus(ClientStatus.REQUEST_FOR_EVIDENCE.getClientName());
        else if(requestForEvidence == null && approveButton == null)
            manageCrimeFileModel.setReviewStatus(ClientStatus.REJECTED.getClientName());
        else if(requestForEvidence == null && rejectButton == null)
            manageCrimeFileModel.setReviewStatus(ClientStatus.APPROVED.getClientName());

        manageCrimeFileModel.setCriminalsCode(criminalsCode);

        ResponseMVCDto response = manageCasesDetailsDelegate.updateCrimeReport(manageCrimeFileModel, httpServletRequest);

        if (response.isResult()) {
            return "redirect:/prosecutionManageCases";
        } else {
            manageCrimeFileModel = manageCasesDetailsDelegate.getCaseDetail(criminalsCode, httpServletRequest);
            callAttributes(model, httpServletRequest, manageCrimeFileModel);
            model.addAttribute("httpError", response.getMessage());

            return "prosecution_manage_case_detail";
        }
    }

    private void callAttributes(Model model, HttpServletRequest httpServletRequest, ManageCrimeFileModel manageCrimeFileModel) {
        model.addAttribute("prosecutorName", httpServletRequest.getSession().getAttribute(TpCmsConstants.PROSECUTOR_NAME));
        model.addAttribute("prosecutorProfilePicture", httpServletRequest.getSession().getAttribute(TpCmsConstants.PROSECUTOR_PROFILE_PICTURE));
        model.addAttribute("accessRole", httpServletRequest.getSession().getAttribute(TpCmsConstants.ACCESS_ROLE));
        model.addAttribute("updateReport", manageCrimeFileModel);
    }


}
