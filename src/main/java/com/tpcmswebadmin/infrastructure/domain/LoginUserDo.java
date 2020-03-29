package com.tpcmswebadmin.infrastructure.domain;

import com.tpcmswebadmin.infrastructure.domain.constant.TpCmsConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.servlet.http.HttpServletRequest;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginUserDo extends PaginationDo {

    private String loginOfficersCode;

    private String loginOfficerUnitNumber;

    private String mobileAppDeviceId;

    private String accessRole;

    private String calenderDate;

    private String reportingUnit;

    private String searchKey;

    private String status;

    public static LoginUserDo makeLoginUser(HttpServletRequest httpServletRequest) {
        return LoginUserDo.builder()
                .loginOfficersCode((String) httpServletRequest.getSession().getAttribute(TpCmsConstants.OFFICER_CODE))
                .loginOfficerUnitNumber((String) httpServletRequest.getSession().getAttribute(TpCmsConstants.REPORT_UNIT))
                .mobileAppDeviceId((String) httpServletRequest.getSession().getAttribute(TpCmsConstants.MOBILE_APP_DEVICE_ID))
                .accessRole((String) httpServletRequest.getSession().getAttribute(TpCmsConstants.ACCESS_ROLE))
                .build();
    }

}
