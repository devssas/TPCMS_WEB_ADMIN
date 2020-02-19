package com.tpcmswebadmin.webpages.dashboard.delegate;

import com.ssas.tpcms.engine.vo.response.AdminDashBoardResponseVO;
import com.ssas.tpcms.engine.vo.response.PushNotificationsResponseVO;
import com.ssas.tpcms.engine.vo.response.TPEngineResponse;
import com.tpcmswebadmin.service.dashboard.DashboardService;
import com.tpcmswebadmin.webpages.dashboard.domain.model.DashboardModel;
import com.tpcmswebadmin.webpages.dashboard.domain.model.DashboardNotificationModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Component
@RequiredArgsConstructor
public class DashboardDelegate {

    private final DashboardService dashboardService;

    public DashboardModel getDashboard(HttpServletRequest httpServletRequest) {
        TPEngineResponse response = dashboardService.getAdminDashboard(httpServletRequest);
        AdminDashBoardResponseVO clientResponse = response.getAdminDashBoardResponseVO();

        log.info("Dashboard request to be returned for user {}", response.getOfficerCode());

        return DashboardModel.builder()
                .transactionId(response.getTransactionId())
                .officerCode(response.getOfficerCode())
                .reportingUnit(response.getOfficerCode())
                .missionPermitCount(clientResponse.getCountMissionPermitsVO().length == 0 ? "0" : clientResponse.getCountMissionPermitsVO()[0].getTotalMissionPermitCount())
                .caseCount(clientResponse.getCountOfficerProfileVO().length == 0 ? "0" :clientResponse.getCountOfficerProfileVO()[0].getTotalOfficersCount())
                .notificationCount(clientResponse.getCountNotificationsVO().length == 0 ? "0" :clientResponse.getCountNotificationsVO()[0].getTotalNotificationsCount())
                .sosCount(clientResponse.getCountSOSVO().length == 0 ? "0" : clientResponse.getCountSOSVO()[0].getTotalSOSRequestCount())
                .build();
    }

    public DashboardNotificationModel getDashboardNotifications(HttpServletRequest httpServletRequest) {
        TPEngineResponse response = dashboardService.getAdminDashboardNotifications(httpServletRequest);
        PushNotificationsResponseVO[] clientResponse = response.getPushNotificationsList();

        log.info("Dashboard notifications request to be returned for user {}", response.getOfficerCode());

        return DashboardNotificationModel.builder()
                .notificationCount(clientResponse[0].getTotalNotificationCount())
                .sosCount(clientResponse[0].getTotalSOSCount())
                .build();
    }

}
