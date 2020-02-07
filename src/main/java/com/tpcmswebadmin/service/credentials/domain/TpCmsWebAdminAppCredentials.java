package com.tpcmswebadmin.service.credentials.domain;


public class TpCmsWebAdminAppCredentials {

    private String mobileAppDeviceId;

    private String mobileAppPassword;

    private String mobileAppSmartSecurityKey;

    private String mobileAppUserName;

    public TpCmsWebAdminAppCredentials(String mobileAppDeviceId, String mobileAppPassword, String mobileAppSmartSecurityKey, String mobileAppUserName) {
        this.mobileAppDeviceId = mobileAppDeviceId;
        this.mobileAppPassword = mobileAppPassword;
        this.mobileAppSmartSecurityKey = mobileAppSmartSecurityKey;
        this.mobileAppUserName = mobileAppUserName;
    }

    public String getMobileAppDeviceId() {
        return mobileAppDeviceId;
    }

    public String getMobileAppPassword() {
        return mobileAppPassword;
    }

    public String getMobileAppSmartSecurityKey() {
        return mobileAppSmartSecurityKey;
    }

    public String getMobileAppUserName() {
        return mobileAppUserName;
    }

    public static class Builder {
        private String mobileAppDeviceId;

        private String mobileAppPassword;

        private String mobileAppSmartSecurityKey;

        private String mobileAppUserName;

        public Builder() {
        }

        public Builder mobileAppDeviceId(String mobileAppDeviceId) {
            this.mobileAppDeviceId = mobileAppDeviceId;

            return this;
        }

        public Builder mobileAppPassword(String mobileAppPassword) {
            this.mobileAppPassword = mobileAppPassword;

            return this;
        }

        public Builder mobileAppSmartSecurityKey(String mobileAppSmartSecurityKey) {
            this.mobileAppSmartSecurityKey = mobileAppSmartSecurityKey;

            return this;
        }

        public Builder mobileAppUserName(String mobileAppUserName) {
            this.mobileAppUserName = mobileAppUserName;

            return this;
        }

        public TpCmsWebAdminAppCredentials build(){
            return new TpCmsWebAdminAppCredentials(mobileAppDeviceId, mobileAppPassword, mobileAppSmartSecurityKey, mobileAppUserName);
        }
    }


}
