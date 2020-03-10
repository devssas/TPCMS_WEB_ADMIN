package com.tpcmswebadmin.webpages.notification.controller;

import com.tpcmswebadmin.infrastructure.domain.constant.Pages;
import com.tpcmswebadmin.infrastructure.domain.constant.TpCmsConstants;
import com.tpcmswebadmin.webpages.notification.delegate.NotificationDelegate;
import com.tpcmswebadmin.webpages.notification.delegate.NotificationNewDelegate;
import com.tpcmswebadmin.webpages.notification.model.NotificationCreateModel;
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

import static com.tpcmswebadmin.infrastructure.domain.enums.Roles.*;

@Slf4j
@Controller
@RequiredArgsConstructor
public class NotificationNewController {

    private final NotificationNewDelegate notificationNewDelegate;

    private final ReferenceDelegate referenceDelegate;

    @GetMapping("/newNotification")
    public String getNotifications(Model model, HttpServletRequest httpServletRequest) {
        model.addAttribute("officerName", httpServletRequest.getSession().getAttribute(TpCmsConstants.OFFICER_NAME));
        model.addAttribute("officerProfilePicture", httpServletRequest.getSession().getAttribute(TpCmsConstants.OFFICER_PROFILE_PICTURE));
        model.addAttribute("notificationTypes", notificationNewDelegate.getNotificationTypes());
        model.addAttribute("natureOfAnnouncement", referenceDelegate.getNatureOfAnnouncement());

        String adminRole = (String) httpServletRequest.getSession().getAttribute(TpCmsConstants.ACCESS_ROLE);
        model.addAttribute("accessRole", adminRole);


        if(adminRole.equals(ADMIN.name())) {
            model.addAttribute("disabled", TpCmsConstants.LIST_DISABLE);
            model.addAttribute("dashboardPage", Pages.DASHBOARD_ADMIN_JSON);
        } else {
            model.addAttribute("dashboardPage", Pages.DASHBOARD_SUPERADMIN_JSON);
            model.addAttribute("prosecutorPage", Pages.MENU_BAR_SUPERADMIN_PROSECUTION_HOME);
        }

        return "notification_new";
    }

    @PostMapping("/newNotification")
    public String createNotification(@Valid @ModelAttribute("newNotificationModel") NotificationCreateModel notificationCreateModel,
                                     BindingResult bindingResult, Model model, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            log.warn("Error on creating notification {}", bindingResult.getAllErrors());
            return "notification_new";
        }

        notificationNewDelegate.createNotification(notificationCreateModel, httpServletRequest);
        String adminRole = (String) httpServletRequest.getSession().getAttribute(TpCmsConstants.ACCESS_ROLE);

        if(PROSECUTION.name().equals(adminRole)) {
            return "redirect:/dashboard_prosecutor";
        } else if (ADMIN.name().equals(adminRole)){
            return "redirect:/dashboard_superadmin";
        } else {
            return "redirect:/dashboard_admin";
        }
    }

}
