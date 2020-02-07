package com.tpcmswebadmin.webpages.dashboard;

import com.tpcmswebadmin.service.dashboard.domain.DashboardModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String getSignInUsernamePage(Model model) {
        model.addAttribute("dashboard", new DashboardModel());

        return "dashboard";
    }
}
