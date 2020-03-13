package com.tpcmswebadmin.webpages.policeofficer.delegate;

import com.tpcmswebadmin.infrastructure.domain.dto.ResponseMVCDto;
import com.tpcmswebadmin.webpages.policeofficer.model.PoliceOfficerCreateModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@RequiredArgsConstructor
public class PoliceOfficerNewDelegate {

    public ResponseMVCDto createOfficer(PoliceOfficerCreateModel policeOfficerCreateModel, HttpServletRequest httpServletRequest) {

        return null;
    }
}
