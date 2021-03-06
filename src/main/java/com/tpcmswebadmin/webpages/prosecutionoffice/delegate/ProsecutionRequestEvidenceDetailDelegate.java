package com.tpcmswebadmin.webpages.prosecutionoffice.delegate;

import com.tpcmswebadmin.infrastructure.domain.LoginUserDo;
import com.tpcmswebadmin.service.prosecutionoffice.domain.ProsecutionRequestEvidenceDetailDto;
import com.tpcmswebadmin.service.prosecutionoffice.service.ProsecutionCasesRequestEvidenceClientService;
import com.tpcmswebadmin.webpages.prosecutionoffice.model.ManageCrimeFileModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@RequiredArgsConstructor
public class ProsecutionRequestEvidenceDetailDelegate {

    private final ProsecutionCasesRequestEvidenceClientService prosecutionCasesRequestEvidenceClientService;

    public ManageCrimeFileModel getRequestEvidenceByCaseId(String criminalsCode, HttpServletRequest httpServletRequest) {
        LoginUserDo loginUserDo = LoginUserDo.makeLoginUser(httpServletRequest);
        ProsecutionRequestEvidenceDetailDto response = prosecutionCasesRequestEvidenceClientService.getProsecutionRequestEvidenceDetailDto(
                criminalsCode, loginUserDo);

        return ManageCrimeFileModel.builder()
                .caseId(response.getCaseId())
                .caseDate(response.getCaseDate())
                .officerName(response.getOfficerId())
                .crimeLocation(response.getCrimeLocation())
                .status(response.getStatus())
                .suspects(response.getSuspects())
                .images(response.getImages())
                .crimeType(response.getCrimeType())
                .crimeTitle(response.getCrimeTitle())
                .crimeClassification(response.getCrimeClassification())
                .witnessStatementFirstName(response.getWitnessFirstName())
                .witnessStatementLastName(response.getWitnessLastName())
                .witnessStatement(response.getWitnessStatement())
                .build();
    }
}
