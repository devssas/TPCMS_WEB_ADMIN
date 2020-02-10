package com.tpcmswebadmin.service.authentication;

import com.ssas.tpcms.engine.vo.request.OfficersLoginRequestVO;
import com.tpcmswebadmin.service.authentication.domain.model.SignInPassCodeModel;
import com.tpcmswebadmin.service.authentication.domain.model.SignInUserCodeModel;
import com.tpcmswebadmin.service.authentication.domain.model.SignInUsernameModel;
import com.tpcmswebadmin.infrastructure.client.TPCMSClient;
import com.tpcmswebadmin.infrastructure.utils.StringUtility;
import com.tpcmswebadmin.service.credentials.CredentialsService;
import com.tpcmswebadmin.service.credentials.domain.TpCmsWebAdminAppCredentials;
import org.springframework.stereotype.Service;

import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;
import java.util.logging.Logger;


@Service
public class AuthenticationService {

    private static final Logger logger = Logger.getLogger(AuthenticationService.class.getName());

    private final TPCMSClient tpcmsClient;

    private final CredentialsService credentialsService;

    public AuthenticationService(TPCMSClient tpcmsClient, CredentialsService credentialsService) {
        this.tpcmsClient = tpcmsClient;
        this.credentialsService = credentialsService;
    }

    public void signInUserName(SignInUsernameModel signInUsernameModel) {
        OfficersLoginRequestVO officersLoginRequestVO = new OfficersLoginRequestVO();
        officersLoginRequestVO.setMobileAppUserName(signInUsernameModel.getUsername());

        setCredentials(officersLoginRequestVO);

        try {
            logger.info(StringUtility.concat("SignIn userName request will be sent to client ", officersLoginRequestVO.getMobileAppUserName()));
            tpcmsClient.tpcmsWebAdminClient().getTPCMSCoreServices().officersSignIn(officersLoginRequestVO);
        } catch (RemoteException | ServiceException e) {
            logger.warning(StringUtility.concat("something wrong on signIn username request. " + officersLoginRequestVO.getMobileAppUserName()));
        }
    }

    public void signInUserCode(SignInUserCodeModel signInUsercodeModel) {
        OfficersLoginRequestVO officersLoginRequestVO = new OfficersLoginRequestVO();
        officersLoginRequestVO.setUserCode(signInUsercodeModel.getUserCode());

        setCredentials(officersLoginRequestVO);

        try {
            logger.info(StringUtility.concat("SignIn userCode request will be sent to client ", officersLoginRequestVO.getMobileAppUserName()));
            tpcmsClient.tpcmsWebAdminClient().getTPCMSCoreServices().officersSignIn(officersLoginRequestVO);
        } catch (RemoteException | ServiceException e) {
            logger.warning(StringUtility.concat("something wrong on signIn userCode request. " + officersLoginRequestVO.getMobileAppUserName()));
        }
    }

    public void signInPassCode(SignInPassCodeModel signInPasscodeModel) {
        OfficersLoginRequestVO officersLoginRequestVO = new OfficersLoginRequestVO();
        officersLoginRequestVO.setPassCode(signInPasscodeModel.getPassCode());

        setCredentials(officersLoginRequestVO);

        try {
            logger.info(StringUtility.concat("SignIn passCode request will be sent to client ", officersLoginRequestVO.getMobileAppUserName()));
            tpcmsClient.tpcmsWebAdminClient().getTPCMSCoreServices().officersSignIn(officersLoginRequestVO);
        } catch (RemoteException | ServiceException e) {
            logger.warning(StringUtility.concat("something wrong on signIn passCode request. " + officersLoginRequestVO.getMobileAppUserName()));
        }
    }

    private void setCredentials(OfficersLoginRequestVO officersLoginRequestVO) {
        TpCmsWebAdminAppCredentials credentials = credentialsService.getCredentialsOfWebAdmin();
        officersLoginRequestVO.setMobileAppUserName(credentials.getMobileAppUserName());
        officersLoginRequestVO.setMobileAppDeviceId(credentials.getMobileAppDeviceId());
        officersLoginRequestVO.setMobileAppPassword(credentials.getMobileAppPassword());
        officersLoginRequestVO.setMobileAppSmartSecurityKey(credentials.getMobileAppSmartSecurityKey());
    }

}
