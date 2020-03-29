package com.tpcmswebadmin.webpages.sos.controller;

import com.tpcmswebadmin.infrastructure.domain.constant.Pages;
import com.tpcmswebadmin.infrastructure.domain.constant.TpCmsConstants;
import com.tpcmswebadmin.infrastructure.domain.dto.ResponseMVCDto;
import com.tpcmswebadmin.webpages.sos.delegate.SosDetailsDelegate;
import com.tpcmswebadmin.webpages.sos.model.SosCallModel;
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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.tpcmswebadmin.infrastructure.domain.enums.Roles.ADMIN;


@Slf4j
@Controller
@RequiredArgsConstructor
public class SosDetailsController {

    private final SosDetailsDelegate sosDetailsDelegate;

    @GetMapping("/sosDetails")
    public String getSosDetailPage(@RequestParam("sosId") String sosId, Model model, HttpServletRequest httpServletRequest) {
        SosCallModel sosCallModel = sosDetailsDelegate.getSosDetailsById(sosId, httpServletRequest);
        callAttributes(model, httpServletRequest, sosCallModel);

        return "sos_details";
    }

    @PostMapping("/sosDetails")
    public String updateSos(@Valid @ModelAttribute("updateSos") SosCallModel sosCallUpdateModel,
                            BindingResult bindingResult, Model model, HttpServletRequest httpServletRequest) {

        if (bindingResult.hasErrors()) {
            log.warn("Error on updating sos details {}", bindingResult.getAllErrors());
            return "sos_details";
        }

        ResponseMVCDto response = sosDetailsDelegate.updateSos(sosCallUpdateModel, httpServletRequest);

        if(response.isResult()) {
            if(sosCallUpdateModel.getStatus().equals("CLOSED"))
                return "redirect:/sosDetails?sosId=" + sosCallUpdateModel.getId();
            else
                return "redirect:/sos";
        } else {
            callAttributes(model, httpServletRequest, sosCallUpdateModel);
            model.addAttribute("httpError", response.getMessage());
            return "sos_details";
        }
    }

    private void callAttributes(Model model, HttpServletRequest httpServletRequest, SosCallModel sosModel) {
        model.addAttribute("updateSos", sosModel);
        model.addAttribute("officerName", httpServletRequest.getSession().getAttribute(TpCmsConstants.OFFICER_NAME));
        model.addAttribute("officerProfilePicture", httpServletRequest.getSession().getAttribute(TpCmsConstants.OFFICER_PROFILE_PICTURE));
        model.addAttribute("statuses", statuses(sosModel.getStatus()));
        model.addAttribute("sosId",sosModel.getId());

        String adminRole = (String) httpServletRequest.getSession().getAttribute(TpCmsConstants.ACCESS_ROLE);
        model.addAttribute("accessRole", adminRole);

        if(adminRole.equals(ADMIN.name())) {
            model.addAttribute("disabled", TpCmsConstants.LIST_DISABLE);
            model.addAttribute("dashboardPage", Pages.DASHBOARD_ADMIN_JSON);
            model.addAttribute("buttonType", "disabled");
        } else {
            model.addAttribute("dashboardPage", Pages.DASHBOARD_SUPERADMIN_JSON);
            model.addAttribute("prosecutorPage", Pages.MENU_BAR_SUPERADMIN_PROSECUTION_HOME);

            if(sosModel.getStatus().equals("ACT"))
                model.addAttribute("buttonType", null);
            else
                model.addAttribute("buttonType", "disabled");
        }
    }

    private List<String> statuses(String status) {
        if(status.equals("ACT")) {
            return Arrays.asList("ACT", "CLOSED");
        }
        else {
            return Collections.singletonList("CLOSED");
        }
    }
}
