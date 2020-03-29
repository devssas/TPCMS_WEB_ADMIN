package com.tpcmswebadmin.webpages.prosecutionoffice.delegate;

import com.ssas.tpcms.engine.vo.response.TPEngineResponse;
import com.tpcmswebadmin.infrastructure.client.response.ResponseAPIDto;
import com.tpcmswebadmin.infrastructure.domain.LoginUserDo;
import com.tpcmswebadmin.infrastructure.domain.dto.ResponseMVCDto;
import com.tpcmswebadmin.infrastructure.utils.ImageUtility;
import com.tpcmswebadmin.infrastructure.utils.StringUtility;
import com.tpcmswebadmin.service.prosecutionoffice.domain.ProsecutionCasesDto;
import com.tpcmswebadmin.service.prosecutionoffice.domain.ProsecutionManageCasesDetailDto;
import com.tpcmswebadmin.service.prosecutionoffice.domain.ProsecutionRequestEvidenceDetailDto;
import com.tpcmswebadmin.service.prosecutionoffice.service.ProsecutionManageCasesClientService;
import com.tpcmswebadmin.service.prosecutionoffice.service.ProsecutionManageCasesUpdateClientService;
import com.tpcmswebadmin.webpages.prosecutionoffice.model.ManageCrimeFileModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProsecutionManageCasesDetailsDelegate {

    private final ProsecutionManageCasesClientService prosecutionManageCasesClientService;

    private final ProsecutionManageCasesUpdateClientService prosecutionManageCasesUpdateClientService;

    public ManageCrimeFileModel getCaseDetail(String criminalsCode, HttpServletRequest httpServletRequest) {
        LoginUserDo loginUserDo = LoginUserDo.makeLoginUser(httpServletRequest);
        TPEngineResponse response = prosecutionManageCasesClientService.getCaseDetails(criminalsCode, loginUserDo);

        return ManageCrimeFileModel.builder()
                .caseId(response.getCriminalProfileList()[0].getCaseId())
                .caseDate(response.getCriminalProfileList()[0].getFlagedDate())
                .officerName(response.getCriminalProfileList()[0].getArrestedOfficerId())
                .crimeLocation(response.getCriminalProfileList()[0].getCrimeLocation())
                .status(response.getCriminalProfileList()[0].getCrimianlStatusCode())
                .suspects(Collections.singletonList(StringUtility.makeFullName(response.getCriminalProfileList()[0].getFirstName_Ar() + " " + response.getCriminalProfileList()[0].getLastName_Ar())))
                .images(response.getCriminalProfileList()[0].getCriminalWitnessResponseVOArr() == null ? null :
                        (Arrays.asList(ImageUtility.convertToBase64image(response.getCriminalProfileList()[0].getCriminalWitnessResponseVOArr()[0].getEvidencePhoto1()),
                                ImageUtility.convertToBase64image(response.getCriminalProfileList()[0].getCriminalWitnessResponseVOArr()[0].getEvidencePhoto2()),
                                ImageUtility.convertToBase64image(response.getCriminalProfileList()[0].getCriminalWitnessResponseVOArr()[0].getEvidencePhoto3()),
                                ImageUtility.convertToBase64image(response.getCriminalProfileList()[0].getCriminalWitnessResponseVOArr()[0].getEvidencePhoto4()),
                                ImageUtility.convertToBase64image(response.getCriminalProfileList()[0].getCriminalWitnessResponseVOArr()[0].getEvidencePhoto5()),
                                ImageUtility.convertToBase64image(response.getCriminalProfileList()[0].getCriminalWitnessResponseVOArr()[0].getEvidencePhoto6()),
                                ImageUtility.convertToBase64image(response.getCriminalProfileList()[0].getCriminalWitnessResponseVOArr()[0].getEvidencePhoto7()),
                                ImageUtility.convertToBase64image(response.getCriminalProfileList()[0].getCriminalWitnessResponseVOArr()[0].getEvidencePhoto8()),
                                ImageUtility.convertToBase64image(response.getCriminalProfileList()[0].getCriminalWitnessResponseVOArr()[0].getEvidencePhoto9()),
                                ImageUtility.convertToBase64image(response.getCriminalProfileList()[0].getCriminalWitnessResponseVOArr()[0].getEvidencePhoto10()))))
                .crimeType(response.getCriminalProfileList()[0].getTypeOfCrime())
                .crimeTitle(response.getCriminalProfileList()[0].getCrimeName())
                .crimeClassification(response.getCriminalProfileList()[0].getCrimeClassification())
                .witnessStatementFirstName(response.getCriminalProfileList()[0].getFirstName_Ar())
                .witnessStatementLastName(response.getCriminalProfileList()[0].getLastName_Ar())
                .witnessStatement(response.getCriminalProfileList()[0].getCriminalWitnessResponseVOArr() == null ? null : response.getCriminalProfileList()[0].getCriminalWitnessResponseVOArr()[0].getAdminOfficerStatement())
                .build();
    }

    public ResponseMVCDto updateCrimeReport(ManageCrimeFileModel manageCrimeFileModel, HttpServletRequest httpServletRequest) {
        LoginUserDo loginUserDo = LoginUserDo.makeLoginUser(httpServletRequest);
        TPEngineResponse response = prosecutionManageCasesUpdateClientService.update(manageCrimeFileModel, loginUserDo);

        return ResponseMVCDto.prepareResponse(response);
    }
}
