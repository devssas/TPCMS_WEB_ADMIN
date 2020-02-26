package com.tpcmswebadmin.infrastructure.domain.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Pages {

    /**
     * Pages
     */
    public static final String SIGN_IN_USERNAME = "signin_username";
    public static final String SIGN_IN_USERCODE = "signin_usercode";
    public static final String SIGN_IN_PASSCODE = "signin_passcode";

    public static final String DASHBOARD = "dashboard";

    /**
     * Redirect
     */
    public static final String REDIRECT_SIGN_IN_USERNAME = "redirect:/signInUsername";
    public static final String REDIRECT_SIGN_IN_USERCODE = "redirect:/signInUserCode";
    public static final String REDIRECT_SIGN_IN_PASSCODE = "redirect:/signInPassCode";

    public static final String REDIRECT_DASHBOARD = "redirect:/dashboard";
}
