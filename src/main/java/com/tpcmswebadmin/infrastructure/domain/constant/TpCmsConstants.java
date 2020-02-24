package com.tpcmswebadmin.infrastructure.domain.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TpCmsConstants {

    public static final String LOGIN_USER = "loginUser";
    public static final String OFFICER_CODE = "officerCode";
    public static final String REPORT_UNIT = "reportUnit";
    public static final String USERNAME = "username";
    public static final String USERCODE = "userCode";


    /**
     * static parameter pass for webAdmin
     */
    public static final String WEB_ADMIN = "TPCMS_WEBADMIN";
    /**
     * static parameter pass for login calls
     */
    public static final String MOBILE_DEVICE_ID = "e369f536f443a91";

    /**
     * static user code for the signInUsername client call;
     */
    public static final String USER_CODE = "57115";

    /**
     * static parameter name, profile picture
     */
    public static final String OFFICER_NAME = "officerName";
    public static final String OFFICER_PROFILE_PICTURE = "profilePicture";
    public static final String ACCESS_ROLE = "accessRole";

}
