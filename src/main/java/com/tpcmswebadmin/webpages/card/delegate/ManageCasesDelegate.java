package com.tpcmswebadmin.webpages.card.delegate;

import com.tpcmswebadmin.infrastructure.domain.LoginUserDo;
import com.tpcmswebadmin.service.criminals.domain.card.ManageCasesCard;
import com.tpcmswebadmin.service.criminals.service.CriminalProfileClientService;
import com.tpcmswebadmin.webpages.card.domain.ManageCasesCardModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@RequiredArgsConstructor
public class ManageCasesDelegate {

    private final CriminalProfileClientService criminalProfileClientService;

    public ManageCasesCardModel getManageCasesCardByCriminalsCode(String criminalsCode, HttpServletRequest httpServletRequest) {
        LoginUserDo loginUserDo = LoginUserDo.makeLoginUser(httpServletRequest);
        ManageCasesCard manageCasesCard = criminalProfileClientService.getCriminalCaseCardByCriminalsCode(criminalsCode, loginUserDo);

        return ManageCasesCardModel.builder()
                .image(manageCasesCard.getImages())
                .crimeType(manageCasesCard.getCrimeType())
                .criminalName(manageCasesCard.getCriminalName())
                .caseId(manageCasesCard.getCaseId())
                .date(manageCasesCard.getDate())
                .status(manageCasesCard.getStatus())
                .requestUnit(manageCasesCard.getRequestUnit())
                .caseBrief(manageCasesCard.getCaseBrief())
                .build();
    }
}
