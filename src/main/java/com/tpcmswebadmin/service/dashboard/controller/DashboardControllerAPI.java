package com.tpcmswebadmin.service.dashboard.controller;

import com.tpcmswebadmin.service.dashboard.domain.DashboardDto;
import com.tpcmswebadmin.service.dashboard.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("api")
public class DashboardControllerAPI {

    private final DashboardService dashboardService;

    @GetMapping("dashboard")
    public DashboardDto getDashboard(HttpServletRequest httpServletRequest) {
        return dashboardService.getAdminDashboardAPI(httpServletRequest);
    }
}
