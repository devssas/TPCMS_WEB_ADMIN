package com.tpcmswebadmin.webpages.notification.controller;

import com.tpcmswebadmin.infrastructure.domain.constant.Pages;
import com.tpcmswebadmin.infrastructure.domain.constant.TpCmsConstants;
import com.tpcmswebadmin.infrastructure.domain.dto.ResponseMVCDto;
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
        callAttributes(model, httpServletRequest, new NotificationCreateModel());

        return "notification_new";
    }

/*    @PostMapping("/newNotification")
    public String createNotification(@Valid @ModelAttribute("newNotificationModel") NotificationCreateModel notificationCreateModel,
                                     BindingResult bindingResult, Model model, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            log.warn("Error on creating notification {}", bindingResult.getAllErrors());
            return "notification_new";
        }

        String adminRole = (String) httpServletRequest.getSession().getAttribute(TpCmsConstants.ACCESS_ROLE);
        if(notificationCreateModel.getNotificationType() == null) {
            notificationCreateModel.setNotificationType("Notification");
        }
        ResponseMVCDto response = notificationNewDelegate.createNotification(notificationCreateModel, httpServletRequest);

        if(response.isResult()) {
            if(PROSECUTION.name().equals(adminRole)) {
                return "redirect:/dashboardProsecutor";
            } else if (ADMIN.name().equals(adminRole)){
                return "redirect:/dashboard";
            } else {
                return "redirect:/dashboardSuperAdmin";
            }
        } else {
            callAttributes(model, httpServletRequest, notificationCreateModel);
            model.addAttribute("httpError", response.getMessage());
            return "notification_new";
        }
    }*/

    private void callAttributes(Model model, HttpServletRequest httpServletRequest, NotificationCreateModel notificationCreateModel) {
        model.addAttribute("newNotificationCreateModel", notificationCreateModel);
        model.addAttribute("officerName", httpServletRequest.getSession().getAttribute(TpCmsConstants.OFFICER_NAME));
        model.addAttribute("officerProfilePicture", httpServletRequest.getSession().getAttribute(TpCmsConstants.OFFICER_PROFILE_PICTURE));
        model.addAttribute("notificationTypes", "Notification");
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
    }

}
