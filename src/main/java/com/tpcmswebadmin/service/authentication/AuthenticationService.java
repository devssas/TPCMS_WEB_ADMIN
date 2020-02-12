package com.tpcmswebadmin.service.authentication;

import com.ssas.tpcms.engine.vo.request.OfficersLoginRequestVO;
import com.ssas.tpcms.engine.vo.response.TPEngineResponse;
import com.tpcmswebadmin.service.authentication.domain.model.SignInPassCodeModel;
import com.tpcmswebadmin.service.authentication.domain.model.SignInUserCodeModel;
import com.tpcmswebadmin.service.authentication.domain.model.SignInUsernameModel;
import com.tpcmswebadmin.infrastructure.client.TPCMSClient;
import com.tpcmswebadmin.infrastructure.utils.StringUtility;
import com.tpcmswebadmin.service.credentials.CredentialsService;
import com.tpcmswebadmin.service.credentials.domain.TpCmsWebAdminAppCredentials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;


@Service
public class AuthenticationService {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

    private final TPCMSClient tpcmsClient;

    private final CredentialsService credentialsService;

    public AuthenticationService(TPCMSClient tpcmsClient, CredentialsService credentialsService) {
        this.tpcmsClient = tpcmsClient;
        this.credentialsService = credentialsService;
    }

    public TPEngineResponse signInUserName(SignInUsernameModel signInUsernameModel) {
        OfficersLoginRequestVO officersLoginRequestVO = new OfficersLoginRequestVO();
        officersLoginRequestVO.setAdminUserName(signInUsernameModel.getUsername());
        officersLoginRequestVO.setUserCode("57115"); //todo static in order to pass
        setCredentials(officersLoginRequestVO);

        try {
            logger.info("SignIn userName request will be sent to client. {}", officersLoginRequestVO.getMobileAppUserName());

            return tpcmsClient.tpcmsWebAdminClient().getTPCMSCoreServices().officersSignIn(officersLoginRequestVO);
        } catch (RemoteException | ServiceException e) {
            logger.warn("Something wrong on signIn username request. " + officersLoginRequestVO.getMobileAppUserName());
        }
        return null;
    }

    public TPEngineResponse signInUserCode(SignInUserCodeModel signInUserCodeModel) {
        OfficersLoginRequestVO officersLoginRequestVO = new OfficersLoginRequestVO();
        officersLoginRequestVO.setAdminUserName(signInUserCodeModel.getUsername());
        officersLoginRequestVO.setUserCode(signInUserCodeModel.getUserCodeFull());
        setCredentials(officersLoginRequestVO);

        try {
            logger.info("SignIn userCode request will be sent to client. Code: {}, User: {}" + signInUserCodeModel.getUserCodeFull(), officersLoginRequestVO.getMobileAppUserName());

            return tpcmsClient.tpcmsWebAdminClient().getTPCMSCoreServices().officersSignIn(officersLoginRequestVO);
        } catch (RemoteException | ServiceException e) {
            logger.warn(StringUtility.concat("something wrong on signIn userCode request. " + officersLoginRequestVO.getMobileAppUserName()));
        }

        return null;
    }

    public TPEngineResponse signInPassCode(SignInPassCodeModel signInPasscodeModel) {
        OfficersLoginRequestVO officersLoginRequestVO = new OfficersLoginRequestVO();
        officersLoginRequestVO.setPassCode(signInPasscodeModel.getPassCodeFull());
        officersLoginRequestVO.setAdminUserName(signInPasscodeModel.getUserName());
        officersLoginRequestVO.setUserCode(signInPasscodeModel.getUserCode());
        setCredentials(officersLoginRequestVO);

        try {
            logger.info("SignIn passCode request will be sent to client. {} ", officersLoginRequestVO.getMobileAppUserName());

            return tpcmsClient.tpcmsWebAdminClient().getTPCMSCoreServices().officersSignIn(officersLoginRequestVO);
        } catch (RemoteException | ServiceException e) {
            logger.warn(StringUtility.concat("Something wrong on signIn passCode request. " + officersLoginRequestVO.getMobileAppUserName()));
        }

        return null;
    }

    private void setCredentials(OfficersLoginRequestVO officersLoginRequestVO) {
        TpCmsWebAdminAppCredentials credentials = credentialsService.getCredentialsOfWebAdmin();

        officersLoginRequestVO.setMobileAppUserName(credentials.getMobileAppUserName());
        officersLoginRequestVO.setMobileAppDeviceId("e369f536f443a91"); //todo static
        officersLoginRequestVO.setMobileAppPassword(credentials.getMobileAppPassword());
        officersLoginRequestVO.setMobileAppSmartSecurityKey(credentials.getMobileAppSmartSecurityKey());
    }

}
