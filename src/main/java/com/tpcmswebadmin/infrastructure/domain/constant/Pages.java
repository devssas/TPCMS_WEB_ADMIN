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

    public static final String DASHBOARD_ADMIN = "dashboard_admin";
    public static final String DASHBOARD_PROSECUTOR = "dashboard_prosecutor";
    public static final String DASHBOARD_SUPERADMIN = "dashboard_superadmin";

    public static final String PROSECUTION_OFFICE_MENU = "prosecution_office_menu";
    public static final String SYSTEM_ADMIN_PROSECUTION_OFFICE_MENU = "system_administrator_prosecution_office_menu";

    public static final String NOTIFICATION_PROSECUTOR = "notification_prosecutor_view";

    public static final String PROSECUTION_REQUEST_EVIDENCE = "prosecution_request_evidence";
    public static final String PROSECUTION_SUBMIT_REVIEW = "prosecution_submit_review";

    /**
     * Redirect
     */
    public static final String REDIRECT_SIGN_IN_USERNAME = "redirect:/signInUsername";
    public static final String REDIRECT_SIGN_IN_USERCODE = "redirect:/signInUserCode";
    public static final String REDIRECT_SIGN_IN_PASSCODE = "redirect:/signInPassCode";

    public static final String REDIRECT_DASHBOARD = "redirect:/dashboard";
    public static final String REDIRECT_PROSECUTOR_DASHBOARD = "redirect:/dashboardProsecutor";

    /**
     * Error
     */
    public static final String ERROR_500 = "error_500";

    /**
     * Json Page For FE
     */

    public static final String DASHBOARD_ADMIN_JSON = "dashboard";
    public static final String DASHBOARD_PROSECUTOR_JSON = "dashboardProsecutor";
    public static final String DASHBOARD_SUPERADMIN_JSON = "dashboardSuperAdmin";

    /**
     * Mvc page names
     */
    public static final String MENU_BAR_PROSECUTION_PROSECUTION_HOME = "prosecutionOfficeMenu";
    public static final String MENU_BAR_SUPERADMIN_PROSECUTION_HOME = "prosecutionOfficeMenuAdmin";

}

