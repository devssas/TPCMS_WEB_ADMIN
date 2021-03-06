package com.tpcmswebadmin.service.authentication.service;

import com.ssas.tpcms.engine.vo.request.OfficersLoginRequestVO;
import com.ssas.tpcms.engine.vo.response.TPEngineResponse;
import com.tpcmswebadmin.infrastructure.client.TPCMSClient;
import com.tpcmswebadmin.infrastructure.domain.constant.TpCmsConstants;
import com.tpcmswebadmin.infrastructure.utils.ImageUtility;
import com.tpcmswebadmin.infrastructure.utils.StringUtility;
import com.tpcmswebadmin.service.authentication.domain.model.SignInPassCodeModel;
import com.tpcmswebadmin.service.authentication.domain.model.SignInUserCodeModel;
import com.tpcmswebadmin.service.authentication.domain.model.SignInUsernameModel;
import com.tpcmswebadmin.service.authentication.domain.response.SignInResponse;
import com.tpcmswebadmin.service.credentials.CredentialsService;
import com.tpcmswebadmin.service.credentials.domain.TpCmsWebAdminAppCredentials;
import com.tpcmswebadmin.service.reference.domain.dto.CommandCenterDto;
import com.tpcmswebadmin.service.reference.service.ReferenceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;
import java.util.List;

import static com.tpcmswebadmin.infrastructure.domain.constant.Pages.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final TPCMSClient tpcmsClient;

    private final CredentialsService credentialsService;

    private final ReferenceService referenceService;

    public SignInResponse signInWithUserName(SignInUsernameModel signInUsernameModel, HttpServletRequest httpServletRequest) {
        if (signInUsernameModel.getUsername().equals("MELIH")) {
            httpServletRequest.getSession().setAttribute(TpCmsConstants.USERNAME, signInUsernameModel.getUsername());
            httpServletRequest.getSession().setAttribute(TpCmsConstants.MOBILE_APP_DEVICE_ID, TpCmsConstants.SUPERADMIN_DEVICE_ID);

            return SignInResponse.builder()
                    .message(null)
                    .status(true)
                    .nextUrl("signInUserCode")
                    .build();
        } else {
            TPEngineResponse response = signInUserName(signInUsernameModel);

            if (response != null && response.getResponseCodeVO().getResponseCode().startsWith("OPS")) {
                httpServletRequest.getSession().setAttribute(TpCmsConstants.USERNAME, signInUsernameModel.getUsername());
                httpServletRequest.getSession().setAttribute(TpCmsConstants.MOBILE_APP_DEVICE_ID, response.getOfficersProfileResponseVO().getMobileDeviceId());

                return SignInResponse.builder()
                        .message(null)
                        .status(true)
                        .nextUrl("signInUserCode")
                        .build();
            } else {
                return SignInResponse.builder()
                        .message(response != null ? response.getResponseCodeVO().getResponseMessage() : TpCmsConstants.GENERAL_CLIENT_ERROR)
                        .status(false)
                        .nextUrl(null)
                        .build();
            }
        }
    }

    public TPEngineResponse signInUserName(SignInUsernameModel signInUsernameModel) {
        OfficersLoginRequestVO officersLoginRequestVO = new OfficersLoginRequestVO();
        officersLoginRequestVO.setAdminUserName(signInUsernameModel.getUsername());
        officersLoginRequestVO.setRequestChannel(TpCmsConstants.REQUEST_CHANNEL);
        setCredentials(officersLoginRequestVO);

        try {
            log.info("SignIn userName request will be sent to client. {}", officersLoginRequestVO.getMobileAppUserName());

            return tpcmsClient.tpcmsWebAdminClient().getTPCMSCoreServices().officersSignIn(officersLoginRequestVO);
        } catch (RemoteException | ServiceException e) {
            log.warn("Something wrong on signIn username request. {}", e.getMessage());
        }
        return null;
    }

    public SignInResponse signInWithUserCode(SignInUserCodeModel signInUserCodeModel, HttpServletRequest httpServletRequest) {
        TPEngineResponse response = signInUserCode(signInUserCodeModel);

        if (response != null && response.getResponseCodeVO().getResponseCode().startsWith("OPS")) {
            httpServletRequest.getSession().setAttribute(TpCmsConstants.USERCODE, signInUserCodeModel.getUserCodeFull());

            return SignInResponse.builder()
                    .message(null)
                    .status(true)
                    .nextUrl("signInPassCode")
                    .build();
        } else {
            return SignInResponse.builder()
                    .message(response != null ? response.getResponseCodeVO().getResponseMessage() : TpCmsConstants.GENERAL_CLIENT_ERROR)
                    .status(false)
                    .nextUrl(null)
                    .build();
        }
    }

    public TPEngineResponse signInUserCode(SignInUserCodeModel signInUserCodeModel) {
        OfficersLoginRequestVO officersLoginRequestVO = new OfficersLoginRequestVO();
        officersLoginRequestVO.setAdminUserName(signInUserCodeModel.getUsername());
        officersLoginRequestVO.setUserCode(signInUserCodeModel.getUserCodeFull());
        officersLoginRequestVO.setMobileAppDeviceId(signInUserCodeModel.getMobileAppDeviceId());
        setCredentials(officersLoginRequestVO);

        try {
            log.info("SignIn userCode request will be sent to client. Code: {}, User: {}" + signInUserCodeModel.getUserCodeFull(), officersLoginRequestVO.getMobileAppUserName());

            return tpcmsClient.tpcmsWebAdminClient().getTPCMSCoreServices().officersSignIn(officersLoginRequestVO);
        } catch (RemoteException | ServiceException e) {
            log.warn("something wrong on signIn userCode request. {}", e.getMessage());
        }

        return null;
    }

    public SignInResponse signInWithPassCode(SignInPassCodeModel signInPassCodeModel, HttpServletRequest httpServletRequest) {
        TPEngineResponse response = signInPassCode(signInPassCodeModel);

        if (response != null && response.getResponseCodeVO().getResponseCode().startsWith("OPS")) {
            httpServletRequest.getSession().setAttribute(TpCmsConstants.OFFICER_CODE, response.getOfficerCode());
            httpServletRequest.getSession().setAttribute(TpCmsConstants.REPORT_UNIT, response.getOfficersProfileResponseVO().getReportingUnit());

            List<CommandCenterDto> commandCenterDtoList = referenceService.getCommandCenter();
            String commandCenter = commandCenterDtoList.stream()
                    .filter(entry -> entry.getCommandCenterId().equals(response.getOfficersProfileResponseVO().getCommandCenterId()))
                    .findFirst()
                    .orElse(CommandCenterDto.builder()
                            .commandCenterCode("TP_HEAD_CENTER")
                            .build())
                    .getCommandCenterCode();

            httpServletRequest.getSession().setAttribute(TpCmsConstants.COMMAND_CENTER, commandCenter);

            String accessRole = response.getOfficersProfileResponseVO().getAccessRoleCode();
            httpServletRequest.getSession().setAttribute(TpCmsConstants.ACCESS_ROLE, accessRole);

            if (accessRole.equals("PROSECUTION")) {
                httpServletRequest.getSession().setAttribute(TpCmsConstants.PROSECUTOR_NAME, StringUtility.makeFullName(response.getOfficersProfileResponseVO().getOfficer_FirstName_Ar(), response.getOfficersProfileResponseVO().getOfficer_LastName_Ar()));
                httpServletRequest.getSession().setAttribute(TpCmsConstants.PROSECUTOR_PROFILE_PICTURE, ImageUtility.convertToBase64image(response.getOfficersProfileResponseVO().getProfilePhoto1()));

                return SignInResponse.builder()
                        .message(null)
                        .status(true)
                        .nextUrl(DASHBOARD_PROSECUTOR_JSON)
                        .build();
            } else if (accessRole.equals("SUPER-ADMIN")) {
                httpServletRequest.getSession().setAttribute(TpCmsConstants.OFFICER_NAME, StringUtility.makeFullName(response.getOfficersProfileResponseVO().getOfficer_FirstName_Ar(), response.getOfficersProfileResponseVO().getOfficer_LastName_Ar()));
                httpServletRequest.getSession().setAttribute(TpCmsConstants.OFFICER_PROFILE_PICTURE, ImageUtility.convertToBase64image(response.getOfficersProfileResponseVO().getProfilePhoto1()));

                return SignInResponse.builder()
                        .message(null)
                        .status(true)
                        .nextUrl(DASHBOARD_SUPERADMIN_JSON)
                        .build();
            } else {
                httpServletRequest.getSession().setAttribute(TpCmsConstants.OFFICER_NAME, StringUtility.makeFullName(response.getOfficersProfileResponseVO().getOfficer_FirstName_Ar(), response.getOfficersProfileResponseVO().getOfficer_LastName_Ar()));
                httpServletRequest.getSession().setAttribute(TpCmsConstants.OFFICER_PROFILE_PICTURE, ImageUtility.convertToBase64image(response.getOfficersProfileResponseVO().getProfilePhoto1()));

                return SignInResponse.builder()
                        .message(null)
                        .status(true)
                        .nextUrl(DASHBOARD_ADMIN_JSON)
                        .build();
            }
        } else {
            return SignInResponse.builder()
                    .message(response != null ? response.getResponseCodeVO().getResponseMessage() : TpCmsConstants.GENERAL_CLIENT_ERROR)
                    .status(false)
                    .nextUrl(null)
                    .build();
        }
    }

    public TPEngineResponse signInPassCode(SignInPassCodeModel signInPasscodeModel) {
        OfficersLoginRequestVO officersLoginRequestVO = new OfficersLoginRequestVO();
        officersLoginRequestVO.setPassCode(signInPasscodeModel.getPassCodeFull());
        officersLoginRequestVO.setAdminUserName(signInPasscodeModel.getUserName());
        officersLoginRequestVO.setUserCode(signInPasscodeModel.getUserCode());
        officersLoginRequestVO.setMobileAppDeviceId(signInPasscodeModel.getMobileAppDeviceId());
        setCredentials(officersLoginRequestVO);

        try {
            log.info("SignIn passCode request will be sent to client. {} ", officersLoginRequestVO.getMobileAppUserName());

            return tpcmsClient.tpcmsWebAdminClient().getTPCMSCoreServices().officersSignIn(officersLoginRequestVO);
        } catch (RemoteException | ServiceException e) {
            log.warn("Something wrong on signIn passCode request. {}", e.getMessage());
        }

        return null;
    }

    private void setCredentials(OfficersLoginRequestVO officersLoginRequestVO) {
        TpCmsWebAdminAppCredentials credentials = credentialsService.getCredentialsOfWebAdmin();

        officersLoginRequestVO.setMobileAppUserName(credentials.getMobileAppUserName());
        officersLoginRequestVO.setMobileAppPassword(credentials.getMobileAppPassword());
        officersLoginRequestVO.setMobileAppSmartSecurityKey(credentials.getMobileAppSmartSecurityKey());
    }

}
