package com.tpcmswebadmin.webpages.dashboard.delegate;

import com.ssas.tpcms.engine.vo.response.TPEngineResponse;
import com.tpcmswebadmin.service.dashboard.DashboardProsecutorService;
import com.tpcmswebadmin.webpages.dashboard.domain.model.DashboardProsecutorModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Component
@RequiredArgsConstructor
public class DashboardProsecutorDelegate {

    private final DashboardProsecutorService dashboardProsecutorService;

    public DashboardProsecutorModel getDashboardProsecutor(HttpServletRequest httpServletRequest) {
        TPEngineResponse response = dashboardProsecutorService.getProsecutorDashboard(httpServletRequest);
        log.info("Dashboard Prosecutor request to be returned for user {}", response.getOfficerCode());

        return DashboardProsecutorModel.builder()
                .transactionId(response.getTransactionId())
                .officerCode(response.getOfficerCode())
                .reportingUnit(response.getOfficerCode())
                .totalEvidenceCount(response.getProsecutionDashBoardResponseVO().getCountRequestForEvidenceList()[0].getTotalEvidenseCount())
                .notificationCount(response.getProsecutionDashBoardResponseVO().getCountNotificationsList()[0].getTotalNotificationsCount())
                .build();
    }

}
