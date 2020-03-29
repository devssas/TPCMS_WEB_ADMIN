package com.tpcmswebadmin.webpages.criminals.delegate;

import com.ssas.tpcms.engine.vo.response.TPEngineResponse;
import com.tpcmswebadmin.infrastructure.domain.LoginUserDo;
import com.tpcmswebadmin.infrastructure.domain.dto.ResponseMVCDto;
import com.tpcmswebadmin.infrastructure.utils.StringUtility;
import com.tpcmswebadmin.service.criminals.service.CrimeReportUpdateClientService;
import com.tpcmswebadmin.service.criminals.service.CrimeReportsClientService;
import com.tpcmswebadmin.webpages.criminals.model.CrimeReportUpdateModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Component
@RequiredArgsConstructor
public class ManageCrimeReportUpdateDelegate {

    private final CrimeReportsClientService crimeReportsClientService;

    private final CrimeReportUpdateClientService crimeReportUpdateClientService;
    
    public CrimeReportUpdateModel getCrimeReportByReportId(String reportId, HttpServletRequest httpServletRequest) {
        LoginUserDo loginUserDo = LoginUserDo.makeLoginUser(httpServletRequest);
        TPEngineResponse response = crimeReportsClientService.getCrimeReportByCrimeReportId(reportId, loginUserDo);

        return CrimeReportUpdateModel.builder()
                .crimeLocation(response.getCrimeReportList()[0].getCrimeLocation())
                .crimeType(response.getCrimeReportList()[0].getTypeOfCrime())
                .suspectName1(response.getCrimeReportList()[0].getListOfSuspects())
                .suspectName2(null)
                .suspectName3(null)
                .crimeName(response.getCrimeReportList()[0].getCrimeName())
                .crimeScene(response.getCrimeReportList()[0].getCrimeScene())
                .nationalId(response.getCrimeReportList()[0].getNationalIdNumber())
                .passportNumber(response.getCrimeReportList()[0].getPassportNumber())
                .drivingLicenseNumber(response.getCrimeReportList()[0].getDrivingLicenseNumber())
                .mobileNumberCountryCode(response.getCrimeReportList()[0].getMobileNumberCountryCode())
                .mobileNumber(response.getCrimeReportList()[0].getMobileNumber())
                .suspectKnownAddress(response.getCrimeReportList()[0].getContactAddress())
                .images(null)
                .crimeBrief(response.getCrimeReportList()[0].getCaseBriefDesc())
                .idNumber(response.getCrimeReportList()[0].getReportingOfficerUnitNumber())
                .reportingOfficer(StringUtility.makeFullName(response.getCrimeReportList()[0].getReportingFirstName_Ar(), response.getCrimeReportList()[0].getReportingLastName_Ar()))
                .initialStatus(response.getCrimeReportList()[0].getCrimianlStatusCode())
                .build();
    }

    public ResponseMVCDto updateCrimeReport(CrimeReportUpdateModel crimeReportUpdateModel, HttpServletRequest httpServletRequest) {
        LoginUserDo loginUserDo = LoginUserDo.makeLoginUser(httpServletRequest);
        TPEngineResponse response = crimeReportUpdateClientService.update(crimeReportUpdateModel, loginUserDo);

        return ResponseMVCDto.prepareResponse(response);
    }
}
