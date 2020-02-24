package com.tpcmswebadmin.webpages.card.delegate;

import com.tpcmswebadmin.service.criminals.domain.CrimeReportCardDto;
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
        CrimeReportCardDto crimeReportCardDto = crimeReportsClientService.getCrimeReportCardByCrimeReportId(crimeReportId, httpServletRequest);

        return CrimeReportCardModel.builder()
                .crimeTitle(crimeReportCardDto.getCrimeTitle())
                .crimeScene(crimeReportCardDto.getCrimeScene())
                .caseBrief(crimeReportCardDto.getCaseBrief())
                .reportedDate(crimeReportCardDto.getReportedDate())
                .reportId(crimeReportCardDto.getReportId())
                .images(crimeReportCardDto.getImages())
                .status(crimeReportCardDto.getStatus())
                .suspects(crimeReportCardDto.getSuspects())
                .build();
    }
}
