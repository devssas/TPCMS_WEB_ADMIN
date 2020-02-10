package com.tpcmswebadmin.service.credentials;

import com.tpcmswebadmin.service.credentials.domain.TpCmsWebAdminAppCredentials;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CredentialsService {

    @Value("${tpcms.credentials.appUserName}")
    private String appUserName;

    @Value("${tpcms.credentials.appPassword}")
    private String appPassword;

    @Value("${tpcms.credentials.appSmartSecurityKey}")
    private String appSmartSecurityKey;

    public TpCmsWebAdminAppCredentials getCredentialsOfWebAdmin() {
        return new TpCmsWebAdminAppCredentials.Builder()
                .mobileAppDeviceId(null)
                .mobileAppUserName(appUserName)
                .mobileAppPassword(appPassword)
                .mobileAppSmartSecurityKey(appSmartSecurityKey)
                .build();
    }
}
