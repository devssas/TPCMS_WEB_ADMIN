package com.tpcmswebadmin.service.authentication;

import com.ssas.tpcms.engine.vo.request.OfficersLoginRequestVO;
import com.tpcmswebadmin.domain.authentication.SignInPasscodeModel;
import com.tpcmswebadmin.domain.authentication.SignInUsercodeModel;
import com.tpcmswebadmin.domain.authentication.SignInUsernameModel;
import com.tpcmswebadmin.infrastructure.client.TPCMSClient;
import com.tpcmswebadmin.infrastructure.utils.StringUtility;
import org.springframework.stereotype.Service;

import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;
import java.util.logging.Logger;


@Service
public class AuthenticationService {

    private static final Logger logger = Logger.getLogger(AuthenticationService.class.getName());

    private final TPCMSClient tpcmsClient;

    public AuthenticationService(TPCMSClient tpcmsClient) {
        this.tpcmsClient = tpcmsClient;
    }

    public void signInUserName(SignInUsernameModel signInUsernameModel) {
        OfficersLoginRequestVO officersLoginRequestVO = new OfficersLoginRequestVO();
        officersLoginRequestVO.setMobileAppUserName(signInUsernameModel.getUsername());

        try {
            logger.info(StringUtility.concat("SignIn userName request will be sent to client ", officersLoginRequestVO.getMobileAppUserName()));
            tpcmsClient.tpcmsWebAdminClient().getTPCMSCoreServices().officersSignIn(officersLoginRequestVO);
        } catch (RemoteException | ServiceException e) {
            logger.warning(StringUtility.concat("something wrong on signIn username request. " + officersLoginRequestVO.getMobileAppUserName()));
        }

    }

    public void signInUserCode(SignInUsercodeModel signInUsercodeModel) {
        OfficersLoginRequestVO officersLoginRequestVO = new OfficersLoginRequestVO();
        officersLoginRequestVO.setUserCode(signInUsercodeModel.getUserCode());

        try {
            logger.info(StringUtility.concat("SignIn userCode request will be sent to client ", officersLoginRequestVO.getMobileAppUserName()));
            tpcmsClient.tpcmsWebAdminClient().getTPCMSCoreServices().officersSignIn(officersLoginRequestVO);
        } catch (RemoteException | ServiceException e) {
            logger.warning(StringUtility.concat("something wrong on signIn userCode request. " + officersLoginRequestVO.getMobileAppUserName()));
        }
    }

    public void signInPassCode(SignInPasscodeModel signInPasscodeModel) {
        OfficersLoginRequestVO officersLoginRequestVO = new OfficersLoginRequestVO();
        officersLoginRequestVO.setPassCode(signInPasscodeModel.getPassCode());

        try {
            logger.info(StringUtility.concat("SignIn passCode request will be sent to client ", officersLoginRequestVO.getMobileAppUserName()));
            tpcmsClient.tpcmsWebAdminClient().getTPCMSCoreServices().officersSignIn(officersLoginRequestVO);
        } catch (RemoteException | ServiceException e) {
            logger.warning(StringUtility.concat("something wrong on signIn passCode request. " + officersLoginRequestVO.getMobileAppUserName()));
        }
    }
}
