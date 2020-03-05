package com.tpcmswebadmin.webpages.card.delegate;

import com.tpcmswebadmin.service.criminals.domain.card.CrimeReportCard;
import com.tpcmswebadmin.service.criminals.service.CrimeReportsClientService;
import com.tpcmswebadmin.webpages.card.domain.CrimeReportCardModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@RequiredArgsConstructor
public class CrimeReportCardDelegate {

    private final CrimeReportsClientService crimeReportsClientService;

    public CrimeReportCardModel getCrimeReportCardByCrimeReportId(String crimeReportId, HttpServletRequest httpServletRequest) {
        CrimeReportCard crimeReportCard = crimeReportsClientService.getCrimeReportCardByCrimeReportId(crimeReportId, httpServletRequest);

        return CrimeReportCardModel.builder()
                .crimeTitle(crimeReportCard.getCrimeTitle())
                .crimeScene(crimeReportCard.getCrimeScene())
                .caseBrief(crimeReportCard.getCaseBrief())
                .reportedDate(crimeReportCard.getReportedDate())
                .reportId(crimeReportCard.getReportId())
                .images(crimeReportCard.getImages())
                .status(crimeReportCard.getStatus())
                .suspects(crimeReportCard.getSuspects())
                .build();
    }
}
