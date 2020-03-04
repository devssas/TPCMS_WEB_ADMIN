package com.tpcmswebadmin.webpages.authentication.delegate;

import com.ssas.tpcms.engine.vo.response.TPEngineResponse;
import com.tpcmswebadmin.infrastructure.utils.ImageUtility;
import com.tpcmswebadmin.infrastructure.utils.StringUtility;
import com.tpcmswebadmin.service.authentication.service.AuthenticationService;
import com.tpcmswebadmin.service.authentication.domain.model.SignInPassCodeModel;
import com.tpcmswebadmin.webpages.authentication.domain.SignInPassCodeDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SignInPassCodeDelegate {

    private final AuthenticationService authenticationService;

    public SignInPassCodeDelegate(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    public SignInPassCodeDto signInPassCode(SignInPassCodeModel signInPassCodeModel) {
        TPEngineResponse response = authenticationService.signInPassCode(signInPassCodeModel);
        SignInPassCodeDto signInPassCodeDto = new SignInPassCodeDto();

        if (response.getResponseCodeVO().getResponseCode().startsWith("OPS")) {
            signInPassCodeDto.setHasResult(true);
            signInPassCodeDto.setOfficerCode(response.getOfficerCode());
            signInPassCodeDto.setReportUnit(response.getOfficersProfileResponseVO().getReportingUnit());
            signInPassCodeDto.setProfilePicture(ImageUtility.convertToBase64image(response.getOfficersProfileResponseVO().getProfilePhoto1()));
            signInPassCodeDto.setOfficerName(StringUtility.makeFullName(response.getOfficersProfileResponseVO().getOfficer_FirstName_Ar(), response.getOfficersProfileResponseVO().getOfficer_LastName_Ar()));
            signInPassCodeDto.setAccessRole(response.getOfficersProfileResponseVO().getAccessRoleCode());
        }

        return signInPassCodeDto;
    }


}
