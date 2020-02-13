package com.tpcmswebadmin.service.policestaff.controller;

import com.tpcmswebadmin.infrastructure.client.response.ResponseDto;
import com.tpcmswebadmin.service.policestaff.service.PoliceStaffServiceAPI;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PoliceStaffControllerAPI {

    private final PoliceStaffServiceAPI policeStaffServiceAPI;

    public PoliceStaffControllerAPI(PoliceStaffServiceAPI policeStaffServiceAPI) {
        this.policeStaffServiceAPI = policeStaffServiceAPI;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("policeStaff")
    public ResponseDto<Void> merchantBrandingNameValidator() {
        return policeStaffServiceAPI.getPoliceOfficers();
    }

}
