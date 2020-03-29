package com.tpcmswebadmin.webpages.policeofficer.controller;

import com.tpcmswebadmin.infrastructure.domain.constant.Pages;
import com.tpcmswebadmin.infrastructure.domain.constant.TpCmsConstants;
import com.tpcmswebadmin.infrastructure.domain.dto.ResponseMVCDto;
import com.tpcmswebadmin.webpages.policeofficer.delegate.PoliceOfficerDeleteDelegate;
import com.tpcmswebadmin.webpages.policeofficer.delegate.PoliceOfficerNewDelegate;
import com.tpcmswebadmin.webpages.policeofficer.model.PoliceOfficerCreateModel;
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
public class PoliceOfficerDeleteController {

    private final PoliceOfficerDeleteDelegate policeOfficerDeleteDelegate;

    @GetMapping("/deleteOfficer")
    public String createNewOfficer(@RequestParam("officerId") String officerId, HttpServletRequest httpServletRequest) {
        policeOfficerDeleteDelegate.delete(officerId, httpServletRequest);

        return "redirect:/officerProfiles";
    }

}
