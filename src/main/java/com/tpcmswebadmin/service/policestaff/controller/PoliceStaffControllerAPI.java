package com.tpcmswebadmin.service.policestaff.controller;

import com.tpcmswebadmin.service.policestaff.service.PoliceStaffServiceAPI;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PoliceStaffControllerAPI {

    private final PoliceStaffServiceAPI policeStaffServiceAPI;

    public PoliceStaffControllerAPI(PoliceStaffServiceAPI policeStaffServiceAPI) {
        this.policeStaffServiceAPI = policeStaffServiceAPI;
    }
}
